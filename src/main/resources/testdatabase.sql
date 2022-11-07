CREATE TABLE users (
  users_id INTEGER auto_increment PRIMARY KEY,
  login_id varchar(20),
  login_password varchar(100),
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
  resumes_place varchar(10),
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
  subscribe_id integer auto_increment PRIMARY KEY,
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

