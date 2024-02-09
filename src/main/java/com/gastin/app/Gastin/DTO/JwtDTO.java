package com.gastin.app.Gastin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@AllArgsConstructor
public class JwtDTO {
    private String token;
    private String bearer = "Bearer";
    private String email;
    private String rol;
    private Long id;
}
