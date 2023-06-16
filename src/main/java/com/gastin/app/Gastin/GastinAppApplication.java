package com.gastin.app.Gastin;

import com.gastin.app.Gastin.Jobs.JobScheduledMovements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class GastinAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastinAppApplication.class, args);

		try {
			// Configuración del job
			JobDetail job = JobBuilder.newJob(JobScheduledMovements.class)
					.withIdentity("myJob", "group1")
					.build();

			// Configuración del trigger (ejecución diaria a las 9:00 AM)
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("myTrigger", "group1")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(20, 33))
					.build();

			// Creación y programación del scheduler
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
