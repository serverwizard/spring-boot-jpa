package com.daou.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by hongjong-wan on 2018. 1. 28..
 */
public class PageVO {

    private static final int DEFAULT_SIZE = 3;
    private static final int DEFAULT_MAX_SIZE = 50;

    private int page;
    private int size;

    private String keyword;
    private String type;

    public PageVO() {
        this.page = 1;
        this.size = DEFAULT_SIZE;
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        this.size = size < DEFAULT_MAX_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_MAX_SIZE : size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pageable makePageable(int direction, String... props) {
        Sort.Direction dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;

        return new PageRequest(this.page - 1, this.size, dir, props);
    }
}
