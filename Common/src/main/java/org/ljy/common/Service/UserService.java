package org.ljy.common.service;

import org.ljy.common.model.dto.UserChangePwdDTO;
import org.ljy.common.model.dto.UserDTO;
import org.ljy.common.model.dto.UserLoginDTO;
import org.ljy.common.model.entity.User;
import org.ljy.common.model.vo.UserVO;

public interface UserService {
    User createUser(UserDTO userDTO);
    User changePasswd(UserChangePwdDTO userChangePwdDTO);
    UserVO findUserById(String id);
    void deleteUser(String id, String adminId, String adminPwd);
    User login(UserLoginDTO loginDTO);
}
