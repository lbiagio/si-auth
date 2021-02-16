package com.biagio.siauth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String password;
    private LocalDateTime lastUpdateDatetime;
}
