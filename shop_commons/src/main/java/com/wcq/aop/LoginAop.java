package com.wcq.aop;

import com.wcq.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**

 * 判定是否登录？ - 切面类
 *

 * 前置增强

 * 后置增强

 * 环绕增强

 * 异常增强

 * 后置完成增强
 *
 */
@Aspect
@Component
public class LoginAop {

    @Autowired
    private RedisTemplate redisTemplate;

    /**

     * 环绕增强
     *

     * @Around(切点表达式)
     *

     * 切点表达式 - 表示当前确定增强哪些方法

     * @annotation(IsLogin) - 表示标记了@IsLogin注解的方法就会被这个环绕增强植入
     *

     * @return
     */
    @Around("@annotation(IsLogin)")
    public Object loginAop(ProceedingJoinPoint joinPoint){

        //----------1、获得cookie--------------
        //获得请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获得cookie
        String loginToken = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("login_token")){
                    loginToken = cookie.getValue();
                    break;
                }
            }
        }

        //----------2、通过登录令牌判断是否登录----------
        User user = null;
        if(loginToken != null){
            //redis中验证
            user = (User) redisTemplate.opsForValue().get(loginToken);
        }

        //----------3、判断是否登录-----------------
        if(user == null){
            //当前未登录
            //获得@IsLogin注解

            //获得方法签名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获得方法的反射对象
            Method method = signature.getMethod();
            //通过反射对象获得方法上的注解
            IsLogin islogin = method.getAnnotation(IsLogin.class);
            //调用注解的方法
            boolean flag = islogin.mustLogin();

            //调用它的mustLogin方法，获得方法返回值
            if(flag){
                //获得当前的url
                String url = "http://www.kill.com" + request.getRequestURI().toString();

                //获得url后面的参数 keyword=xxx&key=xxxx&name=xxxx
                String queryString = request.getQueryString();

                if(queryString != null) {
                    url = url + "?" + queryString;
                }
                //http://localhost:16666/cart/insert

                //url编码
                try {
                    url = URLEncoder.encode(url, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //强制登录
                return "redirect:http://www.kill.com/sso/toLogin?returnUrl=" + url;
            }

        }

        //如果登录user就不为空，如果不登录user就为空
        UserHolder.setUser(user);

        //指定调用目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //清空threadlocal
        UserHolder.setUser(null);

        return result;
    }

}