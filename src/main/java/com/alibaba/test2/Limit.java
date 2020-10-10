package com.alibaba.test2;

import java.io.Serializable;

public class Limit implements Serializable {

    /**
     * 跳过多少行
     */
    private int rows = 0;

    /**
     * 取多少行
     */
    private int offset;

    public Limit(int rows, int offset) {
        this.rows = rows;
        this.offset = offset;
    }

    public int getRows() {
        return rows;
    }

    public Limit setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public Limit setOffset(int offset) {
        this.offset = offset;
        return this;
    }
}
