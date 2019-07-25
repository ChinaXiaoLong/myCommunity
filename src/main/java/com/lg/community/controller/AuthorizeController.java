package com.lg.community.controller;

import com.lg.community.entity.AccessTokenEntity;
import com.lg.community.entity.GitHubUser;
import com.lg.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.secret}")
    private String clientsecret;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.url}")
    private String redirectUrl;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")
             String code,@RequestParam(name = "state") String state){
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id(clientId);
        accessTokenEntity.setClient_secret(clientsecret);
        accessTokenEntity.setCode(code);
        accessTokenEntity.setRedirect_uri(redirectUrl);
        accessTokenEntity.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenEntity);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println("userName="+user.getLogin());
        return "index";
    }

}
