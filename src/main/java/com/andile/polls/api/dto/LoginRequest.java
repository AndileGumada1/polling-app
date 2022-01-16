package com.andile.polls.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
/**
 * Class is used as a request for logging in
 * */
@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String userNameOrEmail;
    @NotBlank
    private String password;
}
