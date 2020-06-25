package org.marble.service;

import org.marble.dao.HrMapper;
import org.marble.emuns.CrudEnum;
import org.marble.pojo.Hr;
import org.marble.pojo.RespBean;
import org.marble.untils.CurrentHr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    HrMapper hrMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        //这里就是前端传过来的username、password
        //需要使用username去查询用户，至于password会由SpringSecurity去验证是否正确
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        hr.setRoles(hrMapper.selectRole(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(CurrentHr.currentHr().getId(),keywords);
    }

    public RespBean updateHr(Hr hr) {
        if (hrMapper.updateByPrimaryKeySelective(hr)==1){
            return RespBean.ok(CrudEnum.UPDATE_SUCCESS);
        }
        return RespBean.error(CrudEnum.UPDATE_FAIL);
    }
}
