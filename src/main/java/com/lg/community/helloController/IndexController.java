package com.lg.community.helloController;

import com.lg.community.mapper.UserMapper;
import com.lg.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
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

        return "index";
    }

}