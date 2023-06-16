package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.DTO.ScheduleDTO;
import com.gastin.app.Gastin.Model.Movement;
import com.gastin.app.Gastin.Model.Schedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    //@Query(nativeQuery = true,name = "Schedule.ScheduleList")

    @Query("SELECT p FROM Schedule p LEFT JOIN FETCH p.original_movement m WHERE m.user.id = :user AND p.active = true")
    //@EntityGraph(attributePaths = "original_movement")
    public List<Schedule> findActiveProgramacionesWithMovimientoByuser(@Param("user") Long user);
    @Query(nativeQuery = true,name="Schedule.PendingDaySchedule")
    public List<ScheduleDTO> dailySheduledList(@Param("dia")Integer day);
}
