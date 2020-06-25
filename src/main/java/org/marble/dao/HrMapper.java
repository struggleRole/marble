package org.marble.dao;

import org.apache.ibatis.annotations.Param;
import org.marble.pojo.Hr;
import org.marble.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String s);

    List<Role> selectRole(Integer id);

    List<Hr> getAllHrs(@Param("id") Integer id, @Param("keywords") String keywords);

}