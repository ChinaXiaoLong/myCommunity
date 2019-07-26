package com.lg.community.dto;


import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
@Data
public class PageDto {
    //内容
    private List<QuestionDto> questions;
    //上一页
    private Boolean showPrevioue;
    //第一页
    private Boolean showFistPage;
    //是否有下一页
    private Boolean showNext;
    //是否有最后一页
    private Boolean showEnd;
    //当前页
    private Integer page;
    //当前的page数
    private List<Integer> pages = new ArrayList<>();

    public void setPageination(Integer totalCount, Integer page, Integer size) {
        //总页数
        Integer totalPage = 0;
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalCount = totalCount /size +1;
        }


        pages.add(page);
        for (int i = 1; i <=3 ; i++) {
            if(page - i >0){
                pages.add(page-1,0);
            }
            if (page + 1 <= totalPage){
                pages.add(page + i);
            }
        }
        //当当前页是第一页时没有上一页
        if(page == 1){
            showPrevioue = false;
        }else {
            showPrevioue = true;
        }
        //当当前页为最后一页时没有下一页
        if(page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }
        //是否展示第一页
        if(pages.contains(1)){
            showFistPage = false;
        }else {
            showFistPage = true;
        }
        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEnd = false;
        }{
            showEnd = true;
        }

    }
}
