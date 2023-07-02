package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.*;
import com.gastin.app.Gastin.Model.Movement;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

public interface MovementService {
    public MovementDTO createMovement(Long usuario_id, Long cuenta_id,Long cuentadestino_id,Long categoria_id, Long tipo_movimiento_id, MovementDTO movementDTO);
    public MovementDTO updateMovement(Long cuenta_id, Long cuentadestino_id,Long transfer, Long categoria_id, MovementDTO movementDTO, Long id);
    public void deleteMovement(Long id);
    public List<ListDateMovementsDTO> getMovementListByUser(Long usuario_id);
    public List<CategoryTotalDTO> getCategoriesAndTotalReport(Long user, Long type, Date dateFrom, Date dateTo);
    public List<CategoryTotalsByUserDTO> getCategoriesAndTotalReportByMajorUser(Long user, Date dateFrom, Date dateTo);
    public MovementDTO dtoMapping(Movement originalMovement);
}
