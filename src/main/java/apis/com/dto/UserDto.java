package apis.com.dto;

import apis.com.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto { 
    private String fullName; 
    private String email;
    private Role role;; 
    private String password;
}