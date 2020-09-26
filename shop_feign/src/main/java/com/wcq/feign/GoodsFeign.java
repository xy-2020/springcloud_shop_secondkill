package com.wcq.feign;

import com.wcq.entity.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient("MICSERVICE-GOODS")
public interface GoodsFeign {

    @RequestMapping("/goods/insert")
    int insertGoods(@RequestBody Goods goods);

    @RequestMapping("/goods/list")
    List<Goods> goodsList();

    @RequestMapping("/goods/queryKillList")
    List<Goods> queryKillList(@RequestBody Date date);

    @RequestMapping("/goods/queryKillById")
    Goods queryById(@RequestParam("gid") Integer gid);
}
