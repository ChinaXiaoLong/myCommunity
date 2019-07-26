package com.lg.community.service.impl;

import com.lg.community.dto.PageDto;
import com.lg.community.dto.QuestionDto;
import com.lg.community.mapper.QuesstionMapper;
import com.lg.community.mapper.UserMapper;
import com.lg.community.model.Question;
import com.lg.community.model.User;
import com.lg.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuesstionMapper quesstionMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public PageDto list(Integer page, Integer size) {
        Integer offset = size*(page -1);
        List<Question> questions = quesstionMapper.list(offset,size);
        if(questions.size() >0){
            List<QuestionDto> questionDtos = new ArrayList<>();
            PageDto pageDto = new PageDto();
            for (Question qusstion:
                    questions) {
                User user = userMapper.findById(qusstion.getCreator());
                QuestionDto questionDto = new QuestionDto();
                BeanUtils.copyProperties(qusstion,questionDto);
                questionDto.setUser(user);
                questionDtos.add(questionDto);
            }
            pageDto.setQuestions(questionDtos);
            Integer totalCount = quesstionMapper.count();
            pageDto.setPageination(totalCount,page,size);
            return pageDto;
        }else {
        return null;
        }
    }

}
