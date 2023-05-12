package com.gastin.app.Gastin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class JwtDTO {
    @Getter
    @Setter
    private String token;
    @Getter @Setter
    private String bearer = "Bearer";
    @Getter @Setter
    private String email;

}
