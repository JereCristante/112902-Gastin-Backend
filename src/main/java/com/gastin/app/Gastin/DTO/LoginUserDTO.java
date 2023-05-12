package com.gastin.app.Gastin.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


public class LoginUserDTO {
    @Email
    @Getter
    @Setter
    private String email;
    @NotBlank
    @Getter @Setter
    private String password;
}
