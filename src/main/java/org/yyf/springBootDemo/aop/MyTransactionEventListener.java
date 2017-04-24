package org.yyf.springBootDemo.aop;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created by tobi on 2017/4/24.
 */
//@Component
public class MyTransactionEventListener {
    @TransactionalEventListener
    public void doSth(){

    }
}
