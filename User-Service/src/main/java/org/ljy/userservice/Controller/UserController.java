package org.ljy.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.ljy.common.model.dto.UserChangePwdDTO;
import org.ljy.common.model.dto.UserDTO;
import org.ljy.common.model.dto.UserLoginDTO;
import org.ljy.common.model.entity.User;
import org.ljy.common.model.vo.UserVO;
import org.ljy.common.service.UserService;
import org.ljy.common.util.jwt.JWTUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @DubboReference
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping("/changePasswd")
    public User changePasswd(@RequestBody UserChangePwdDTO userChangePwdDTO) {
        return userService.changePasswd(userChangePwdDTO);
    }

    @GetMapping("/get")
    public UserVO findUserById(@RequestBody String id) {
        return userService.findUserById(id);
    }


    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO loginDTO) {
        log.warn("loginDTO: {}", loginDTO.getUsername());
        return JWTUtils.getToken(userService.login(loginDTO));
    }
}
