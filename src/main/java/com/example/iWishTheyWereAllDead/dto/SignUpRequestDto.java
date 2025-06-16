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
    private String name;
    private String phone;

    public UserEntity toEntity(){
        return new UserEntity(email, password, name, phone);
    }

}