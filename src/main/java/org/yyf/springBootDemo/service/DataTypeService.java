package org.yyf.springBootDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yyf.springBootDemo.dao.DataTypeMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by tobi on 2017/1/4.
 */
@Service
@Transactional
public class DataTypeService {
    @Autowired
    private DataTypeMapper dataTypeMapper;

    public List<Map> listDataTypeMapper(){
        List<Map> maps = dataTypeMapper.listDataType();
        return maps;
    }

    public void testTransaction(){
        dataTypeMapper.insertOne();
        int x=1/0;
        dataTypeMapper.insertOne();
    }
}
