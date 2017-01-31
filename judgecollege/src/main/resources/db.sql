drop table user_ROLES CASCADE CONSTRAINTS;
DROP TABLE ROLES CASCADE CONSTRAINTS;
DROP TABLE USERS CASCADE CONSTRAINTS;

CREATE TABLE USERS(
username VARCHAR2(50),
password VARCHAR2(50),
first_name VARCHAR2(50),
last_name VARCHAR2(50),
gender VARCHAR2(1),
phone VARCHAR2(30),
email VARCHAR2(100),
dob DATE);

insert into users(username,password,first_name,last_name,gender,phone,email,dob)
values ('admin','admin','admin','admin','M','123456','admin@fdmgroup.com',to_date('01/01/1900','DD/MM/YYYY'));

CREATE TABLE roles (
	role_name			VARCHAR2(30) NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (role_name)
);

CREATE TABLE user_roles (
	username			VARCHAR2(30) NOT NULL,
	role_name			VARCHAR2(30) NOT NULL,
	PRIMARY KEY(username, role_name),
	CONSTRAINT ur_name_fk FOREIGN KEY (username) REFERENCES users(username),
	CONSTRAINT ur_role_fk FOREIGN KEY (role_name) REFERENCES roles(role_name)
);

INSERT INTO roles (role_name) VALUES ('admin');

INSERT INTO user_roles (username, role_name) VALUES ('admin', 'admin');