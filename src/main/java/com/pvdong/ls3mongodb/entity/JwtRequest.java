package com.pvdong.ls3mongodb.entity;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
