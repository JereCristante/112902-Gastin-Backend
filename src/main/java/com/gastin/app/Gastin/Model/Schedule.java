package com.gastin.app.Gastin.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "programaciones")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(name = "dia",nullable = false)
    @Getter @Setter
    private Integer Day;
    @Column(name = "cuotas_totales",nullable = false)
    @Getter @Setter
    private Integer total_payments;
    @Column(name = "cuotas_pagas",nullable = false)
    @Getter @Setter
    private Integer payed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimiento_original_id",nullable=false)
    private Movement original_movement;

}
