package cn.edu.ahut.springbootdemo.controller;

import cn.edu.ahut.springbootdemo.common.TableResult;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import cn.edu.ahut.springbootdemo.service.IWGoodService;
import cn.edu.ahut.springbootdemo.service.IWOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    IWOrderService iwOrderService;

    @Autowired
    IWGoodService iwGoodService;

    @RequestMapping(value = "/getPageOrderList")
    @ResponseBody
    public TableResult<WOrder> getPageOrderList(int page, int limit) {
        TableResult<WOrder> tableResult = new TableResult<>();
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<WOrder> userPage = iwOrderService.getAllPageOrderList(pageable);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(userPage.getTotalElements());
        tableResult.setData(userPage.getContent());

        return tableResult;
    }

    //TODO: 多表条件查询
    @RequestMapping(value = "/getPageOrderListBuyer")
    @ResponseBody
    public TableResult<WOrder> getPageOrderListBuyer(int page, int limit) {
        TableResult<WOrder> tableResult = new TableResult<>();
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<WOrder> userPage = iwOrderService.getAllPageOrderList(pageable);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(userPage.getTotalElements());
        tableResult.setData(userPage.getContent());

        return tableResult;
    }


//    //TODO:
//    @RequestMapping(value = "/orderSearch")
//    public String orderSearch(Model model) {
//        String code;
//        if ( true ) {
//
//
//            code = "0";
//        } else {
//            code = "1";
//        }
//
//        return code;
//    }

//    // 取结果
//    @RequestMapping(value = "/orderSearch/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public TableResult<WOrder> orderSearch(@PathVariable Integer id) {
//
//    }

    // 订单发货
    @RequestMapping(value = "/orderSend")
    @ResponseBody
    public String orderSend(Integer id, Model model) {
        String code;
        WOrder order = this.iwOrderService.getOrderByOrderId(id);

        if ( order.getStatus().equals("已下单") ) {
            // 设置订单状态
            order.setStatus("已发货");
            model.addAttribute("order", order);
            // 存回数据库
            iwOrderService.addOrder(order);
            code = "0";
        } else {
            code = "1";
        }
        return code;
    }

    // 确认收货
    @RequestMapping(value = "/orderReceive")
    @ResponseBody
    public String orderReceive(Integer id, Model model) {
        String code;
        WOrder order = this.iwOrderService.getOrderByOrderId(id);

        if ( order.getStatus().equals("已发货") ) {
            // 设置订单状态
            order.setStatus("已收货");
            model.addAttribute("order", order);
            // 存回数据库
            iwOrderService.addOrder(order);
            code = "0";
        } else {
            code = "1";
        }
        return code;
    }


    // 取消订单
    @RequestMapping(value = "/orderReject")
    @ResponseBody
    public String orderReject(Integer id, Model model) {
        String code;
        WOrder order = this.iwOrderService.getOrderByOrderId(id);

        if ( order.getStatus().equals("已下单") ) {

            // 设置订单状态
            order.setStatus("已取消");
            model.addAttribute("order", order);
            // 存回数据库
            iwOrderService.addOrder(order);
            iwGoodService.IncreGoodQuantity(order.getGoodid());
            code = "0";
        } else {
            code = "1";
        }
        return code;
    }

}
