package cn.edu.ahut.springbootdemo.controller;

import cn.edu.ahut.springbootdemo.common.TableResult;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import cn.edu.ahut.springbootdemo.service.IWOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    IWOrderService iwOrderService;

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

    // 订单发货
    @RequestMapping(value = "/orderSend")
    @ResponseBody
    public String orderSend(Integer id, Model model) {
        String code;
        WOrder order = this.iwOrderService.getOrderByOrderId(id);

        // 设置订单状态
        order.setStatus(1);
        model.addAttribute("order", order);
        // 存回数据库
        iwOrderService.addOrder(order);
        code = "0";
        return code;
    }

    // 确认收货
    @RequestMapping(value = "/orderReceive")
    @ResponseBody
    public String orderReceive(Integer id, Model model) {
        String code;
        WOrder order = this.iwOrderService.getOrderByOrderId(id);

        // 设置订单状态
        order.setStatus(2);
        model.addAttribute("order", order);
        // 存回数据库
        iwOrderService.addOrder(order);
        code = "0";
        return code;
    }


    // 取消订单
    @RequestMapping(value = "/orderReject")
    @ResponseBody
    public String orderReject(Integer id, Model model) {
        String code;
        WOrder order = this.iwOrderService.getOrderByOrderId(id);

        // 设置订单状态
        order.setStatus(3);
        model.addAttribute("order", order);
        // 存回数据库
        iwOrderService.addOrder(order);
        code = "0";
        return code;
    }

}
