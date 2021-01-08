package cn.edu.ahut.springbootdemo.controller;

import cn.edu.ahut.springbootdemo.common.TableResult;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import cn.edu.ahut.springbootdemo.service.IWOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
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

}
