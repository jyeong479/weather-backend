package com.example.iWishTheyWereAllDead.service;
import com.example.iWishTheyWereAllDead.dto.LoginRequestDto;
import com.example.iWishTheyWereAllDead.dto.LoginResponseDto;
import com.example.iWishTheyWereAllDead.dto.SignUpRequestDto;
import com.example.iWishTheyWereAllDead.entity.UserEntity;
import com.example.iWishTheyWereAllDead.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> readAll() {
        return userRepository.findAll();
    }

    public UserEntity readOne(String email) {
        return userRepository.findById(email).orElse(null);
    }

    public UserEntity create(SignUpRequestDto dto) {
        UserEntity restApi = dto.toEntity();
        if (userRepository.existsById(dto.getEmail())) {
            return null;
        }
        return userRepository.save(restApi);
    }

    public UserEntity update(String email, LoginRequestDto dto) {
        UserEntity restApi = dto.toEntity();
        UserEntity target = userRepository.findById(email).orElse(null);
        if (target == null) {
            return null;
        }
        target.patch(restApi);
        UserEntity updated = userRepository.save(target);
        return updated;
    }

    public UserEntity delete(String email) {
        UserEntity target = userRepository.findById(email).orElse(null);
        if(target == null) {
            return null;
        }

        userRepository.delete(target);
        return target;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        if(!userRepository.existsByEmail(loginRequestDto.getEmail())) {
            return null;
        } else {
            UserEntity restApi = userRepository.findByEmail(loginRequestDto.getEmail());
            if(loginRequestDto.getPassword().equals(restApi.getPassword())){
                return new LoginResponseDto(loginRequestDto.getEmail());
            } else {
                return null;
            }
        }
    }
}