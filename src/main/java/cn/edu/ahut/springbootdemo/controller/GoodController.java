package cn.edu.ahut.springbootdemo.controller;

import cn.edu.ahut.springbootdemo.common.TableResult;
import cn.edu.ahut.springbootdemo.entity.WGood;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import cn.edu.ahut.springbootdemo.entity.WUser;
import cn.edu.ahut.springbootdemo.service.IWGoodService;
import cn.edu.ahut.springbootdemo.service.IWOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoodController {
    @Autowired
    IWGoodService wGoodService;
    @Autowired
    IWOrderService iwOrderService;

    @RequestMapping(value = "/getPageGoodList")
    @ResponseBody
    public TableResult<WGood> getPageGoodList(int page, int limit) {
        TableResult<WGood> tableResult = new TableResult<>();
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<WGood> goodPage = wGoodService.getAllPageGoodList(pageable);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(goodPage.getTotalElements());
        tableResult.setData(goodPage.getContent());

        return tableResult;
    }

    // 用户购买
    @RequestMapping(value = "/buyGood")
    @ResponseBody
    public String buyGood(WGood wGood, HttpServletRequest request){
        String code;
        WUser user = (WUser) request.getSession().getAttribute("user");

        System.out.println(wGood.toString());

        if ( wGood.getQuantity() >= 1 ) {
            WOrder order = new WOrder();
            order.setUserid(user.getId());
            order.setGoodid(wGood.getId());
            // 默认买一件
            order.setSize(1);

            // 状态： 下单
            order.setStatus("已下单");

            // 生成订单
            iwOrderService.addOrder(order);

            // 商品数目减一
            wGoodService.delGoodQuantity(wGood.getId());

            code = "0";
        } else {
            code = "1";
        }
        return code;
    }


}
