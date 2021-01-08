package cn.edu.ahut.springbootdemo.service;

import cn.edu.ahut.springbootdemo.dao.IWGoodDao;
import cn.edu.ahut.springbootdemo.entity.WGood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class WGoodService implements IWGoodService{

    @Autowired
    IWGoodDao iwGoodDao;

    @Override
    public WGood getWGoodByGoodId(Integer goodId) {
        return iwGoodDao.findDistinctTopById(goodId);
    }

    @Override
    public WGood getWGoodByGoodName(String goodName) {
        return iwGoodDao.findDistinctTopByName(goodName);
    }

    @Override
    public Page<WGood> getAllPageGoodList(Pageable pageable) {
        return iwGoodDao.findAll(pageable);
    }

}
