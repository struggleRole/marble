package org.marble.dao;

import org.apache.ibatis.annotations.Param;
import org.marble.pojo.Position;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int deletePositionByIds(@Param("ids") Integer[] ids);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPosition();



}