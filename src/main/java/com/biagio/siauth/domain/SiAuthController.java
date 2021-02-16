package com.biagio.siauth.domain;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiAuthController {

    @PutMapping("/register")
    public String register(){
        return "a";
    };
}
