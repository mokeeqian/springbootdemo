package cn.edu.ahut.springbootdemo.dao;

import cn.edu.ahut.springbootdemo.entity.WOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IWOrderDao extends JpaRepository<WOrder,Integer>, JpaSpecificationExecutor {
    WOrder findDistinctTopById(Integer id);
    List<WOrder> findWOrdersByUseridEquals(Integer userid);
    List<WOrder> findWOrdersByIdEquals(Integer id);
}
