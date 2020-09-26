package com.wcq.serviceimpl;

import com.wcq.entity.Orders;

import java.util.List;

public interface IOrderService {


    List<Orders> queryByUid(Integer uid);

    Orders queryById(Integer id);

    Orders QueryByOid(String oid);

    int updateOrderStatus(String orderid, Integer status);

    int insertOrders(Integer gid, Integer uid, Integer gnumber);

}
