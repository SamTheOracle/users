package com.oracolo.findmycar.users.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "users")
public class User implements MetadataEnable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
	@Column(name = "picture_url")
	private String pictureUrl;
	private String locale;
	@Column(name = "family_name")
	private String familyName;
	@Column(name = "given_name")
	private String givenName;
	@Column(name = "chat_id")
	private Long chatId;
	@Column(name = "unique_key")
	private String uniqueKey;

	private Metadata metadata;

	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public Metadata getMetadata() {
		return metadata;
	}

	@Override
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(name, user.name) && Objects.equals(
				pictureUrl, user.pictureUrl) && Objects.equals(locale, user.locale) && Objects.equals(familyName, user.familyName)
				&& Objects.equals(givenName, user.givenName) && Objects.equals(chatId, user.chatId) && Objects.equals(uniqueKey,
				user.uniqueKey);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, name, pictureUrl, locale, familyName, givenName, chatId, uniqueKey);
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", email='" + email + '\'' + ", name='" + name + '\'' + ", pictureUrl='" + pictureUrl + '\''
				+ ", locale='" + locale + '\'' + ", familyName='" + familyName + '\'' + ", givenName='" + givenName + '\'' + ", chatId="
				+ chatId + ", uniqueKey='" + uniqueKey + '\'' + '}';
	}


}
