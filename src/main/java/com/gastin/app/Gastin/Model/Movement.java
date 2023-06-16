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
        query = "select SUBSTRING(fecha, 1, 10) as date, sum(monto) as total FROM gastindata.movimientos where usuario_id=:user and activo=true group by date order by fecha desc;",
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
        query = "SELECT m.id,m.descripcion,m.monto,m.categoria_id as categoria,m.cuenta_id as cuenta,m.fecha as fecha,m.tipo_movimiento_id as tipo_mov,m.transfer as transfer FROM gastindata.movimientos m where m.usuario_id=:user and SUBSTRING(m.fecha, 1, 10)=SUBSTRING(:date, 1, 10) and m.activo=true order by m.fecha desc;",
        resultSetMapping = "ListMovementsMapping",
        resultClass = ListMovementsDTO.class)
@SqlResultSetMapping(
        name = "ListMovementsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ListMovementsDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "descripcion", type = String.class),
                                @ColumnResult(name = "monto", type = Double.class),
                                @ColumnResult(name = "categoria", type = Long.class),
                                @ColumnResult(name = "cuenta", type = Long.class),
                                @ColumnResult(name = "fecha", type = String.class),
                                @ColumnResult(name = "tipo_mov", type = Integer.class),
                                @ColumnResult(name = "transfer", type = Long.class)
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id",nullable=false)
    @Getter @Setter
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id",nullable=false)
    @Getter @Setter
    private Account account;
    @JoinColumn(name = "nro_transferencia")
    @Getter @Setter
    private Long transfer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    @Getter @Setter
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_movimiento_id")
    @Getter @Setter
    private MovementType movementType;

    @Column(name = "activo")
    @Getter @Setter
    private Boolean active;

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", user=" + user +
                ", account=" + account +
                ", transfer=" + transfer +
                ", category=" + category +
                ", movementType=" + movementType +
                ", active=" + active +
                '}';
    }
}
