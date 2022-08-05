package br.com.agroproducer.service;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import br.com.agroproducer.job.DroneSimulacaoJob;

@Service
public class DroneService {
	
	public void criarDroneSimulacao(Integer droneId) {
		try {
			JobDetail job = JobBuilder.newJob(DroneSimulacaoJob.class)
										.withIdentity("droneId" + droneId)
										.build();
			
			job.getJobDataMap().put("droneId", droneId);
			
			Trigger trigger = TriggerBuilder.newTrigger()
												.withSchedule(SimpleScheduleBuilder.simpleSchedule()
														.withIntervalInSeconds(10)
														.repeatForever())
												.build();
			
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
			
			sch.start();
			sch.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}