package org.marble.service;

import org.marble.dao.MenuMapper;
import org.marble.pojo.Hr;
import org.marble.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getAllMenusById() {
        //获取当前用户的就是下面这个
        return menuMapper.getAllMenusById(((Hr)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getId());
    }

    public List<Menu> getAllMenuByRole(){
        return menuMapper.getAllMenuByRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

}
