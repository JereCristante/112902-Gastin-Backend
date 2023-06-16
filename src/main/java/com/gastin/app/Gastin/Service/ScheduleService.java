package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.ListDateMovementsDTO;
import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.DTO.ScheduleDTO;
import com.gastin.app.Gastin.Model.Schedule;

import java.util.List;

public interface ScheduleService {

    public ScheduleDTO createSchedule(ScheduleDTO ScheduleDTO);
    public ScheduleDTO updateSchedule(ScheduleDTO ScheduleDTO, Long id);
    public void deleteSchedule(Long id);
    public List<ScheduleDTO> getScheduleListByUser(Long usuario_id);
    public Integer dailyScheduleJob(Integer day);
}
