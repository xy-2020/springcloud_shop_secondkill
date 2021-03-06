package com.wcq.service;

import com.wcq.entity.Goods;

import java.util.Date;
import java.util.List;

public interface IGoodsService {

    int insertGoods(Goods goods);

    List<Goods> goodsList();

    List<Goods> queryKillList(Date date);

    int updateKillSave(Integer gid, Integer gnumber);

    Goods queryById(Integer gid);
}
