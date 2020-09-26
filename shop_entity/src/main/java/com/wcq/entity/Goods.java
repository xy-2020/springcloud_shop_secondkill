package com.wcq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Goods extends BaseEntity{

    private String subject;
    private String info;
    private BigDecimal price;
    private Integer save;
    private Integer type = 1; //商品类型 1：普通商品 2：秒杀商品

    @TableField(exist = false)
    private String fmUrl;
    @TableField(exist = false)
    private List<String> otherUrl = new ArrayList<>();
    @TableField(exist = false)
    private GoodsSecondkill goodsKill;//商品的秒杀信息

    public void addOtherUrl(String url){

        otherUrl.add(url);
    }


}
