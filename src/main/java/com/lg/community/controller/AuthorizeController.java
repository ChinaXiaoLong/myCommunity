package com.lg.community.controller;

import com.lg.community.entity.AccessTokenEntity;
import com.lg.community.entity.GitHubUser;
import com.lg.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")
             String code,@RequestParam(name = "state") String state){
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id("69dd8a74fdad69bb3ab8");
        accessTokenEntity.setClient_secret("01b03290fb162baa250df61e0f62c36d42e27052");
        accessTokenEntity.setCode(code);
        accessTokenEntity.setRedirect_uri("http://localhost:8081/callback");
        accessTokenEntity.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenEntity);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println("userName="+user.getLogin());
        return "index";
    }

}
