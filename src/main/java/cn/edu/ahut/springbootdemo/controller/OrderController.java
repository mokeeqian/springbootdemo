package cn.edu.ahut.springbootdemo.controller;

import cn.edu.ahut.springbootdemo.common.TableResult;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import cn.edu.ahut.springbootdemo.entity.WUser;
import cn.edu.ahut.springbootdemo.service.IWGoodService;
import cn.edu.ahut.springbootdemo.service.IWOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    // 用户自己的订单
    @RequestMapping(value = "/getPageOrderListBuyer")
    @ResponseBody
    public TableResult<WOrder> getPageOrderListBuyer(HttpSession session) {
        TableResult<WOrder> tableResult = new TableResult<>();
        WUser user = (WUser) session.getAttribute("user");
        Integer userid = user.getId();
        List<WOrder> res = iwOrderService.getAllOrdersByUserid(userid);

        Page<WOrder> orderPage = new PageImpl<>(res);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(orderPage.getTotalElements());
        tableResult.setData(orderPage.getContent());
        return tableResult;
    }




    @RequestMapping(value = "/orderSearch/{id}", method = RequestMethod.POST)
    @ResponseBody
    public TableResult<WOrder> orderSearch(@PathVariable Integer id) {
        TableResult<WOrder> tableResult = new TableResult<>();
        List<WOrder> orderList = iwOrderService.getAllOrdersByOrderid(id);
        Page<WOrder> orderPage = new PageImpl<>(orderList);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(orderPage.getTotalElements());
        tableResult.setData(orderPage.getContent());
        return tableResult;
    }

    @RequestMapping(value = "/orderSearchByUserid/{id}", method = RequestMethod.POST)
    @ResponseBody
    public TableResult<WOrder> orderSearchByUserid(@PathVariable Integer id) {
        TableResult<WOrder> tableResult = new TableResult<>();
        List<WOrder> orderList = iwOrderService.getAllOrdersByUserid(id);
        Page<WOrder> orderPage = new PageImpl<>(orderList);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(orderPage.getTotalElements());
        tableResult.setData(orderPage.getContent());
        return tableResult;
    }

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
