package com.biagio.siauth.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
public class SiAuthController {

    @GetMapping("/**")
    public String get(){
        log.info("AAAAAAAAAAA");
        return "a";
    };
    @PutMapping("/**")
    public String register(){
        log.info("AAAAAAAAAAA");
        return "a";
    };
}
