package cn.edu.ahut.springbootdemo.dao;

import cn.edu.ahut.springbootdemo.entity.WGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IWGoodDao extends JpaRepository<WGood,Integer>, JpaSpecificationExecutor {
    WGood findDistinctTopById(Integer goodId);
    WGood findDistinctTopByName(String goodName);

}
