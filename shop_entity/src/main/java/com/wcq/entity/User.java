package com.wcq.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User extends BaseEntity{

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
}
