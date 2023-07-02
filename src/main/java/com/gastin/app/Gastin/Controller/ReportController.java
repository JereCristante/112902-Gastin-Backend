package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.DTO.ReportDTO;
import com.gastin.app.Gastin.Service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reports")
@CrossOrigin
public class ReportController {
    @Autowired
    private com.gastin.app.Gastin.Service.ReportService ReportService;
    @PostMapping("/{usuarioId}")
    public ResponseEntity<ReportDTO> saveMovement(@PathVariable(value = "usuarioId")Long usuarioId, @RequestBody ReportDTO reportDTO){

        return new ResponseEntity<>(ReportService.createReport(usuarioId,reportDTO), HttpStatus.CREATED);
    }
}
