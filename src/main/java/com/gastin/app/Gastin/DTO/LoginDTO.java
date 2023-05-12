package com.gastin.app.Gastin.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class LoginDTO {
    @Getter @Setter @NotBlank
    private String email;
    @Getter @Setter @NotBlank
    private String password;
}
