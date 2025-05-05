package org.ljy.common.Service;

import org.ljy.common.Model.DTO.UserChangePwdDTO;
import org.ljy.common.Model.DTO.UserDTO;
import org.ljy.common.Model.DTO.UserLoginDTO;
import org.ljy.common.Model.Entity.User;
import org.ljy.common.Model.VO.UserVO;


public interface UserService {
    User createUser(UserDTO userDTO);
    User changePasswd(UserChangePwdDTO userChangePwdDTO);
    UserVO findUserById(String id);
    void deleteUser(String id, String adminId, String adminPwd);
    User login(UserLoginDTO loginDTO);
}
