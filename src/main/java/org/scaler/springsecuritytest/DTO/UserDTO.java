package org.scaler.springsecuritytest.DTO;

import lombok.Data;
import org.scaler.springsecuritytest.Model.Role;
import org.scaler.springsecuritytest.Model.User;

import java.util.List;

@Data
public class UserDTO {

    private String name;
    private String email;
    private List<Role> roles;

    public static UserDTO from(User u){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(u.getName());
        userDTO.setEmail(u.getEmail());
        userDTO.setRoles(u.getRoles());

        return userDTO;
    }

}
