package com.lg.community.controller;

import com.lg.community.mapper.QuesstionMapper;
import com.lg.community.mapper.UserMapper;
import com.lg.community.model.Question;
import com.lg.community.model.User;
import com.lg.community.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuesstionMapper quesstionMapper;
    @Autowired
    UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("questionVo",new QuestionVo());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(QuestionVo questionVo, HttpServletRequest request, Model model){
            model.addAttribute("questionVo",questionVo);
        if (questionVo.getTitle() == null||questionVo.getTitle().equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (questionVo.getDescription() == null||questionVo.getDescription().equals("")){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if (questionVo.getTag() == null||questionVo.getTag().equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie:
                    cookies ){
                if(cookie.getName().equals("token")){
                    user = userMapper.findUserByToken(cookie.getValue());
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
            if (user == null){
                model.addAttribute("error","用户未登录");
                return "publish";
            }
            Question question = new Question();
            question.setTitle(questionVo.getTitle());
            question.setDescription(questionVo.getDescription());
            question.setTag(questionVo.getTag());
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            quesstionMapper.create(question);
        }

        return "redirect:/";
    }
}
