package com.gastin.app.Gastin.Model;

import com.gastin.app.Gastin.DTO.ListDateMovementsDTO;
import com.gastin.app.Gastin.DTO.ScheduleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "programaciones")
//@NamedNativeQuery(name = "Schedule.ScheduleList",
  //      query = "SELECT p.* FROM gastindata.programaciones p left join gastindata.movimientos m on p.movimiento_original_id=m.id where m.usuario_id=:user and p.activo=true",
    //    resultSetMapping = "ScheduleMapping",
      //  resultClass = Schedule.class)
//@SqlResultSetMapping(
  //      name = "ScheduleMapping",
    //    classes = {
      //          @ConstructorResult(
        //                targetClass = Schedule.class,
          //              columns = {
            //                    @ColumnResult(name = "id", type = Long.class),
              //                  @ColumnResult(name = "dia", type = Integer.class),
                //                @ColumnResult(name = "cuotas_pagas", type = Integer.class),
                  //              @ColumnResult(name = "cuotas_totales", type = Integer.class),
                    //            @ColumnResult(name = "movimiento_original_id", type = Movement.class),
                      //          @ColumnResult(name = "activo", type = Boolean.class)
                       // }
                //)
        //}
//)
@SqlResultSetMapping(
        name = "ScheduleDTOMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ScheduleDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "dia", type = Integer.class),
                                @ColumnResult(name = "cuotas_pagas", type = Integer.class),
                                @ColumnResult(name = "cuotas_totales", type = Integer.class),
                                @ColumnResult(name = "movimiento_original_id", type = Long.class),
                                @ColumnResult(name = "activo", type = Boolean.class)
                        }
                )
        }
)
@NamedNativeQuery(name = "Schedule.PendingDaySchedule",
        query = "SELECT p.id as id, p.dia as dia, p.cuotas_pagas as cuotas_pagas, p.cuotas_totales as cuotas_totales, p.movimiento_original_id as movimiento_original_id, p.activo as activo FROM gastindata.programaciones p where p.cuotas_totales > p.cuotas_pagas and p.dia=:dia and p.activo=true",
        resultSetMapping = "ScheduleDTOMapping",
        resultClass = ScheduleDTO.class)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(name = "dia",nullable = false)
    @Getter @Setter
    private Integer day;
    @Column(name = "cuotas_totales",nullable = false)
    @Getter @Setter
    private Integer total_payments;
    @Column(name = "cuotas_pagas",nullable = false)
    @Getter @Setter
    private Integer payed;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movimiento_original_id",nullable=false)
    @Getter @Setter
    private Movement original_movement;
    @Column(name = "activo",nullable = false)
    @Getter @Setter
    private Boolean active;

}
