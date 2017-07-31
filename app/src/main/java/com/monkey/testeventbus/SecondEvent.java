package com.monkey.testeventbus;

/**
 * Description:
 * Author: lanjing
 * Time: 2017/7/31 17:11
 */

public class SecondEvent {
    public String msg;

    public SecondEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SecondEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
