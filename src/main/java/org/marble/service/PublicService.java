package org.marble.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.marble.dao.PublicMapper;
import org.marble.pojo.PageBean;
import org.marble.pojo.Public;
import org.marble.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicService {
    @Autowired
    PublicMapper publicMapper;

    public PageBean allPublicByPage(Integer page, Integer size){
        if (page != null && size != null){
            page = (page-1)*size;
        }
        List<Public> publics =  publicMapper.allPublicByPage(page,size);
        Integer total = publicMapper.getTotal();
        PageBean pageBean = new PageBean();
        pageBean.setData(publics);
        pageBean.setTotal(total);
        return pageBean;
    }

    public PageInfo allPublicPage(Integer page,Integer size){
        PageHelper.startPage(page,size);
        List<Public> publices =  publicMapper.allPublicByPage(page,size);
        PageInfo pageInfo = new PageInfo(publices);
        return pageInfo;
    }


    public RespBean allPublic() {
        List<Public> all = publicMapper.all();
        List<Public> collect = all.stream().filter(s -> s.getId() == 3 || s.getId()
                ==2)
                .collect
                (Collectors.toList());
        return RespBean.ok(collect);
    }
}
