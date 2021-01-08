package cn.edu.ahut.springbootdemo.controller;


import cn.edu.ahut.springbootdemo.entity.WUser;
import cn.edu.ahut.springbootdemo.service.IWUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private IWUserService wUserService;

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logining")
    @ResponseBody
    public String logining(String username, String password, HttpSession httpSession){
        String code;
        WUser wuser= this.wUserService.getWuserByUsername(username);
        if(wuser!=null&&wuser.getPassword().equals(password)) {
            // 设置
            httpSession.setAttribute("user", wuser);
            if ( wuser.getRole() == 0 ) {
                // 卖家主页
                code="0";
            } else {
                // 买家主页
                code = "1";
            }
        }
        else {
            // 登录失败
            code = "-1";
        }
        return code;
    }

    // 卖家主页
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    // 买家主页
    @RequestMapping(value = "/index_buyer")
    public String indexBuyer() {
        return "index_buyer";
    }

    @RequestMapping(value = "/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/userList")
    public String userList() {
        return "userList";
    }

    @RequestMapping(value = "/goodList")
    public String goodList() {
        return "goodList";
    }


    @RequestMapping(value = "/userAdd")
    public String userAdd(){
        return "userAdd";
    }

    @RequestMapping(value = "/userEdit")
    public String userEdit(Integer id, Model model){
        WUser wUser=this.wUserService.getUserById(id);
        model.addAttribute("user",wUser);
        return "userEdit";
    }

    @RequestMapping(value = "/userQuery")
    public String userQuery(){
        return "userQuery";
    }

}
