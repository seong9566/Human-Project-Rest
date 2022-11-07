INSERT INTO personal (personal_name, personal_email, personal_education, personal_phone_number, personal_address, created_at) 
VALUES('testUsername', 'testEmail@example.com', '4년제졸업','010-4444-6666l', 'test-address', now());
-- INSERT INTO users (login_id, login_password, personal_id, created_at) VALUES('testuser1', 'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869', 1, now());
INSERT INTO company(company_name,company_picture,company_email,company_phone_number,company_address,created_at) VALUES('박동훈', '사진입니다', 'ssar@nate.com','01024102957','화명동', now());

-- INSERT INTO users (login_id, login_password, company_id, created_at) VALUES('testuser', 'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869', 1, now());
INSERT INTO resumes(resumes_title, resumes_picture, resumes_introduce, resumes_category_id, personal_id，career_id，portfolio_id，created_at)
 VALUES('프론트엔드용이력서', '사진값', '안녕하세요',2, 1,1,1,now());


