``` DB 사용자 설정
CREATE USER 'jobsmatch'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON *.* TO 'jobsmatch'@'%';
CREATE database jobs;
```

``` 테이블 생성
CREATE TABLE users (
  users_id INTEGER auto_increment PRIMARY KEY,
  login_id varchar(20),
  login_password varchar(50),
  personal_id integer,
  company_id integer,
  created_at timestamp
);

CREATE TABLE company (
  company_id integer auto_increment PRIMARY KEY,
  company_name varchar(20),
  company_picture longtext,
  company_email varchar(50),
  company_phone_number varchar(20),
  company_address varchar(50),
  created_at timestamp
);

CREATE TABLE personal (
  personal_id integer auto_increment PRIMARY KEY,
  personal_name varchar(20),
  personal_email varchar(50),
  personal_education varchar(10),
  personal_phone_number varchar(20),
  personal_address varchar(50),
  created_at timestamp
);


CREATE TABLE resumes (
  resumes_id integer auto_increment PRIMARY KEY,
  resumes_title varchar(50),
  resumes_picture longtext,
  resumes_introduce longtext,
  resumes_category_id integer,
  personal_id integer,
  career_id integer,
  portfolio_id integer,
  created_at timestamp
);


CREATE TABLE portfolio (
  portfolio_id integer auto_increment PRIMARY KEY,
  portfolio_source LONGTEXT,
  portfolio_file longtext,
  created_at timestamp
);


CREATE TABLE career (
  career_id integer auto_increment PRIMARY KEY,
  one_year_less tinyint(1),
  two_year_over tinyint(1),
  three_year_over tinyint(1),
  five_year_over tinyint(1),
  created_at TIMESTAMP
);


CREATE TABLE job_posting_board (
  job_posting_board_id integer auto_increment PRIMARY KEY,
  company_id integer,
  job_posting_board_category_id integer,
  job_posting_board_career_id integer,
  job_posting_board_title varchar(50),
  job_posting_salary integer,
  job_posting_board_place varchar(50),
  job_posting_board_content longtext,
  job_posting_board_deadline timestamp,
  created_at timestamp
);


CREATE TABLE apply (
  apply_id integer auto_increment PRIMARY KEY,
  job_posting_board_id integer,
  resumes_id integer,
  alarm_id integer,
  created_at timestamp
);


CREATE TABLE subscribe (
  subcribe_id integer auto_increment PRIMARY KEY,
  company_id integer,
  personal_id integer,
  alarm_id integer,
  created_at timestamp
);


CREATE TABLE personal_like (
  personal_like_id integer auto_increment PRIMARY KEY,
  resumes_id integer,
  company_id integer,
  alarm_id integer,
  created_at timestamp
);

CREATE TABLE company_like (
  company_like_id integer auto_increment PRIMARY KEY,
  personal_id integer,
  company_id integer,
  alarm_id integer,
  created_at timestamp
);


CREATE TABLE category (
  category_id integer auto_increment PRIMARY KEY,
  category_frontend tinyint(1),
  category_backend tinyint(1),
  category_devops tinyint(1),
  created_at timestamp
);


CREATE TABLE alarm (
  alarm_id integer auto_increment PRIMARY KEY,
  users_id integer,
  alarm_apply_id integer,
  alarm_incruit_id integer,
  alarm_subscribe_id integer,
  alarm_company_like_id integer,
  alarm_personal_like_id integer,
  alarm_message longtext,
  alarm_check tinyint(1),
  created_at timestamp
);

ALTER TABLE users ADD FOREIGN KEY (personal_id) REFERENCES personal (personal_id);


ALTER TABLE users ADD FOREIGN KEY (company_id) REFERENCES company (company_id);


ALTER TABLE resumes ADD FOREIGN KEY (personal_id) REFERENCES personal (personal_id);


ALTER TABLE personal_like ADD FOREIGN KEY (company_id) REFERENCES company (company_id);


ALTER TABLE subscribe ADD FOREIGN KEY (company_id) REFERENCES company (company_id);


ALTER TABLE subscribe ADD FOREIGN KEY (personal_id) REFERENCES personal (personal_id);


ALTER TABLE apply ADD FOREIGN KEY (resumes_id) REFERENCES resumes (resumes_id);


ALTER TABLE company_like ADD FOREIGN KEY (personal_id) REFERENCES personal (personal_id);


ALTER TABLE personal_like ADD FOREIGN KEY (resumes_id) REFERENCES resumes (resumes_id);


ALTER TABLE company_like ADD FOREIGN KEY (company_id) REFERENCES company (company_id);


ALTER TABLE resumes ADD FOREIGN KEY (career_id) REFERENCES career (career_id);


ALTER TABLE company_like ADD FOREIGN KEY (alarm_id) REFERENCES alarm (alarm_id);


ALTER TABLE personal_like ADD FOREIGN KEY (alarm_id) REFERENCES alarm (alarm_id);


ALTER TABLE subscribe ADD FOREIGN KEY (alarm_id) REFERENCES alarm (alarm_id);


ALTER TABLE apply ADD FOREIGN KEY (alarm_id) REFERENCES alarm (alarm_id);


ALTER TABLE alarm ADD FOREIGN KEY (alarm_company_like_id) REFERENCES company_like (company_like_id);


ALTER TABLE alarm ADD FOREIGN KEY (alarm_personal_like_id) REFERENCES personal_like (personal_like_id);


ALTER TABLE alarm ADD FOREIGN KEY (alarm_apply_id) REFERENCES apply (apply_id);


ALTER TABLE alarm ADD FOREIGN KEY (alarm_subscribe_id) REFERENCES subscribe (subscribe_id);


ALTER TABLE alarm ADD FOREIGN KEY (users_id) REFERENCES users (users_id);


ALTER TABLE resumes ADD FOREIGN KEY (portfolio_id) REFERENCES portfolio (portfolio_id);


ALTER TABLE job_posting_board ADD FOREIGN KEY (company_id) REFERENCES company (company_id);


ALTER TABLE job_posting_board ADD FOREIGN KEY (job_posting_board_career_id) REFERENCES career (career_id);


ALTER TABLE job_posting_board ADD FOREIGN KEY (job_posting_board_category_id) REFERENCES category (category_id);

ALTER TABLE resumes ADD FOREIGN KEY (resumes_category_id) REFERENCES category (category_id);
```

```더미데이터 생성
개인회원은 51 ~ 100 번
기업 회원은 1 ~ 50 번
auto_increment 재조정 :
ALTER TABLE [테이블명] AUTO_INCREMENT=1;
SET @COUNT = 0;
UPDATE [테이블명] SET [AUTO_INCREMENT 열 이름] = @COUNT:=@COUNT+1;
-------------------- user(기업) -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 1;	
WHILE i <= 50 DO	
INSERT INTO users (login_id, login_password, company_id, created_at)
VALUES(concat('company',i), '1234', i, now());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$
CALL loopInsert;
-------------------- user(개인) -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 1;	
WHILE i <= 50 DO	
INSERT INTO users (login_id, login_password, personal_id, created_at)
VALUES(concat('personal',i), '1234', i, now());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$
CALL loopInsert;
-------------------- company -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 1;	
WHILE i <= 50 DO	
INSERT INTO company (company_name, company_category_id, created_at)
VALUES(concat('companyname',i), if(i%4=0, 1, i%4),  NOW());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$

CALL loopInsert;
-------------------- personal -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 1;	
WHILE i <= 50 DO	
INSERT INTO personal (personal_name, personal_category_id, created_at)
VALUES(concat('personalname',i), i,  NOW());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$

CALL loopInsert;
-------------------- company_detail -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 1;	
WHILE i <= 50 DO	
INSERT INTO company_detail (company_id, company_email, company_phone_number, company_picture, company_address, created_at)
VALUES(i, CONCAT('company_email',i,'@example.com'), CONCAT('010-',i,'-0000'), CONCAT('company_picture', i), CONCAT('company_adress', i), NOW());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$

CALL loopInsert;
-------------------- personal_detail -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 51;	
WHILE i <= 100 DO	
INSERT INTO personal_detail (personal_id, personal_email, personal_phone_number, created_at)
VALUES(i, CONCAT('personal_email',i,'@example.com'), CONCAT('010-',i,'-0000'), NOW());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$

CALL loopInsert;
-------------------- resumes -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 1;	
WHILE i <= 50 DO	
INSERT INTO resumes (personal_id, resumes_title, resumes_picture, resumes_introduce, created_at)
VALUES(i, CONCAT('resumes_title',i), CONCAT(i,"번유저의사진입니다."), CONCAT("안녕하세요 저는 ", i, "입니다."), NOW());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$

CALL loopInsert;
-------------------- job_posting_board -------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
CREATE PROCEDURE loopInsert()
BEGIN
DECLARE i INT DEFAULT 1;	
WHILE i <= 50 DO	
INSERT INTO job_posting_board (company_id, job_posting_board_title, job_posting_board_content, created_at)
VALUES(i, CONCAT('job_posting_board_title',i), CONCAT(i, "구인합니다."), NOW());
SET i = i + 1;
END WHILE;
END$$
DELIMITER $$
CALL loopInsert;

```
