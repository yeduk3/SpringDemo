package com.example.kakaoapi.domain.login;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Login {

    @GetMapping("/login")
    public String getAuthToken(@RequestParam String param) {
        WebClient
        
        https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}

    }
    
}