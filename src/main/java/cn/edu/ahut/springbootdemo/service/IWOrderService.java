package cn.edu.ahut.springbootdemo.service;

import cn.edu.ahut.springbootdemo.entity.WOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWOrderService {
    WOrder getOrderByOrderId(Integer id);
//    WOrder getOrderByUserId(Integer id);
    void addOrder(WOrder order);
    Page<WOrder> getAllPageOrderList(Pageable pageable);

    List<WOrder> getAllOrdersByUserid(Integer id);
    List<WOrder> getAllOrdersByOrderid(Integer id);

}
