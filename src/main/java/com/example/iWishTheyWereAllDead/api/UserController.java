package com.example.iWishTheyWereAllDead.api;

import com.example.iWishTheyWereAllDead.dto.LoginRequestDto;
import com.example.iWishTheyWereAllDead.dto.LoginResponseDto;
import com.example.iWishTheyWereAllDead.dto.SignUpRequestDto;
import com.example.iWishTheyWereAllDead.entity.UserEntity;
import com.example.iWishTheyWereAllDead.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
class UserController {
    private final UserService userService;
    @Autowired
    public UserController (UserService restApiService) {
        this.userService = restApiService;
    }

    // 확인차 조회
    @GetMapping("/user")
    public ResponseEntity<?> readAll() {
        List<UserEntity> readAll = userService.readAll();
        return (readAll != null)?
                ResponseEntity.status(HttpStatus.OK).body(readAll):
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<UserEntity> create(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserEntity created = userService.create(signUpRequestDto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info(loginRequestDto.getEmail());
        log.info(loginRequestDto.getPassword());
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        if(loginResponseDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        }
    }

}