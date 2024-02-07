package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.ListDateMovementsDTO;
import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.DTO.ScheduleDTO;
import com.gastin.app.Gastin.Model.Schedule;
import com.gastin.app.Gastin.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/schedules")
@CrossOrigin
public class ScheduleController {
    @Autowired
    private ScheduleService ScheduleService;
    @PostMapping("/new")
    public ResponseEntity<ScheduleDTO> saveSchedule(@RequestBody ScheduleDTO scheduleDTO){
        return new ResponseEntity<ScheduleDTO>(ScheduleService.createSchedule(scheduleDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleList(@PathVariable(value = "usuarioId")Long usuarioId){
        return new ResponseEntity<List<ScheduleDTO>>(ScheduleService.getScheduleListByUser(usuarioId), HttpStatus.OK);
    }
    @PutMapping("/{programacionId}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable(value = "programacionId")Long programacionId, @RequestBody ScheduleDTO scheduleDTO){
        return new ResponseEntity<ScheduleDTO>(ScheduleService.updateSchedule(scheduleDTO,programacionId), HttpStatus.CREATED);

    }
    @DeleteMapping(value = "/deleteSchedule/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable(name = "id") long id){
        ScheduleService.deleteSchedule(id);
        return new ResponseEntity<>("Programacion eliminada", HttpStatus.OK);
    }
    @GetMapping("/RunJobManually")
    public ResponseEntity<String> runDailyScheduleJob(){
        Integer result = ScheduleService.dailyScheduleJob();
        return new ResponseEntity<>("Programaciones guardadas: "+ result, HttpStatus.OK);
    }
}
