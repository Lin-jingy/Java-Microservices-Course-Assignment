package org.ljy.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import org.ljy.common.util.GenObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "User")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class User implements Serializable {
    @Id
    private String id;
    private String permission;
    @Indexed
    @Field("username")
    private String username;
    private String password;
    private String email;
    private Date createAt;
    private Date lastLogged;

    public User() {
        this.id = GenObjectId.getRandObjectId();
        this.permission = "normal";
        this.createAt = new Date();
        this.lastLogged = new Date();
    }
}
