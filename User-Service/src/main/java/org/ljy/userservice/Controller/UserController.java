package org.ljy.userservice.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.ljy.common.Model.DTO.UserChangePwdDTO;
import org.ljy.common.Model.DTO.UserDTO;
import org.ljy.common.Model.DTO.UserLoginDTO;
import org.ljy.common.Model.Entity.User;
import org.ljy.common.Model.VO.UserVO;
import org.ljy.common.Service.UserService;
import org.ljy.common.Util.JWT.JWTUtils;
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
