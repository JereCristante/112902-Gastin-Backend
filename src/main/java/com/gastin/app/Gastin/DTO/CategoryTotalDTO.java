package com.gastin.app.Gastin.DTO;

import lombok.Getter;
import lombok.Setter;

public class CategoryTotalDTO {
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Double total;

    public CategoryTotalDTO(String description, Double total) {
        this.description = description;
        this.total = total;
    }
}
