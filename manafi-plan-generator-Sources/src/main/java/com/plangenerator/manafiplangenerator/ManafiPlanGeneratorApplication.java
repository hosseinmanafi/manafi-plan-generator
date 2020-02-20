package com.plangenerator.manafiplangenerator;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import com.plangenerator.model.PayloadRequest;
import com.plangenerator.service.Payload;

@SpringBootApplication
public class ManafiPlanGeneratorApplication {

	public static void main(String[] args) {	
		
		
		SpringApplication.run(ManafiPlanGeneratorApplication.class, args);
	}	
	

}
