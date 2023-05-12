package com.gastin.app.Gastin.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class AccountDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Double balance;
    @Getter @Setter
    private Boolean active;
}
