package org.com.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Slf4j
@Component("procesarArchivosController")
public class ProcesarArchivosController {
	
	
	public void procersarArchivos() {
		
	 log.info("Ejecutando job...");
   
    try {
        Thread.sleep(300000); // 5 min
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    log.info("job finalizado.");
		
		
	}
	
	public void procersarImprimeLogs() {
		
		 log.info("Ejecutando job... ");
	   
	  
	
	}
}