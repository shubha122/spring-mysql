package com.example.springmysql;

import com.example.springmysql.dao.ProductDao;
import com.example.springmysql.dao.UserDao;
import com.example.springmysql.model.Product;
import com.example.springmysql.request.UpdateUserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.example.springmysql.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringMysqlApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private UserDao userDao;

	@MockBean
	private ProductDao productDao;

	ObjectMapper om = new ObjectMapper();
	@Before
	public void setUp(){
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
	}


	@Test
	public void createUserTest() throws Exception {
		User user = new User();
		//List<User> users = new ArrayList<>();
		//user.setId(9);
		user.setFname("ivy");
		user.setLname("jain");
		user.setCity("jaipur");
		user.setCountry("india");
		user.setEmail("ivy@gmail.com");
		user.setGender("F");
		user.setPassword("123");
		//List<User> expectedUsers = new ArrayList<>();
		User expectedUser = new User(9, user.getFname(), user.getLname(), user.getEmail(),user.getGender(),user.getPassword(),user.getCountry(),user.getCity());
		//expectedUsers.add(u1);
		String jsonRequest = om.writeValueAsString(user);
		String jsonResponse = om.writeValueAsString(expectedUser);
		given(userDao.save(any())).willReturn(expectedUser);
		//when(userDao.saveAll(anyList()).thenReturn(expectedUsers);
		MvcResult result = mockMvc.perform(post("/user/createUser").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Assert.assertEquals(jsonResponse,resultContent);
	}


@Test
	public void createUserValidationTest() throws Exception {
	User user = new User();
	user.setFname("Ivy");
	user.setLname("Chris");
	user.setCity("jaipur");
	user.setCountry("india");
	user.setEmail("Ivy");
	user.setGender("F");
	user.setPassword("123");
	String jsonRequest = om.writeValueAsString(user);
	MockHttpServletResponse response= mockMvc.perform(post("/user/createUser").content(jsonRequest)
			.contentType(MediaType.APPLICATION_JSON)).andReturn()
			.getResponse();
	Assert.assertEquals(400,response.getStatus());
	}

	@Test
	public void getUserFindAllTest() throws Exception {
		List<User> expectedUsers = new ArrayList<>();
		User u1 = new User(9,"emi","jain", "tom@gmail.com","F","123","india","pune");
		expectedUsers.add(u1);
		String jsonResponse = om.writeValueAsString(expectedUsers);
		given(userDao.findAll()).willReturn(expectedUsers);
		MvcResult result = mockMvc.perform(get("/user/getUsers"))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Assert.assertEquals(jsonResponse,resultContent);
	}
	@Test
	public void getUserFindByGenderTest() throws Exception {
		List<User> expectedUsers = new ArrayList<>();
		User u1 = new User(9,"jon","jain", "jon@gmail.com","M","123","india","bangalore");
		expectedUsers.add(u1);
		String jsonResponse = om.writeValueAsString(expectedUsers);
		when(userDao.findByGender(anyString())).thenReturn(expectedUsers);
		System.out.println("json response "+expectedUsers);
		MvcResult result = mockMvc.perform(get("/user/getUsers?gender=M"))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Assert.assertEquals(jsonResponse,resultContent);
	}

	@Test
	public void updateUserTest() throws Exception {
		UpdateUserRequest req = new	 UpdateUserRequest();
		req.setFname("Taru");
		String jsonRequest = om.writeValueAsString(req);
		int userId = 28;
		User expectedUser = new User(userId,req.getFname(),"jain","taru@gmail.com","M","111","india","jaipur");
		String jsonResponse = om.writeValueAsString(expectedUser);
		when(userDao.findById(userId)).thenReturn(expectedUser);
		MvcResult result = mockMvc.perform(put("/user/updateUser/" + userId).content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
				Assert.assertEquals(jsonResponse,resultContent);
	}
	@Test
	public void updateUserNullTest() throws Exception {

		UpdateUserRequest req = new	 UpdateUserRequest();
		req.setLname("Taru");
		String jsonRequest = om.writeValueAsString(req);
		int userId = 29;
		User expectedUser = new User(userId,"shu",req.getLname(),"abhay@gmail.com","M","111","india","jaipur");
		when(userDao.findById(userId)).thenReturn(null);
		MockHttpServletResponse response = mockMvc.perform(put("/user/updateUser/" + userId).content(jsonRequest)
		.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();
	Assert.assertEquals(404,response.getStatus());
	}

	@Test
	public void getProductTest() throws Exception {
		List<Product> products = new ArrayList<>();
		Product product = loadProduct("/test.json");
		products.add(product);
		String jsonResponse = om.writeValueAsString(products);
		int subcategory_id = product.getSubCategory().getId();
		String brand = product.getBrand();
		when(productDao.findBySubCategoryIdAndBrand(subcategory_id,brand)).thenReturn(products);
		MvcResult result = mockMvc.perform(get("/product/getProducts?subcategory_id="+subcategory_id+"&brand="+brand))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Assert.assertEquals(jsonResponse,resultContent);
	}

	public Product loadProduct(String name) {
		ObjectMapper mapper = new ObjectMapper();
		Product product = new Product();
		TypeReference<Product> typeReference = new TypeReference<Product>(){};
		InputStream inputStream = this.getClass().getResourceAsStream(name);
		try {
			product = mapper.readValue(inputStream,typeReference);
		} catch (IOException e){
			System.out.println("exception in reading json" + e.getMessage());
		}
		return product;
	}
}