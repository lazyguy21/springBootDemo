package org.yyf.springBootDemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.yyf.springBootDemo.dao.DataTypeMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by tobi on 2017/1/4.
 */
@Service
@Transactional
@Slf4j
public class DataTypeService {
    @Autowired
    private DataTypeMapper dataTypeMapper;

    public List<Map> listDataTypeMapper() {
        List<Map> maps = dataTypeMapper.listDataType();
        return maps;
    }

    public void testTransaction(boolean fail) {
//        dataTypeMapper.insertOne();

        if (fail) {
            int x = 1 / 0;
        }
//        dataTypeMapper.insertOne();
        log.info("something3");
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        log.info("do something after transaction committed");
                    }
                });
        log.info("something 2");
    }

}
