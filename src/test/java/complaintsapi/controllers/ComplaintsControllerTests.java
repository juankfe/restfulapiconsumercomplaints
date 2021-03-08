package complaintsapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class ComplaintsControllerTests {

	/*
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@BeforeEach
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	
	@Test
	public void testAddComplaint() throws Exception{
		
		String url = "/complaints";		
		
		this.mvc.perform(post(url)
				.content("{\"complaintId\":\"\", \"title\":\"order did not arrive\", "
						+ "\"description\":\"I ordered a pair os shoes four weeks agoa and it did "
						+ "not arrive yet\", \"locale\":\"chicago\", \"company\":\"bestshoes\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdateComplaint() throws Exception{
		
		String url = "/complaints";	
		
		this.mvc.perform(put(url)
				.content("{\"complaintId\":\"10\", \"title\":\"order did not arrive\", "
						+ "\"description\":\"I ordered a pair os shoes four weeks agoa and it did "
						+ "not arrive yet\", \"locale\":\"chicago\", \"company\":\"bestshoes\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testListComplaints() throws Exception{
		
		String url = "/complaints?pageNumber=0&pageSize=3";		
		
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	@Test
	public void testListComplaintsByLocale() throws Exception{
		
		String url = "/complaintsbylocale?locale=chicago&pageNumber=0&pageSize=3";		
		
		this.mvc.perform(get(url)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testListComplaintsByCompany() throws Exception{
		
		String url = "/complaintsbycompany?company=bestshoes&pageNumber=0&pageSize=3";		
		
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	@Test
	public void testListComplaintsByCompanyByLocale() throws Exception{
		
		String url = "/complaintsbycompanybylocale?company=bestshoes&locale=chicago&pageNumber=0&pageSize=3";		
		
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	@Test
	public void testCountComplaintsByLocale() throws Exception{
		
		String url = "/countcomplaintsbylocale?locale=chicago";		
		
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	@Test
	public void testCountComplaintsByCompany() throws Exception{
		
		String url = "/countcomplaintsbycompany?company=bestshoes";		
		
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	@Test
	public void testCountComplaintsByCompanyByLocale() throws Exception{
		
		String url = "/countcomplaintsbycompanybylocale?company=bestshoes&locale=chicago";		
		
		this.mvc.perform(get(url)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testDeleteComplaint() throws Exception{
		
		String url = "/complaints?complaintId=6";		
		
		this.mvc.perform(delete(url)).andExpect(status().isOk());
	}*/
	
}
