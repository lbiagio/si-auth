package com.biagio.siauth.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface UserDao {

    @Insert("INSERT INTO TBL_USER (username, password, last_updated_timestamp) " +
            "VALUES (#{username}, #{password}, #{timestamp})")
    public void insertUser(String username, String password, String timestamp);

    @Insert("INSERT INTO TBL_USER_MAIL (id_user, token_url, last_updated_timestamp) " +
            "VALUES (#{idUsere}, #{tokenUrl}, #{timestamp})")
    public void insertUserMailToken(long idUser, String tokenUrl, LocalDateTime timestamp);

    @Select("SELECT id, username, password, last_update_time " +
            "FROM TBL_USER " +
            "WHERE username = #{username}")
    public User getUser(String username);

    @Update("UPDATE TBL_USER "                         +
            "SET password = #{password},"              +
            "   last_update_timestamp = #{timestamp} " +
            "WHERE username == #{username}")
    public void updatePassword(String username, String password, LocalDateTime timestamp);

    @Select("SELECT um.user_id, um.token_url, um.last_update_time " +
            "FROM TBL_USER_MAIL um "                                +
            "JOIN TBL_USER u "                                      +
            "ON um.user_id = u.id "                                 +
            "WHERE u.username == #{username}")
    public UserMailToken getUserMailToken(String username);

    @Update("UPDATE TBL_USER_MAIL "                    +
            "SET token_url = #{tokenUrl}, "            +
            "   last_update_timestamp = #{timestamp} " +
            "WHERE user_id == "                        +
            "   (SELECT um.user_id "                   +
            "   FROM TBL_USER_MAIL um "                +
            "   JOIN TBL_USER u "                      +
            "   ON um.user_id = u.id "                 +
            "   WHERE u.username == #{username})")
    public void updateValidTokenUrl(String username, String tokenUrl, LocalDateTime timestamp);
}
