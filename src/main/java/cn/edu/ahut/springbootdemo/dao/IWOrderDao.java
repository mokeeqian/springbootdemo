package cn.edu.ahut.springbootdemo.dao;

import cn.edu.ahut.springbootdemo.entity.WOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IWOrderDao extends JpaRepository<WOrder,Integer>, JpaSpecificationExecutor {
    WOrder findDistinctTopById(Integer id);
}
