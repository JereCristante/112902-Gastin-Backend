package com.gastin.app.Gastin.Model;

import com.gastin.app.Gastin.DTO.AccountMovementDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuentas")
@SqlResultSetMapping(
        name = "acountmovMapping",
        classes = {
                @ConstructorResult(
                        targetClass = AccountMovementDTO.class,
                        columns = {
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "amount", type = Double.class)
                        }
                )
        }
)
@NamedNativeQuery(name = "AccountMovements",query = "SELECT descripcion as description,monto as amount FROM gastindata.movimientos where cuenta_id=:account and activo=true order by fecha desc LIMIT 3;", resultSetMapping = "acountmovMapping")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(name = "descripcion",nullable = false)
    @Getter @Setter
    private String description;
    @Column(name = "saldo",nullable = false)
    @Getter @Setter
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id",nullable=false)
    @Getter @Setter
    private User user;
    @Column(name = "activo")
    @Getter @Setter
    private Boolean active;
}
