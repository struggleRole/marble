package org.marble.controller.system.basic;

import lombok.extern.slf4j.Slf4j;
import org.marble.Form.PositionForm;
import org.marble.emuns.CrudEnum;
import org.marble.pojo.Position;
import org.marble.pojo.RespBean;
import org.marble.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.marble.emuns.CrudEnum.ADD_FAIL;

@RestController
@RequestMapping("/system/basic/pos")
@Slf4j
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPosition(){
        return positionService.getAllPosition();
    }

    @PostMapping("/")
    public RespBean addPosition(@Valid @RequestBody PositionForm positionForm
                                 ){

        if (positionService.addPosition(positionForm)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败！");
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updatePosition(position)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        System.out.println(id);
        return positionService.deletePosition(id);
    }

    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){
        return positionService.deletePositionByIds(ids);
    }

}
