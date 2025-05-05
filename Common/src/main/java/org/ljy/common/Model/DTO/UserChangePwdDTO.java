package org.ljy.common.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserChangePwdDTO implements Serializable {
    private String username;
    private String oldPassword;
    private String newPassword;
}
