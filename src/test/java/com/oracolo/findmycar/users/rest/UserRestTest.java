package com.oracolo.findmycar.users.rest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracolo.findmycar.users.rest.dto.UniqueKeyDto;
import com.oracolo.findmycar.users.rest.dto.UserDto;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class UserRestTest extends BaseTest{



	@Test
	@DisplayName("Create new google user to token")
	void createNewUserFromToken(){

		String email = "gzano93@gmail.com";
		String fullName = "Giacomo Zanotti";
		String pictureUrl = "https://test.google.com/picture";
		createNewUser(email,fullName,pictureUrl);

	}

}