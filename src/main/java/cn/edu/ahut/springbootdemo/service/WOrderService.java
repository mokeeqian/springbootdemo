package cn.edu.ahut.springbootdemo.service;

import cn.edu.ahut.springbootdemo.dao.IWOrderDao;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;



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
    @Query(value = "select od from WOrder where od.userid = :userid", nativeQuery = true)
    public Page<WOrder> getCertainUserPageOrderList(@Param("userid") Integer userid) {
        return null;
    }


    // 自定义查询条件
//    private class MySpec implements Specification<WOrder> {
//        @Override
//        public Predicate toPredicate(Root<WOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//            return null;
//        }
//    }
}


