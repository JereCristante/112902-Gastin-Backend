package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.DTO.UserDTO;
import com.gastin.app.Gastin.Model.Movement;

import java.util.List;

public interface MovementService {
    public MovementDTO createMovement(Long usuario_id, Long cuenta_id,Long cuentadestino_id,Long categoria_id, Long tipo_movimiento_id, MovementDTO movementDTO);
    public MovementDTO updateMovement(Long cuenta_id, Long cuentadestino_id, Long categoria_id, MovementDTO movementDTO, Long id);
    public void deleteMovement(Long id);
}
