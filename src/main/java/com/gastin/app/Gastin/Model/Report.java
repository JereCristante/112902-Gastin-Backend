package com.gastin.app.Gastin.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reportes")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(name = "observacion",nullable = false)
    @Getter @Setter
    private String observation;
    @Column(name = "tipo",nullable = false)
    @Getter @Setter
    private String type;
    @Column(name = "pantalla",nullable = false)
    @Getter @Setter
    private String screen;
    @Column(name = "estado",nullable = false)
    @Getter @Setter
    private String state;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id",nullable=false)
    @Getter @Setter
    private User user;
    @Column(name = "fecha",nullable = false)
    @Getter @Setter
    private Date date;
}
