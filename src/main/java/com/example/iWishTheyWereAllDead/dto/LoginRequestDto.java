package com.example.iWishTheyWereAllDead.dto;


import com.example.iWishTheyWereAllDead.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class LoginRequestDto {

    @Schema(description = "email", example = "abcd@gmail.com")
    private String email;
    @Schema(description = "password", example = "abcd123!")
    private String password;

    public UserEntity toEntity(){
        return new UserEntity(email, password);
    }
}