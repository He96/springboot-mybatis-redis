package com.spm.common;

import java.io.Serializable;
import java.util.List;

public class ListFooterResult<T> extends ListResult<T> implements Serializable {
    private T footer;

    public T getFooter() {
        return footer;
    }

    public void setFooter(T footer) {
        this.footer = footer;
    }

    public ListFooterResult(T rows, T footer) {
        super(rows);
        this.footer = footer;
    }

    public ListFooterResult(Integer total,T rows, T footer) {
        super(total, rows);
        this.footer = footer;
    }

    public ListFooterResult(int code, String msg, T rows, T footer) {
        super(code, msg, rows);
        this.footer = footer;
    }

    public ListFooterResult(int code, String msg, Integer total, T rows, T footer) {
        super(code, msg, total, rows);
        this.footer = footer;
    }
}
