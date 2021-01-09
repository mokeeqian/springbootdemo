package cn.edu.ahut.springbootdemo.service;

import cn.edu.ahut.springbootdemo.entity.WOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IWOrderService {
    WOrder getOrderByOrderId(Integer id);
//    WOrder getOrderByUserId(Integer id);
    void addOrder(WOrder order);
    Page<WOrder> getAllPageOrderList(Pageable pageable);

    @Query(value = "select od from WOrder where od.userid = :userid", nativeQuery = true)
    public Page<WOrder> getCertainUserPageOrderList(@Param("userid") Integer userid);

}
