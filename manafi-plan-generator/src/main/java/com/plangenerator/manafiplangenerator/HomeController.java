package com.plangenerator.manafiplangenerator;

import java.text.ParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plangenerator.model.PayloadRequest;
import com.plangenerator.service.Payload;


@RestController
public class HomeController {	
	
	@PostMapping("/generate-plan")
	@Consumes(MediaType.APPLICATION_JSON)
	
	public String createJSON(@RequestBody PayloadRequest payloadRequest) throws ParseException {
		
		Payload payload = new Payload(payloadRequest);
		payload.computing();	
		String result = payload.toString();		
		return result;
	}

}
