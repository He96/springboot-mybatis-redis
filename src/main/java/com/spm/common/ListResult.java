package com.spm.common;

import java.io.Serializable;
import java.util.List;

public class ListResult<T> extends Result implements Serializable {

    private Integer total;
    private T rows;

    public ListResult(T rows){
        super(1,"ok");
        this.rows = rows;
    }

    public ListResult(Integer total,T rows){
        super(1,"ok");
        this.total = total;
        this.rows = rows;
    }

    public ListResult(int code, String msg,T rows) {
        super(code, msg);
        this.rows = rows;
    }

    public ListResult(int code, String msg,Integer total,T rows) {
        super(code, msg);
        this.total = total;
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
