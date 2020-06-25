package org.marble.emuns;

import lombok.Getter;

@Getter
public enum CrudEnum {

    ADD_SUCCESS("添加成功"),
    ADD_FAIL("添加失败"),

    UPDATE_SUCCESS("更新成功"),
    UPDATE_FAIL("更新失败"),

    DELETE_SUCCESS("删除成功"),
    DELETE_FAIL("删除失败"),

    TEST_SUCCESS("测试成功"),
    TEST_FAIL("测试失败"),

    ;

    String msg;

    CrudEnum(String msg) {
        this.msg = msg;
    }
}
