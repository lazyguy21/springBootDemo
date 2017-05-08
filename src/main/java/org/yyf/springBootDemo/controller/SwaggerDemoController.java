package org.yyf.springBootDemo.controller;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@RequestMapping("/user")
@Api(value = "swagger功能测试")
public class SwaggerDemoController {
    @ApiOperation(value = "你好")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful retrieval"),
        @ApiResponse(code = 404, message = "Test does not exist"),
        @ApiResponse(code = 500, message = "Internal server error")}
    )
    @ResponseBody
    @GetMapping(value = "/demo2")
    String getDemo() {
        return "Hello World \n" + "Spring boot3";
    }
    @ApiOperation(value = "你好223")
    @ResponseBody
    @PostMapping(value = "/demo23")
    String getDemo2() {
        return "Hello World \n" + "Spring boot3";
    }
}
