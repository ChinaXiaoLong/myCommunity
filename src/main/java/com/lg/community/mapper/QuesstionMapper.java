package com.lg.community.mapper;

import com.lg.community.dto.QuestionDto;
import com.lg.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuesstionMapper {
    @Insert("INSERT into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) \n" +
            "VALUES(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);

    @Select("SELECT * from question")
    List<Question> list();
}
