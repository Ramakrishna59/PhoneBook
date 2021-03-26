package com.phonebook.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.Entity.Contact;
import com.phonebook.Service.ServicesImpl;
import com.phonebook.props.AppProperties;

@WebMvcTest(value = RestControllers.class)
public class RestControllerTest {

	@MockBean
	private ServicesImpl service;
	
	@MockBean
	private AppProperties prop;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_saveContact_1() throws Exception {

		when(service.saveContact(Mockito.any())).thenReturn(true);

		Contact c = new Contact(101, "Rama", "6457947432", "rama@gmail.com");

		ObjectMapper mapper = new ObjectMapper();

		String json = null;

		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int status = response.getStatus();

		assertEquals(201, status);
	}

	@Test
	public void test_saveContact_2() throws Exception {

		when(service.saveContact(Mockito.any())).thenReturn(false);

		Contact c = new Contact(101, "Rama", "6457947432", "rama@gmail.com");

		ObjectMapper mapper = new ObjectMapper();

		String json = null;

		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int status = response.getStatus();

		assertEquals(500, status);
	}

	@Test
	public void test_saveContact_3() throws Exception {

		when(service.saveContact(Mockito.any())).thenThrow(RuntimeException.class);

		Contact c = new Contact(101, "Rama", "6457947432", "rama@gmail.com");

		ObjectMapper mapper = new ObjectMapper();

		String json = null;

		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int status = response.getStatus();

		assertEquals(500, status);
	}

	@Test
	public void getAllContact_Test_1() throws Exception {

		when(service.ViewAllContacts()).thenReturn(Collections.emptyList());

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);
	}

	@Test
	public void getAllContact_Test_2() throws Exception {

		when(service.ViewAllContacts()).thenThrow(RuntimeException.class);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);
	}
//	@Test
//	public void getContactById_Test_1() throws Exception {
//		
//		when(service.getContactById(Mockito.any()));
//		
//		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
//		
//		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
//		
//		MockHttpServletResponse response = result.getResponse();
//		
//		int status = response.getStatus();
//		
//		assertEquals(200, status);
//	}
	@Test
	public void getContactById_Test_2() throws Exception {
		Contact c = new Contact(101, "Rama", "6457947432", "rama@gmail.com");
		
		when(service.getContactById(101)).thenReturn(c);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
	}
	@Test
	public void getContactById_Test_3() throws Exception {
		Contact c = new Contact(null, null, null, null);
		
		when(service.getContactById(null)).thenReturn(c);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
	}
}
