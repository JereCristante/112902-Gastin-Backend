package com.gastin.app.Gastin.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "movimientos")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Column(name = "descripcion",nullable = false)
    @Getter @Setter
    private String description;
    @Column(name = "monto",nullable = false)
    @Getter @Setter
    private Double amount;
    @Column(name = "fecha",nullable = false)
    @Getter @Setter
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id",nullable=false)
    @Getter @Setter
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id",nullable=false)
    @Getter @Setter
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuentadestino_id")
    @Getter @Setter
    private Account destinationAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @Getter @Setter
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_movimiento_id")
    @Getter @Setter
    private MovementType movementType;

    @Column(name = "activo")
    @Getter @Setter
    private Boolean active;



}
