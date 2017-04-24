package org.yyf.springBootDemo.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springBootDemo.domain.User;

import javax.validation.constraints.NotNull;

/**
 * Created by tobi on 2017/4/14.
 */
@RequestMapping
@RestController
@Validated
public class ValidateController {
    @GetMapping("/user/validate")
    public User user(@NotNull(message = "用户名不能为空！") String src) {
        User user = new User();
        user.setId(123L);
        user.setName("zhangshan");
        return user;
    }
}
