package cn.edu.ahut.springbootdemo.service;


import cn.edu.ahut.springbootdemo.entity.WGood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;

public interface IWGoodService {
    WGood getWGoodByGoodId(Integer goodId);
    WGood getWGoodByGoodName(String goodName);
    Page<WGood> getAllPageGoodList(Pageable pageable);

}
