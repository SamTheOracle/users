package com.oracolo.findmycar.users.rest;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Assertions;

import com.oracolo.findmycar.users.rest.dto.UniqueKeyDto;
import com.oracolo.findmycar.users.rest.dto.UserDto;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.restassured.response.Response;

public class BaseTest {

	protected static UniqueKeyDto createNewUser(String email, String fullName, String pictureUrl ){
		UserDto userDto = new UserDto();
		userDto.email = email;
		userDto.name = fullName;
		userDto.pictureUrl = pictureUrl;
		Response response = given().body(userDto).contentType(MediaType.APPLICATION_JSON).post("/users/google");
		Assertions.assertEquals(HttpResponseStatus.CREATED.code(),response.getStatusCode());
		UniqueKeyDto uniqueKeyDto = response.as(UniqueKeyDto.class);
		Assertions.assertNotNull(uniqueKeyDto.uniqueKey);
		return uniqueKeyDto;
	}
	protected static UserDto getUserByEmail(String email){
		Response response = given().queryParam("email",email).get("/users/google");
		Assertions.assertEquals(HttpResponseStatus.OK.code(),response.getStatusCode());
		UserDto userDto = response.as(UserDto.class);
		Assertions.assertNotNull(userDto.uniqueKey);
		return userDto;
	}
	protected static UserDto getUserById(String id){
		Response response = given().get("/users/google/"+id);
		Assertions.assertEquals(HttpResponseStatus.OK.code(),response.getStatusCode());
		UserDto userDto = response.as(UserDto.class);
		Assertions.assertNotNull(userDto.uniqueKey);
		return userDto;

	}
	protected static UserDto createUserDto(String email, String name, String pictureUrl){
		UserDto userDto = new UserDto();
		userDto.email = email;
		userDto.name = name;
		userDto.pictureUrl = pictureUrl;
		return userDto;
	}
}
