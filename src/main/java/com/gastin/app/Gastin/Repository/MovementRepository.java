package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.DTO.ListDateMovementsDTO;
import com.gastin.app.Gastin.DTO.ListMovementsDTO;
import com.gastin.app.Gastin.Model.Category;
import com.gastin.app.Gastin.Model.Movement;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement,Long> {
    @Query(nativeQuery = true,name = "Movement.Dates&Totals")
    //@Query(value="select SUBSTRING(fecha, 1, 10) as fechas, sum(monto) as total FROM gastindata.movimientos where usuario_id=20 group by fechas order by fecha desc;", nativeQuery = true)
    List<ListDateMovementsDTO> generateReport(@Param("user") Long user);

    @Query(nativeQuery = true,name = "Movement.Movements")
    public List<ListMovementsDTO> getMovementsListReport(@Param("user") Long user,@Param("date") Date date);
}
