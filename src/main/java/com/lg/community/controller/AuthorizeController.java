package com.lg.community.controller;

import com.lg.community.entity.AccessTokenEntity;
import com.lg.community.entity.GitHubUser;
import com.lg.community.mapper.UserMapper;
import com.lg.community.model.User;
import com.lg.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.secret}")
    private String clientsecret;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.url}")
    private String redirectUrl;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id(clientId);
        accessTokenEntity.setClient_secret(clientsecret);
        accessTokenEntity.setCode(code);
        accessTokenEntity.setRedirect_uri(redirectUrl);
        accessTokenEntity.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenEntity);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if(gitHubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

}
