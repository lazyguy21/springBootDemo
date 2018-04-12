package org.yyf.springBootDemo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import lombok.Data;

/**
 * Created by lazyguy on 2016-5-20.
 */
@Data
public class User {
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private Boolean ifOk;
    private ColorEnum colorEnum;
    private User friend;

    private ComputerState computerState;

    public User() {

    }

    public User(Long id, String name, Date birthday, Boolean ifOk) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.ifOk = ifOk;
    }


}
