package cn.edu.ahut.springbootdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
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
    public String logining(String username, String password) {
        String code;
        if ( username.equals("admin") && password.equals("admin")) {
            code = "0";
        }else  {
            code = "1";
        }
        return code;
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
