package com.comm.controller;

import com.comm.dto.AccessTokenDTO;
import com.comm.dto.GIthubUser;
import com.comm.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("b607be865a86525ba6f8");
        accessTokenDTO.setClient_secret("a22af1f19d88eca27f7479290ace694ac8f91fe0");
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        try{
            GIthubUser user = githubProvider.getUser(accessToken);
            System.out.println("whynot");
            System.out.println(user.getName());
            System.out.println(user.getBio());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }
}
