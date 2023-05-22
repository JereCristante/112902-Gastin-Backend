package com.gastin.app.Gastin.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ListDateMovementsDTO {

    @Getter @Setter
    private String date;
    @Getter @Setter
    private Double total;
    @Getter
    @Setter
    @Transient
    private List<ListMovementsDTO> movements;
    public ListDateMovementsDTO() {
        this.movements = new ArrayList<>();
        this.date = "";
        this.total = 0.00;
    }

}
