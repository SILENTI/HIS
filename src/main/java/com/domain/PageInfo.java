package com.domain;

import java.util.List;

public class PageInfo {
    private Integer page ;
    private Integer rows ;
    private Integer total ;
    private Integer max ;
    private Integer start ;
    private Integer end ;
    private List<?> data ;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public PageInfo(Integer page, Integer rows, Integer total, Integer max, Integer start, Integer end, List<?> data) {
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.max = max;
        this.start = start;
        this.end = end;
        this.data = data;
    }

}
