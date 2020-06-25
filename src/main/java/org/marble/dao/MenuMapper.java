package org.marble.dao;

import org.marble.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getAllMenusById(Integer hrid);

    List<Menu> getAllMenuByRole();

    List<Menu> getAllMenus();



}