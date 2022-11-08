INSERT INTO personal (personal_name, personal_email, personal_education, personal_phone_number, personal_address, created_at) 
VALUES('testUsername', 'testEmail@example.com', '4년제졸업','010-4444-6666l', 'test-address', now());
INSERT INTO company(company_name,company_picture,company_email,company_phone_number,company_address,created_at) 
VALUES('박동훈', '사진입니다', 'ssar@nate.com','01024102957','화명동', NOW());
INSERT INTO users (login_id, login_password, company_id, created_at) VALUES('testuser2', 'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869', 1, now());
INSERT INTO users (login_id, login_password, personal_id, created_at) VALUES('testuser2', 'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869', 1, now());

INSERT INTO resumes (resumes_title, resumes_picture, resumes_introduce,personal_id) VALUES('ㅎㅇ','사진임','ㅇㅇㅇㅇ',1);
INSERT INTO personal_like (company_id, resumes_id, created_at) VALUES(1,1,NOW());
INSERT INTO company_like (personal_id, company_id, created_at) VALUES(1,1,NOW());