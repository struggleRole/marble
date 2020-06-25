package org.marble.controller;

import org.marble.pojo.PageBean;
import org.marble.pojo.RespBean;
import org.marble.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/log")
public class PublicController {
    @Autowired
    PublicService publicService;

    @GetMapping("/")
    public PageBean allPublicByPage(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "2")
                                            Integer size){
        return publicService.allPublicByPage(page,size);
    }

    @GetMapping("/all")
    public RespBean test(){
        return publicService.allPublic();
    }

}
