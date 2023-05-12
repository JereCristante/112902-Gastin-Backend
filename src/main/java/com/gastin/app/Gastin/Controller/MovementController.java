package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.Service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movements")
public class MovementController {
    @Autowired
    private MovementService MovementService;
    @PostMapping("/{usuarioId}/{cuentaId}/{cuentaDestinoId}/{categoriaId}/{tipoMovimientoId}/movements")
    public ResponseEntity<MovementDTO> saveMovement(@PathVariable(value = "usuarioId")Long usuarioId,@PathVariable(value = "cuentaId")Long cuentaId,@PathVariable(value = "cuentaDestinoId")Long cuentaDestinoId,@PathVariable(value = "categoriaId")Long categoriaId,@PathVariable(value = "tipoMovimientoId")Long tipoMovimientoId, @RequestBody MovementDTO movementDTO){
        if(cuentaDestinoId==0){
            cuentaDestinoId=null;
        }
        if(categoriaId==0){
            categoriaId=null;
        }
        return new ResponseEntity<>(MovementService.createMovement(usuarioId,cuentaId,cuentaDestinoId,categoriaId,tipoMovimientoId,movementDTO), HttpStatus.CREATED);


    }
}
