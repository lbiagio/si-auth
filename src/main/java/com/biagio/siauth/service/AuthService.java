package com.biagio.siauth.service;

import com.biagio.siauth.domain.User;
import com.biagio.siauth.domain.UserDao;
import com.biagio.siauth.domain.UserMailToken;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDao userDao;
    @Value("${si-auth.expireTime}")
    long expireTime;

    public void insertUser(String username) {
        if(!this.userExists(username)) {
            String passwordHash = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
            userDao.insertUser(username, passwordHash, LocalDateTime.now().toString());
        } else {
            throw new InvalidParameterException("User already exists");
        }
    }

    public void insertUserMailToken(String username) {
        long id = userDao.getUser(username).getId();
        userDao.insertUserMailToken(id, UUID.randomUUID().toString(), LocalDateTime.now());
    }

    public boolean userExists(String username) {
        User user = userDao.getUser(username);
        return user != null && user.getUsername().equals(username);
    }

    public void updatePassword(String username, String password) {
        if(password.matches("[a]"))
    }

    public Boolean isValidCredentials(String username, String password) {
        User user = userDao.getUser(username);
        if(user != null) {
            return user.getUsername().equals(username)
                    && new BCryptPasswordEncoder().encode(password).equals(user.getPassword())
                    && user.getLastUpdateDatetime().isAfter(LocalDateTime.now().plusSeconds(expireTime));
        }
    }

    public boolean tokenIsValid(String username, String token) {
        UserMailToken userMailToken = userDao.getUserMailToken(username);
        return userMailToken.getTokenUrl().equals(token)
        && userMailToken.getLastUpdateDateTime().isAfter(LocalDateTime.now().plusSeconds(expireTime));
    }

    public void updateUserToken(String username) {
        UserMailToken userMailToken = userDao.getUserMailToken(username);
        if(userMailToken.getLastUpdateDateTime().isAfter(LocalDateTime.now().plusSeconds(expireTime))) {
            userDao.updateValidTokenUrl(username, UUID.randomUUID().toString(), LocalDateTime.now());
        }
    }
}
