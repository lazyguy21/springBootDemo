package org.yyf.springBootDemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

/**
 * Created by tobi on 2017/1/4.
 */
@Mapper
public interface DataTypeMapper {
   List<Map> listDataType();
    void insertOne();
}
