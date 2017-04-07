package com.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=DasBootApplication.class)
@RestClientTest
public class ShipwreckControllerWebIntegrationTest {
	
	@Test
	public void testListAll() throws JsonProcessingException, IOException{
		RestTemplate rt=new RestTemplate();
		
		ResponseEntity<String> response=rt.getForEntity("http://localhost:8080/api/v1/shipwrecks", String.class);
		
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson=objectMapper.readTree(response.getBody());
		
		assertThat(responseJson.isMissingNode(),is(false));
//		assertThat(responseJson.toString(),equalTo("[]"));
	}

}
