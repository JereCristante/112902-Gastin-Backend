package com.gastin.app.Gastin.DTO;

import com.gastin.app.Gastin.Model.Account;
import com.gastin.app.Gastin.Model.Category;
import com.gastin.app.Gastin.Model.MovementType;
import com.gastin.app.Gastin.Model.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class MovementDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Double amount;
    @Getter @Setter
    private Date date;
    @Getter @Setter
    private Boolean active;
}
