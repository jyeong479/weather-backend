package com.example.iWishTheyWereAllDead.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponseDto {
    private String email;
    private String token;
}
