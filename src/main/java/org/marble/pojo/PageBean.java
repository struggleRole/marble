package org.marble.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageBean {

    private Integer total;

    private List<?> data;


}
