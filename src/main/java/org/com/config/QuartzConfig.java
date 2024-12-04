package org.com.config;

import org.quartz.CronTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ComponentScan
@EnableScheduling
public class QuartzConfig {

	public static final String CRON120000MS = "10 */2 * * * ?"; // Expresión cron equivalente a la tasa fija de 2 minutos
	// /-fiRate = 120000
	public static final String CRON180000MS = "30 0/2 * ? * *"; // Expresión cron equivalente a la tasa fija de 3 minutos
	// /-fixedRate = 180000
	public static final String CRON300000MS = "50 */2 * * * ?"; // Expresión cron equivalente a la tasa fija de 5 minutos
	// /-fixedRate = 300000

	// ProcesarArchivosServiceImpl
	@Bean
	public MethodInvokingJobDetailFactoryBean tpProcesarArchivos() {
		MethodInvokingJobDetailFactoryBean jdf = new MethodInvokingJobDetailFactoryBean();
		jdf.setTargetBeanName("procesarArchivosController");
		jdf.setTargetMethod("procersarArchivos");
		jdf.setConcurrent(false);
		return jdf;
	}

	@Bean
	public CronTriggerFactoryBean triggerProcesarArchivos() {
		CronTriggerFactoryBean ctf = new CronTriggerFactoryBean();
		ctf.setJobDetail(tpProcesarArchivos().getObject());
		ctf.setCronExpression(setCronVariable());
		ctf.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

		
		return ctf;
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean tpprocersarImprimeLogs() {
		MethodInvokingJobDetailFactoryBean jdf = new MethodInvokingJobDetailFactoryBean();
		jdf.setTargetBeanName("procesarArchivosController");
		jdf.setTargetMethod("procersarImprimeLogs");
		jdf.setConcurrent(false);
		return jdf;
	}

	@Bean
	public CronTriggerFactoryBean triggerprocersarImprimeLogs() {
		CronTriggerFactoryBean ctf = new CronTriggerFactoryBean();
		ctf.setJobDetail(tpprocersarImprimeLogs().getObject());
		ctf.setCronExpression(setCronVariable());
		ctf.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

		
		return ctf;
	}
	
	// PLANIFICADOR DE TAREAS
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(triggerProcesarArchivos().getObject());
		scheduler.setTriggers(triggerprocersarImprimeLogs().getObject());
		scheduler.setAutoStartup(true);
		log.info("QuartzConfig tareas ===> ", scheduler.toString());
		return scheduler;

	}

	private String setCronVariable() {

		return CRON120000MS;

	}
}
