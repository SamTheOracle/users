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
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;

@QuarkusTest
class UserRestTest extends BaseTest{

	private static final String UNIQUE_KEY = "kjsdfhò";
	@Test
	@DisplayName("Create new google user to token")
	void createNewUserFromToken(){

		String email = "gzano93@gmail.com";
		String fullName = "Giacomo Zanotti";
		String pictureUrl = "https://test.google.com/picture";
		createNewUser(email,fullName,pictureUrl);

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

		UserDto userDto2 = new UserDto();
		userDto2.email = "gzano93@gmail.com";
		userDto2.name = "Giacomo Zanotti";
		userDto2.pictureUrl = "klajdblk";
		Response response2 = given().body(userDto2).contentType(MediaType.APPLICATION_JSON).post("/users/google");
		Assertions.assertEquals(HttpResponseStatus.BAD_REQUEST.code(),response2.getStatusCode());
		ErrorDto responseMessage2 = response2.getBody().as(ErrorDto.class);
		Assertions.assertEquals("Invalid picture url",responseMessage2.message);
	}


	@Test
	@DisplayName("Should get user by unique key")
	void shouldGetUserByUniqueKey(){
		String email = "marco@acaso.it";
		String name = "Marco";
		String pictureUrl = "https://www.bestpic.com";
		UniqueKeyDto uniqueKeyDto = createNewUser(email,name,pictureUrl);
		Response response = given().queryParam("uniqueKey",uniqueKeyDto.uniqueKey).get("/users/google");
		int status = response.getStatusCode();
		Assertions.assertEquals(HttpResponseStatus.OK.code(),status);
		JsonArray userArray = new JsonArray(response.body().asString());
		Assertions.assertEquals(1,userArray.size());
		UserDto userDto = Json.decodeValue(userArray.getJsonObject(0).encode(),UserDto.class);
		Assertions.assertEquals(email,userDto.email);
		Assertions.assertEquals(name,userDto.name);
		Assertions.assertEquals(pictureUrl,userDto.pictureUrl);
		Assertions.assertNotNull(userDto.id);
	}

}