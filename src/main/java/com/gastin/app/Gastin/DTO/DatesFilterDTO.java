package com.gastin.app.Gastin.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class DatesFilterDTO {
    @Getter @Setter
    private Date dateFrom;
    @Getter @Setter
    private Date dateTo;
}
