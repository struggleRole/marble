package org.marble.dao;

import org.apache.ibatis.annotations.Param;
import org.marble.pojo.Public;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Public record);

    int insertSelective(Public record);

    Public selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Public record);

    int updateByPrimaryKey(Public record);

    List<Public> selectPublicByNum(String publicNum);

    List<Public> allPublicByPage(@Param("page") Integer page, @Param("size")Integer size);

    Integer getTotal();

    List<Public> all();

}