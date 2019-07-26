package com.lg.community.indexController;

import com.lg.community.dto.QuestionDto;
import com.lg.community.mapper.QuesstionMapper;
import com.lg.community.mapper.UserMapper;
import com.lg.community.model.User;
import com.lg.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    QuestionService questionService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return "index";
        }
        for (Cookie cookie:
                cookies ) {
            if(cookie.getName().equals("token")){
                User  user = userMapper.findUserByToken(cookie.getValue());
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        List<QuestionDto> questions = questionService.list();
        model.addAttribute("questions",questions);
        return "index";
    }

}