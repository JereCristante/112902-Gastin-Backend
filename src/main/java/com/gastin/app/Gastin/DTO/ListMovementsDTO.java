package com.gastin.app.Gastin.DTO;

import com.gastin.app.Gastin.Model.MovementType;
import jakarta.persistence.*;
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
    private Long category;
    @Getter @Setter
    private String account;
    @Getter @Setter
    private Integer MovementType;
    @Getter @Setter @Transient
    private CategoryDTO categoryObj;

    public ListMovementsDTO(String description, Double amount, Long category, String account, Integer movementType) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.account = account;
        MovementType = movementType;
    }
}
