package com.soccer.manager.security.jwt;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.soccer.manager.domain.dto.request.UserCreationRequestDTO;
import com.soccer.manager.facade.UserCreationFacade;

@ContextConfiguration
class JwtAuthenticationControllerTest {

	private JwtAuthenticationController jwtAuthenticationController;
	private JwtService jwtService;
	private UserCreationFacade userCreationFacade;
	private MockMvc mockMvc;
	
	@BeforeEach
    public void setUp() {
		jwtService = mock(JwtService.class);
		userCreationFacade = mock(UserCreationFacade.class);
		jwtAuthenticationController = new JwtAuthenticationController(jwtService, userCreationFacade);
		
        mockMvc = MockMvcBuilders
                	.standaloneSetup(jwtAuthenticationController)
                	.build();
	}
	
	@Test
	void testInvalidUsernameSignUp() throws Exception {
		var userCreationRequestDto = new UserCreationRequestDTO();
		
		userCreationRequestDto.setUsername("noemailaddress");
		userCreationRequestDto.setPassword("blahblah");
		
		var requestBody = new JsonMapper().writeValueAsString(userCreationRequestDto);
		
		mockMvc.perform(post("/sign-up")
							.content(requestBody)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void testSignUp() throws Exception {
		var userCreationRequestDto = new UserCreationRequestDTO();
		
		userCreationRequestDto.setUsername("mighty@gmail.com");
		userCreationRequestDto.setPassword("secret123");
		
		var requestBody = new JsonMapper().writeValueAsString(userCreationRequestDto);
		
		mockMvc.perform(post("/sign-up")
							.content(requestBody)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	void testAuthentication() throws Exception {
		// given
		var userCreationRequestDto = new UserCreationRequestDTO();
		
		userCreationRequestDto.setUsername("mighty@gmail.com");
		userCreationRequestDto.setPassword("secret123");
		
		var userCreationRequestBody = new JsonMapper().writeValueAsString(userCreationRequestDto);
		
		var jwtRequest = new JwtRequest();
		
		jwtRequest.setUsername("mighty@gmail.com");
		jwtRequest.setPassword("secret123");
		
		var jwtRequestBody = new JsonMapper().writeValueAsString(jwtRequest);
		
		// when
		when(jwtService.createAuthenticationToken(jwtRequest)).thenReturn(new JwtResponse("f589682c-c600-4ffe-86f9-d2df93d6217e", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaWdodHlAZ21haWwuY29tIiwiZXhwIjoxNjM4MDg2OTU0LCJpYXQiOjE2MzgwODMzNTR9.Z6GCzw5G8SnJ4rYgEeAlDrwCz8beAUXOe5xIPoNa4GIjieWvWnUfUpEgslMZmcczwVLRYP2U1eSbK-qR2o4U7g"));
		
		mockMvc.perform(post("/sign-up")
							.content(userCreationRequestBody)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(result -> mockMvc.perform(post("/authenticate")
													.content(jwtRequestBody)
													.contentType(MediaType.APPLICATION_JSON))
										.andExpect(status().isOk()));
	}
}
