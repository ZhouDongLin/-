package com.example.bean;

public class RespObj {
    private int code;
    private String  msg;
    private Object data;

    public Object getData() {
        return data;
    }

    public RespObj(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RespObj(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
