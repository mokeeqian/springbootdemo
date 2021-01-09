package cn.edu.ahut.springbootdemo.service;

import cn.edu.ahut.springbootdemo.dao.IWOrderDao;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WOrderService implements IWOrderService{

    @Autowired
    IWOrderDao iwOrderDao;

    @Override
    public WOrder getOrderByOrderId(Integer id) {
        return iwOrderDao.getOne(id);
    }

    @Override
    public void addOrder(WOrder order) {
        iwOrderDao.save(order);
    }

    @Override
    public Page<WOrder> getAllPageOrderList(Pageable pageable) {
        return iwOrderDao.findAll(pageable);
    }

    @Override
    public List<WOrder> getAllOrdersByUserid(Integer id) {
        return iwOrderDao.findWOrdersByUseridEquals(id);
    }

    @Override
    public List<WOrder> getAllOrdersByOrderid(Integer id) {
        return iwOrderDao.findWOrdersByIdEquals(id);
    }


}


