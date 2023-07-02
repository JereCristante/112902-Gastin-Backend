package com.gastin.app.Gastin.DTO;

import com.gastin.app.Gastin.Model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ReportDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String observation;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String screen;
    @Getter @Setter
    private String state;
    @Getter @Setter
    private Date date;
    @Getter @Setter
    private Long user;
}
