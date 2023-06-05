package com.gastin.app.Gastin.DTO;

import com.gastin.app.Gastin.Model.MovementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ListMovementsDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Double amount;
    @Getter @Setter
    private Long category;
    @Getter @Setter
    private Long account;
    @Getter @Setter
    private String date;
    @Getter @Setter
    private Integer MovementType;
    @Getter @Setter @Transient
    private CategoryDTO categoryObj;
    @Getter @Setter @Transient
    private AccountDTO accountObj;

    public ListMovementsDTO(Long id,String description, Double amount, Long category, Long account,String date, Integer movementType) {
        this.id=id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.date = date;
        MovementType = movementType;
    }
}
