package org.marble.service;

import org.marble.Form.PositionForm;
import org.marble.dao.PositionMapper;
import org.marble.emuns.CrudEnum;
import org.marble.pojo.Position;
import org.marble.pojo.RespBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

import static org.marble.emuns.CrudEnum.*;

@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPosition(){
        return positionMapper.getAllPosition();
    }


    public Integer addPosition(PositionForm positionForm) {
        Position position = new Position();
        BeanUtils.copyProperties(positionForm,position);
        position.setCreatedate(new Date());
        position.setEnabled(true);
        return positionMapper.insertSelective(position);
    }

    public Integer updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public RespBean deletePosition(Integer id) {
        if (positionMapper.deleteByPrimaryKey(id)==1){
            return RespBean.ok(DELETE_SUCCESS);
        }
        return RespBean.error(DELETE_FAIL);
    }

    public RespBean deletePositionByIds(Integer[] ids){
        if (positionMapper.deletePositionByIds(ids) == ids.length){
            return RespBean.ok(DELETE_SUCCESS);
        }
        return RespBean.error(DELETE_FAIL);
    }
}
