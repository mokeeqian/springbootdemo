package cn.edu.ahut.springbootdemo.controller;

import cn.edu.ahut.springbootdemo.common.TableResult;
import cn.edu.ahut.springbootdemo.entity.WOrder;
import cn.edu.ahut.springbootdemo.entity.WUser;
import cn.edu.ahut.springbootdemo.service.IWUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


// 对用户的操作
@Controller
public class UserController {
    @Autowired
    IWUserService wUserService;

    @RequestMapping(value = "/getPageUserList")
    @ResponseBody
    public TableResult<WUser> getPageUserList(int page, int limit) {
        TableResult<WUser> tableResult = new TableResult<WUser>();
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<WUser> userPage = wUserService.getAllPageUserList(pageable);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(userPage.getTotalElements());
        tableResult.setData(userPage.getContent());

        return tableResult;
    }

    @RequestMapping(value = "/addUser")
    @ResponseBody
    public String addUser(WUser user){
        String code;
        this.wUserService.addUser(user);
        code="0";
        return code;
    }

    @RequestMapping(value = "/delUserById")
    @ResponseBody
    public String delUserById(Integer id){
        String code;
        this.wUserService.delUserById(id);
        code="0";
        return code;
    }

    @RequestMapping(value = "/getCurrentUser")
    @ResponseBody
    public WUser getCurrentUser(HttpServletRequest request) {
        WUser user = (WUser) request.getSession().getAttribute("user");
        System.out.println(user.getUsername());

        return user;
    }


    @RequestMapping(value = "/findUserByUsernameContains/{pattern}", method = RequestMethod.POST)
    @ResponseBody
    public TableResult<WUser> findUserByUsernameContains(@PathVariable String pattern) {
        TableResult<WUser> tableResult = new TableResult<>();
        List<WUser> userList = wUserService.getAllUsersByUsernameContains(pattern);
        Page<WUser> userPage = new PageImpl<>(userList);
        tableResult.setCode(0);
        tableResult.setMsg("");
        tableResult.setCount(userPage.getTotalElements());
        tableResult.setData(userPage.getContent());
        return tableResult;
    }

}
