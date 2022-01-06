package com.andile.polls.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String userNameOrEmail;
    @NotBlank
    private String password;
}
