package com.daou.vo;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjong-wan on 2018. 1. 28..
 */
@Data
public class PageMaker<T> {

    private Page<T> result;

    private Pageable prevPage;
    private Pageable nextPage;

    private int currentPageNum;
    private int totalPageNum;

//    private Pageable currentPage; // currentPage를 구하는 방법?

    private List<Pageable> pageList;


    public PageMaker(Page<T> result) {
        this.result = result;

        this.currentPageNum = result.getNumber() + 1;
        this.totalPageNum = result.getTotalPages();

        this.pageList = new ArrayList<>();

        calcPages();
    }

    private void calcPages() {
        int tempEndNum = (int) Math.ceil(this.currentPageNum / 2.0) * 2;
        int startNum = tempEndNum - 1;

        if (!result.isFirst()) {
            Pageable startPage = result.previousPageable().next();
            for (int i = startNum; i < this.currentPageNum; i++) {
                startPage = startPage.previousOrFirst();
            }
            this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

            if (this.totalPageNum < tempEndNum) {
                tempEndNum = totalPageNum;
                this.nextPage = null;
            }

            for (long i = startNum; i <= tempEndNum; i++) {
                pageList.add(startPage);
                startPage = startPage.next();
            }

            this.nextPage = startPage.getPageNumber() < totalPageNum ? startPage : null;
        } else {

            this.prevPage = null;

            if (!result.isLast()) {
                Pageable startPage = result.nextPageable().previousOrFirst();
                for (long i = startNum; i <= tempEndNum; i++) {
                    pageList.add(startPage);
                    startPage = startPage.next();
                }
                this.nextPage = startPage.getPageNumber() < totalPageNum ? startPage : null;

            } else {
                nextPage = null;
            }
        }
    }
}

