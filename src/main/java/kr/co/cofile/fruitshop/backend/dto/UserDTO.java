package kr.co.cofile.fruitshop.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled = true;
    private List<RoleDTO> roles;
}
