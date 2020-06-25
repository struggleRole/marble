package org.marble.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Public {
    private Integer id;

    private String publicnum;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date publictime;

    private String name;

    private String address;

    private Nation nation;


}