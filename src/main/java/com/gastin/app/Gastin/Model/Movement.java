package com.gastin.app.Gastin.Model;

import com.gastin.app.Gastin.DTO.ListDateMovementsDTO;
import com.gastin.app.Gastin.DTO.ListMovementsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "movimientos")
@NamedNativeQuery(name = "Movement.Dates&Totals",
        query = "select SUBSTRING(fecha, 1, 10) as date, sum(monto) as total FROM gastindata.movimientos where usuario_id=:user group by date order by fecha desc;",
        resultSetMapping = "ListDateMovementsMapping",
        resultClass = ListDateMovementsDTO.class)
@SqlResultSetMapping(
        name = "ListDateMovementsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ListDateMovementsDTO.class,
                        columns = {
                                @ColumnResult(name = "date", type = String.class),
                                @ColumnResult(name = "total", type = Double.class)
                        }
                )
        }
)
@NamedNativeQuery(name = "Movement.Movements",
        query = "SELECT m.descripcion,m.monto,m.categoria_id as categoria,cu.descripcion as cuenta,m.tipo_movimiento_id as tipo_mov FROM gastindata.movimientos m join gastindata.cuentas cu on m.cuenta_id = cu.id where m.usuario_id=:user and SUBSTRING(m.fecha, 1, 10)=SUBSTRING(:date, 1, 10) and m.activo=true order by m.fecha desc;",
        resultSetMapping = "ListMovementsMapping",
        resultClass = ListMovementsDTO.class)
@SqlResultSetMapping(
        name = "ListMovementsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ListMovementsDTO.class,
                        columns = {
                                @ColumnResult(name = "descripcion", type = String.class),
                                @ColumnResult(name = "monto", type = Double.class),
                                @ColumnResult(name = "categoria", type = Long.class),
                                @ColumnResult(name = "cuenta", type = String.class),
                                @ColumnResult(name = "tipo_mov", type = Integer.class)
                        }
                )
        }
)
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
