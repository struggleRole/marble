package org.marble.controller;

import org.marble.pojo.Menu;
import org.marble.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenusById();
    }



}
