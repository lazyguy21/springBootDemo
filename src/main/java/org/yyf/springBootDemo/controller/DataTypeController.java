package org.yyf.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springBootDemo.service.DataTypeService;

import java.util.List;
import java.util.Map;

/**
 * Created by tobi on 2017/1/4.
 */
@RestController
public class DataTypeController {
    @Autowired
    private DataTypeService dataTypeService;

    @RequestMapping("listDataType")
    public List<Map> listDataType(){
        List<Map> maps = dataTypeService.listDataTypeMapper();
        return maps;
    }
    @RequestMapping("testT")
    public void testT(boolean fail){
        dataTypeService.testTransaction(fail);
    }
}
