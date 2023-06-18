package com.example.authkpo.data.model;

import com.example.authkpo.data.entity.RoleEntity;
import com.example.authkpo.data.entity.UserEntity;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    private String username;
    private Set<RoleModel> roles;

    public static UserModel of(UserEntity user) {
        UserModel userModel = new UserModel();
        userModel.setUsername(user.getUsername());

        Set<RoleModel> roleModels = new HashSet<>();
        for (final RoleEntity role : user.getRoles()) {
            roleModels.add(new RoleModel(role.getName()));
        }

        userModel.setRoles(roleModels);

        return userModel;
    }
}
