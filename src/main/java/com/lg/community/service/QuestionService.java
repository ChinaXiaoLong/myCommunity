package com.lg.community.service;

import com.lg.community.dto.PageDto;

public interface QuestionService {
    PageDto list(Integer page, Integer size);
}
