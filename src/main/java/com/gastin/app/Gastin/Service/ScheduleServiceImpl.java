package com.gastin.app.Gastin.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.gastin.app.Gastin.DTO.ListDateMovementsDTO;
import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.DTO.ScheduleDTO;
import com.gastin.app.Gastin.Exceptions.ResourceNotFoundException;
import com.gastin.app.Gastin.Model.Movement;
import com.gastin.app.Gastin.Model.Schedule;
import com.gastin.app.Gastin.Repository.MovementRepository;
import com.gastin.app.Gastin.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private MovementService movementService;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Movement movement = movementRepository.findById(scheduleDTO.getOriginal_movement_id()).orElseThrow(()-> new ResourceNotFoundException("movimiento","id",scheduleDTO.getOriginal_movement_id()));
        Schedule newSchedule = entityMapping(scheduleDTO);
        newSchedule.setOriginal_movement(movement);
        return dtoMapping(scheduleRepository.save(newSchedule));
    }

    @Override
    public ScheduleDTO updateSchedule(ScheduleDTO ScheduleDTO, Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("programacion","id",id));
        schedule.setDay(ScheduleDTO.getDay());
        return dtoMapping(scheduleRepository.save(schedule));
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("programacion","id",id));
        schedule.setActive(false);
        scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleDTO> getScheduleListByUser(Long usuario_id) {
        List<Schedule> schedule = scheduleRepository.findActiveProgramacionesWithMovimientoByuser(usuario_id);
        //System.out.println(schedule.get(0).getOriginal_movement().toString());
        List<ScheduleDTO> scheduleDTOS = schedule.stream().map((Schedule element) -> dtoMapping(element)).collect(Collectors.toList());
        return scheduleDTOS;
    }

    @Override @Scheduled(cron = "0 0 0 * * ?")
    public Integer dailyScheduleJob() {
        //consulta a la tabla programaciones donde el dia sea igual al del parametro y las cuotas pagas sean menores a las totales
        Calendar cal = Calendar.getInstance();
        Integer day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("Job Movimientos Programados corriendo para el dia: "+day);
        Integer registers = 0;
        List<ScheduleDTO> todaySchedules = scheduleRepository.dailySheduledList(day);
        //por todos los schedules que traiga la consulta, obtenemos su movimiento original, le sacamos los parametros variables desc/fecha+1mes/monto/cuenta/tipo mov/usuario
        //sumamos un registro por cada uno y listo
        for (ScheduleDTO schedule:todaySchedules) {
            Movement originalMovement = movementRepository.findById(schedule.getOriginal_movement_id()).orElseThrow(()-> new ResourceNotFoundException("movimiento","id",schedule.getOriginal_movement_id()));
            MovementDTO newPayment = new MovementDTO();
            newPayment.setDescription(originalMovement.getDescription() + " ("+(schedule.getPayed()+1)+"/"+schedule.getTotal_payments()+")");
            if(originalMovement.getAmount()<0){
                newPayment.setAmount(originalMovement.getAmount()*-1);
            }else {
                newPayment.setAmount(originalMovement.getAmount());
            }
            newPayment.setDate(new Date());
            newPayment.setActive(true);
            movementService.createMovement(originalMovement.getUser().getId(),originalMovement.getAccount().getId(),
                    null,originalMovement.getCategory().getId(),originalMovement.getMovementType().getId(), newPayment);
            schedule.setPayed(schedule.getPayed()+1);
            if(schedule.getPayed()>=schedule.getTotal_payments()){
                schedule.setActive(false);
            }
            Schedule updatedSchedule = entityMapping(schedule);
            updatedSchedule.setOriginal_movement(originalMovement);
            scheduleRepository.save(updatedSchedule);
            registers++;
        }
        System.out.println("Job Movimientos Programados registro: "+ registers + " movimientos");
        return registers;
    }

    private ScheduleDTO dtoMapping(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDay(schedule.getDay());
        scheduleDTO.setPayed(schedule.getPayed());
        scheduleDTO.setTotal_payments(schedule.getTotal_payments());
        scheduleDTO.setActive(schedule.getActive());
        scheduleDTO.setOriginal_movement_id(schedule.getOriginal_movement().getId());
        return scheduleDTO;
    }
    private Schedule entityMapping(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDay(scheduleDTO.getDay());
        schedule.setPayed(scheduleDTO.getPayed());
        schedule.setTotal_payments(scheduleDTO.getTotal_payments());
        schedule.setActive(scheduleDTO.getActive());
        return schedule;
    }
}
