package com.example.authkpo.data.model;

import com.example.authkpo.data.entity.RoleEntity;
import java.util.LinkedList;
import java.util.List;
import javax.management.relation.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {

    private String name;

    public static RoleModel of(RoleEntity role) {
        RoleModel roleModel = new RoleModel();
        roleModel.setName(role.getName());

        return roleModel;
    }

    public static List<RoleModel> of(final Iterable<RoleEntity> roles) {
        List<RoleModel> roleModels = new LinkedList<>();

        for (final RoleEntity role : roles) {
            roleModels.add(new RoleModel(role.getName()));
        }

        return roleModels;
    }
}
