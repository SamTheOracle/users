package com.oracolo.findmycar.users.entity;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

	public String email;
	public String name;
	@Column(name = "picture_url")
	public String pictureUrl;
	public String locale;
	@Column(name = "family_name")
	public String familyName;
	@Column(name = "given_name")
	public String givenName;
	@Column(name = "chat_id")
	public Long chatId;
	@Column(name = "unique_key")
	public String uniqueKey;

}
