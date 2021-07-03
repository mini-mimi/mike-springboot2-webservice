package com.mike.springboot2.webservice.config.auth.dto;

import com.mike.springboot2.webservice.domain.users.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String users_name;
    private String users_email;
    private String users_id;
    private String users_picture;

    public SessionUser(Users users) {
        this.users_name = users.getUsers_name();
        this.users_email = users.getUsers_email();
        this.users_id = users.getUsers_id();
        this.users_picture = users.getUsers_picture();
    }
}
