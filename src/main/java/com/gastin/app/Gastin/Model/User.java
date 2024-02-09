package com.gastin.app.Gastin.Model;

import com.gastin.app.Gastin.DTO.TransactionsMetricDTO;
import com.gastin.app.Gastin.DTO.UsersCreatedDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@NamedNativeQuery(name = "User.UserCreatedMetrics",
        query = "SELECT count(*) as amount, CONCAT(MONTH(fecha_alta),\"-\",YEAR(fecha_alta)) as date " +
                "FROM gastindata.USUARIOS " +
                "group by MONTH(fecha_alta) " +
                "order by fecha_alta asc " +
                "limit 8",
        resultSetMapping = "UserCreatedMetricsMapping",
        resultClass = UsersCreatedDTO.class)
@SqlResultSetMapping(
        name = "UserCreatedMetricsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = UsersCreatedDTO.class,
                        columns = {
                                @ColumnResult(name = "amount", type = Integer.class),
                                @ColumnResult(name = "date", type = String.class)
                        }
                )
        }
)
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id")
    @Getter @Setter
    private Role role;
    @Column(name = "fecha_alta")
    @Getter @Setter
    private Date creationDate;
}
