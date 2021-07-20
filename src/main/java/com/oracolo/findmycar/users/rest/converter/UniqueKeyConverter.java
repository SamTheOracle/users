package com.oracolo.findmycar.users.rest.converter;

import javax.enterprise.context.ApplicationScoped;

import com.oracolo.findmycar.users.rest.dto.UniqueKeyDto;

@ApplicationScoped
public class UniqueKeyConverter {

	public UniqueKeyDto to(String uniqueKey){
		UniqueKeyDto uniqueKeyDto = new UniqueKeyDto();
		uniqueKeyDto.uniqueKey = uniqueKey;
		return uniqueKeyDto;
	}
}
