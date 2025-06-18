package com.example.iWishTheyWereAllDead.api;

import com.example.iWishTheyWereAllDead.dto.LoginRequestDto;
import com.example.iWishTheyWereAllDead.dto.LoginResponseDto;
import com.example.iWishTheyWereAllDead.dto.SignUpRequestDto;
import com.example.iWishTheyWereAllDead.entity.UserEntity;
import com.example.iWishTheyWereAllDead.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "회원", description = "회원가입 및 로그인 API")
@Slf4j
@RestController
class UserController {
    private final UserService userService;
    @Autowired
    public UserController (UserService restApiService) {
        this.userService = restApiService;
    }

    // 확인차 조회
    @Operation(summary = "조회", description = "확인차 조회기능입니다")
    @GetMapping("/user")
    public ResponseEntity<?> readAll() {
        List<UserEntity> readAll = userService.readAll();
        return (readAll != null)?
                ResponseEntity.status(HttpStatus.OK).body(readAll):
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // 회원가입
    @Operation(summary = "회원가입", description = "회원가입 기능입니다")
    @PostMapping("/user/signup")
    public ResponseEntity<UserEntity> create(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserEntity created = userService.create(signUpRequestDto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 로그인
    @Operation(summary = "로그인", description = "로그인 기능입니다.")
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