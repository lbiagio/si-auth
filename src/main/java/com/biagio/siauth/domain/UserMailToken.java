package com.biagio.siauth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class UserMailToken {
    long userId;
    String tokenUrl;
    LocalDateTime lastUpdateDateTime;
}
