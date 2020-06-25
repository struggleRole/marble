package org.marble.exception;

import org.marble.pojo.RespBean;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class RuntimeException extends java.lang.RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespBean notValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        return RespBean.error(bindingResult.getFieldError().getField()+"=>"
        +bindingResult.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e){
        return RespBean.error("数据库SQL异常");
    }
}
