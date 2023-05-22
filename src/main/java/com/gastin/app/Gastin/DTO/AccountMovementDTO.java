package com.gastin.app.Gastin.DTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;


public class AccountMovementDTO {
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Double amount;

    public AccountMovementDTO(String description, Double amount) {
        this.description = description;
        this.amount = amount;
    }
}
