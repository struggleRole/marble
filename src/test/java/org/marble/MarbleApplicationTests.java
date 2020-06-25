package org.marble;

import org.junit.jupiter.api.Test;
import org.marble.dao.HrMapper;
import org.marble.pojo.Public;
import org.marble.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MarbleApplicationTests {
    @Autowired
    HrMapper hrMapper;
    @Autowired
    MenuService menuService;


    private static final 

    @Test
    void contextLoads(int i) throws ParseException {
        System.out.println("i = " + i);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String str = "2014-05-17 16:32:45";
        Date date = null;
        List<Public> list = new ArrayList<>();

        date = dateFormat.parse(str);
        System.out.println(date);
        System.out.println();
        int num = 1;
        System.out.println(num);
        System.out.println("num = " + num);

        System.out.println("num = " + num);
        System.out.println("i = [" + i + "]");


    }

    public static void main(String[] args) {
        //ifn
        if (args == null) {
            
        }
        //inn
        if (args != null) {
            
        }


    }


}
