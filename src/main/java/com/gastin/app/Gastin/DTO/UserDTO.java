package com.gastin.app.Gastin.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    @Getter
    @Setter
    private Long id;
    @Getter @Setter
    private String alias;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private Long idUpperUser;
    @Getter @Setter
    private Boolean active;
}
