CREATE TABLE usersdb.users (
	id MEDIUMINT auto_increment NOT NULL,
	email varchar(255) NULL,
	name varchar(255) NULL,
	picture_url varchar(255) NULL,
	locale varchar(255) NULL,
	family_name varchar(255) NULL,
	given_name varchar(255) NULL,
	chat_id MEDIUMINT NULL,
	unique_key varchar(255) NOT NULL,
	insert_date timestamp not null,
    last_update timestamp,
    version int4 not null default 0,
    primary key(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
