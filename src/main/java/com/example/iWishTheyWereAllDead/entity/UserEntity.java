package com.example.iWishTheyWereAllDead.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Getter
@Table(name="회원")
public class UserEntity {
    @Id
    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}