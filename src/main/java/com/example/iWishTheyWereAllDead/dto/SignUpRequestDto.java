package com.example.iWishTheyWereAllDead.dto;


import com.example.iWishTheyWereAllDead.entity.UserEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;

    public UserEntity toEntity(){
        return new UserEntity(email, password);
    }

}