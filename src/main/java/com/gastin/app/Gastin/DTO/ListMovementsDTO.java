package com.gastin.app.Gastin.DTO;

import com.gastin.app.Gastin.Model.MovementType;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;

public class ListMovementsDTO {
    //@Getter
    //@Setter
    //private Long id;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Double amount;
    @Getter @Setter
    private String category;
    @Getter @Setter
    private String account;
    @Getter @Setter
    private Integer MovementType;

    public ListMovementsDTO(String description, Double amount, String category, String account, Integer movementType) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.account = account;
        MovementType = movementType;
    }
}
