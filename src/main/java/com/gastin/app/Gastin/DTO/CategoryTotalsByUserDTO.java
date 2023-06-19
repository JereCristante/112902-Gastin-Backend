package com.gastin.app.Gastin.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CategoryTotalsByUserDTO {
    @Getter @Setter
    private List<CategoryTotalDTO> categories;
    @Getter @Setter
    private String userName;
    @Getter @Setter
    private Double total=0.00;

    public CategoryTotalsByUserDTO(String userName) {
        this.userName = userName;
    }
}
