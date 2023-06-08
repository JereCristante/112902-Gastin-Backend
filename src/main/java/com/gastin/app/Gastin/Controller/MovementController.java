package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.ListDateMovementsDTO;
import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.Service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movements")
@CrossOrigin
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
    @GetMapping("/{usuarioId}/movements")
    public ResponseEntity<List<ListDateMovementsDTO>> getMovementsList(@PathVariable(value = "usuarioId")Long usuarioId){
        return new ResponseEntity<List<ListDateMovementsDTO>>(MovementService.getMovementListByUser(usuarioId), HttpStatus.OK);
    }
    @PutMapping("/{movimientoId}/{cuentaId}/{cuentaDestinoId}/{transfer}/{categoriaId}/movements")
    public ResponseEntity<MovementDTO> updateMovement(@PathVariable(value = "movimientoId")Long movimientoId,@PathVariable(value = "cuentaId")Long cuentaId,@PathVariable(value = "cuentaDestinoId")Long cuentaDestinoId,@PathVariable(value = "transfer")Long transfer,@PathVariable(value = "categoriaId")Long categoriaId, @RequestBody MovementDTO movementDTO){
        if(transfer==0){
            transfer=null;
        }
        if(cuentaDestinoId==0){
            cuentaDestinoId=null;
        }
        if(categoriaId==0){
            categoriaId=null;
        }
        return new ResponseEntity<>(MovementService.updateMovement(cuentaId,cuentaDestinoId,transfer,categoriaId,movementDTO,movimientoId), HttpStatus.CREATED);


    }
    @DeleteMapping(value = "/deleteMovement/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") long id){
        MovementService.deleteMovement(id);
        return new ResponseEntity<>("Movimiento eliminado", HttpStatus.OK);
    }
}
