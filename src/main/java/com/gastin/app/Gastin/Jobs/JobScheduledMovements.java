package com.gastin.app.Gastin.Jobs;

import com.gastin.app.Gastin.Service.ScheduleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

public class JobScheduledMovements implements Job {

   // @Autowired
   private ScheduleService ScheduleService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        String dayOfMonthStr = String.valueOf(dayOfMonth);
        System.out.println("Ejecutando job de Movimientos Programados...");
        Integer result = ScheduleService.dailyScheduleJob(dayOfMonth);
        System.out.println("Movimientos registrados: " + result);

    }
}
