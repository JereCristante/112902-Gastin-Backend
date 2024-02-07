package com.gastin.app.Gastin.DTO;

import com.gastin.app.Gastin.Model.Movement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public class ScheduleDTO {
    @Getter
    @Setter
    private Long id;
    @Getter @Setter
    private Integer day;
    @Getter @Setter
    private Integer payed;
    @Getter @Setter
    private Integer total_payments;
    @Getter @Setter
    private Long original_movement_id;
    @Getter @Setter
    private Boolean active;

    public ScheduleDTO() {
        this.id=0L;
        this.day=0;
        this.payed=0;
        this.total_payments=0;
        this.original_movement_id=null;
        this.active=true;

    }

    public ScheduleDTO(Long id, Integer day, Integer payed, Integer total_payments, Long original_movement_id, Boolean active) {
        this.id = id;
        this.day = day;
        this.payed = payed;
        this.total_payments = total_payments;
        this.original_movement_id = original_movement_id;
        this.active = active;
    }
}
