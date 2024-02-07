package com.gastin.app.Gastin.Jobs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class ScheduleConfig{

}
/*
@Component
public class JobScheduledMovements extends QuartzJobBean {
    @Autowired
    private ScheduleService scheduleService;
    //@Autowired
    //public void setMyService(ScheduleService scheduleService) {
     //   this.scheduleService=scheduleService;
    //}
    //private final ScheduleService scheduleService;
    //public JobScheduledMovements() {
    //}
    //@Autowired
    //public JobScheduledMovements() {
     //   this.scheduleService = new ScheduleServiceImpl();
    //}
    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException{
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
       try {
           Calendar cal = Calendar.getInstance();
           Integer dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

           System.out.println("Ejecutando job de Movimientos Programados...");
           if (scheduleService == null) {
               System.out.println("¡scheduleService es nulo! Verifica la inyección de dependencias.");
               return; // Sal del método si el servicio es nulo
               //this.setMyService(new ScheduleService());
           }
           Integer result = scheduleService.dailyScheduleJob(dayOfMonth);
           System.out.println("Movimientos registrados:" + result);

       } catch (Exception e ){
           System.err.println("Error al ejecutar el trabajo JobScheduledMovements: " + e.getMessage());
           e.printStackTrace();
           throw new JobExecutionException(e);
       }

    }
*/
//}