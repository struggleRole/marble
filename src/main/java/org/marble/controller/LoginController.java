package org.marble.controller;

import org.marble.pojo.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    //因为如果为为登录的话，会自动给你跳转到/login的路径下面，所以我们要处理这个路径下返回的json
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录！");
    }
}
