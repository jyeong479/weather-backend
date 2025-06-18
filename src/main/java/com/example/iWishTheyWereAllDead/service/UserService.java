package com.example.iWishTheyWereAllDead.service;
import com.example.iWishTheyWereAllDead.dto.LoginRequestDto;
import com.example.iWishTheyWereAllDead.dto.LoginResponseDto;
import com.example.iWishTheyWereAllDead.dto.SignUpRequestDto;
import com.example.iWishTheyWereAllDead.entity.UserEntity;
import com.example.iWishTheyWereAllDead.jwt.JwtTokenProvider;
import com.example.iWishTheyWereAllDead.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public List<UserEntity> readAll() {
        return userRepository.findAll();
    }


    public UserEntity create(SignUpRequestDto dto) {
        if (userRepository.existsById(dto.getEmail())) {
            System.out.println("중복된 이메일: " + dto.getEmail());
            return null;
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        UserEntity user = new UserEntity(dto.getEmail(), encodedPassword);
        return userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        UserEntity userEntity = userRepository.findByEmail(loginRequestDto.getEmail());
        if (userEntity == null || !passwordEncoder.matches(loginRequestDto. getPassword(), userEntity.getPassword())){
            return null;
        }

        String token = jwtTokenProvider.createToken(userEntity.getEmail());
        log.info("발급된 토큰: {}", token);
        return new LoginResponseDto(userEntity.getEmail(), token);
    }
}