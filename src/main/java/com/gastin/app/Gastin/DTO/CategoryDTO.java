package com.gastin.app.Gastin.DTO;

import com.gastin.app.Gastin.Model.MovementType;
import com.gastin.app.Gastin.Model.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

public class CategoryDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String icon;

    public CategoryDTO() {
        this.id =0L;
        this.description ="";
        this.icon="";
    }
}
