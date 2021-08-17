package com.oracolo.findmycar.users.rest;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracolo.findmycar.users.exceptions.ErrorDto;
import com.oracolo.findmycar.users.rest.dto.UniqueKeyDto;
import com.oracolo.findmycar.users.rest.dto.UserDto;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

@QuarkusTest
class UserRestTest extends BaseTest{

	private final static String EMAIL_A = "gzano93@gmail.com";
	private final static String EMAIL_B = "unaltramail@mail2.com";

	@Test
	@DisplayName("Create new google user to token")
	void createNewUserFromToken(){

		String email = EMAIL_A;
		String fullName = "Giacomo Zanotti";
		String pictureUrl = "https://test.google.com/picture";
		createNewUser(email,fullName,pictureUrl);

		UserDto userDto = getUserByEmail(email);
		Assertions.assertEquals(email,userDto.email);
		Assertions.assertEquals(pictureUrl,userDto.pictureUrl);

	}
	@Test
	@DisplayName("Should throw bad request if post with empty values email,name or url")
	void shouldThrowWhenEmptyPost(){
		UserDto userDto1 = new UserDto();
		Response response1 = given().body(userDto1).contentType(MediaType.APPLICATION_JSON).post("/users/google");
		Assertions.assertEquals(HttpResponseStatus.BAD_REQUEST.code(),response1.getStatusCode());

		UserDto userDto2 = new UserDto();
		userDto2.email = " ";
		userDto2.pictureUrl="https://test.google.com/picture";
		userDto2.name = "Giacomo Zanotti";
		Response response2 = given().body(userDto2).contentType(MediaType.APPLICATION_JSON).post("/users/google");
		Assertions.assertEquals(HttpResponseStatus.BAD_REQUEST.code(),response2.getStatusCode());

		UserDto userDto3 = new UserDto();
		userDto3.email = "gzano93@gmail.com";
		userDto3.pictureUrl=" ";
		userDto3.name = "Giacomo Zanotti";
		Response response3 = given().body(userDto3).contentType(MediaType.APPLICATION_JSON).post("/users/google");
		Assertions.assertEquals(HttpResponseStatus.BAD_REQUEST.code(),response3.getStatusCode());

		UserDto userDto4 = new UserDto();
		userDto4.email = "gzano93@gmail.com";
		userDto4.pictureUrl="https://test.google.com/picture";
		userDto4.name = "    ";
		Response response4 = given().body(userDto4).contentType(MediaType.APPLICATION_JSON).post("/users/google");
		Assertions.assertEquals(HttpResponseStatus.BAD_REQUEST.code(),response4.getStatusCode());

	}
	@Test
	@DisplayName("Should throw bad request if post with not valid email or picture url")
	void shouldThrowBadPostUser(){
		UserDto userDto1 = new UserDto();
		userDto1.email = "wekjdfwnlkò";
		userDto1.name = "Giacomo Zanotti";
		userDto1.pictureUrl = "https://test.google.com/picture";

		Response response1 = given().body(userDto1).contentType(MediaType.APPLICATION_JSON).post("/users/google");
		Assertions.assertEquals(HttpResponseStatus.BAD_REQUEST.code(),response1.getStatusCode());
		ErrorDto responseMessage = response1.getBody().as(ErrorDto.class);
		Assertions.assertEquals("Invalid user email",responseMessage.message);
	}

	@Test
	@DisplayName("Should get user by unique key")
	void shouldGetUserByUniqueKey(){
		String email = EMAIL_B;
		String fullName = "Giacomo Zanotti";
		String pictureUrl = "https://test.google.com/picture";
		UniqueKeyDto uniqueKeyDto = createNewUser(email,fullName,pictureUrl);

		UserDto userDto = getUserById(uniqueKeyDto.uniqueKey);
		Assertions.assertEquals(email,userDto.email);
		Assertions.assertEquals(pictureUrl,userDto.pictureUrl);
	}


}