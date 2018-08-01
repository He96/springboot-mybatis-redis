package com.spm.common;

import java.io.Serializable;

public class ResultWarp<T> extends Result implements Serializable {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultWarp(T data){
        super(1,"ok");
        this.data = data;
    }

    public ResultWarp(int code, String msg,T data) {
        super(code, msg);
        this.data = data;
    }

}
