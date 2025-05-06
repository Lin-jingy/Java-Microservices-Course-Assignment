package org.ljy.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.ljy.common.model.dto.UserChangePwdDTO;
import org.ljy.common.model.dto.UserDTO;
import org.ljy.common.model.dto.UserLoginDTO;
import org.ljy.common.model.entity.User;
import org.ljy.common.model.vo.UserVO;
import org.ljy.common.service.UserService;
import org.ljy.userservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@DubboService
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        if (!"normal".equals(userDTO.getPermission()) && !"admin".equals(userDTO.getPermission())) {
            throw new RuntimeException("Permission denied");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return userRepository.save(user);
    }

    public User changePasswd(UserChangePwdDTO userChangePwdDTO) {
        User user = userRepository.findByUsername(userChangePwdDTO.getUsername()).orElseThrow(() ->new RuntimeException("user is not exist"));
        if (!user.getPassword().equals(userChangePwdDTO.getNewPassword())) {
            throw new RuntimeException("old password is wrong");
        }
        user.setPassword(userChangePwdDTO.getNewPassword());
        return userRepository.save(user);
    }

    public UserVO findUserById(String id) {
        User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("user is not exist"));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    public void deleteUser(String id, String adminId, String adminPwd){
        User adminUser = userRepository.findById(adminId).orElseThrow(() ->new RuntimeException("admin is not exist"));
        if(!"admin".equals(adminUser.getPermission())) throw new RuntimeException("Permission denied");
        userRepository.findById(id).orElseThrow(() ->new RuntimeException("user is not exist"));
        userRepository.deleteById(id);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->new RuntimeException("user is not exist"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("password is wrong");
        }
        return user;
    }

    public User login(UserLoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() ->new RuntimeException("user is not exist"));
        if(!user.getPassword().equals(loginDTO.getPassword())) throw new RuntimeException("password is wrong");
        return user;
    }
}
