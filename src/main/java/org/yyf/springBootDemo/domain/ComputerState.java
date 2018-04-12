package org.yyf.springBootDemo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComputerState implements CodeInsideEnum {
    OPEN(10),         //开启
    CLOSE(11),         //关闭
    OFF_LINE(12),     //离线
    FAULT(200),     //故障
    UNKNOWN(255);     //未知

    private int code;
    ComputerState(int code) { this.code = code; }

    @JsonValue
    @Override
    public int getCode() { return this.code; }


    @JsonCreator
    public static ComputerState valueOf(int code) {
        ComputerState computerState = CodeEnumUtil.codeOf(ComputerState.class, code);
        return computerState;
    }
}