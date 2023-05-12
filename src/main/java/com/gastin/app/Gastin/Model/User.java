package com.gastin.app.Gastin.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Column(name = "alias",nullable = false)
    @Getter @Setter
    private String alias;
    @Column(name = "email",nullable = false)
    @Getter @Setter
    private String email;
    @Column(name = "password",nullable = false)
    @Getter @Setter
    private String password;
    @Column(name = "idUsuarioMayor")
    @Getter @Setter
    private Long idUpperUser;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Movement> movements = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    @Column(name = "activo")
    @Getter @Setter
    private Boolean active;



}
