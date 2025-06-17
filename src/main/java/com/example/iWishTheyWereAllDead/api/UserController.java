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

    //GET
    @GetMapping("/user")
    public ResponseEntity<?> readAll() {
        List<UserEntity> readAll = userService.readAll();
        return (readAll != null)?
                ResponseEntity.status(HttpStatus.OK).body(readAll):
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> readOne(@PathVariable String email) {
        UserEntity readOne = userService.readOne(email);
        return (readOne != null)?
                ResponseEntity.status(HttpStatus.OK).body(readOne):
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //POST
    @PostMapping("/user")
    public ResponseEntity<UserEntity> create(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserEntity created = userService.create(signUpRequestDto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //UPDATE
    @PatchMapping("/user/{email}")
    public ResponseEntity<UserEntity> update(@PathVariable String email, @RequestBody LoginRequestDto dto) { //오류 직접 해결해 보십시오(내일까지)
        UserEntity updated = userService.update(email, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/user/{email}")
    public ResponseEntity<UserEntity> delete(@PathVariable String email) {
        UserEntity deleted = userService.delete(email);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info(loginRequestDto.getEmail()); //postman으로 login 요청해보시고 객체에 매핑되는 필드값 출력 한번 해보세요
        log.info(loginRequestDto.getPassword());
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        if(loginResponseDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        }
    }

}