INSERT INTO personal (personal_name, personal_email, personal_education, personal_phone_number, personal_address, created_at) 
VALUES('testUsername', 'testEmail@example.com', '4년제졸업','010-4444-6666l', 'test-address', now());

INSERT INTO users (login_id, login_password, personal_id, created_at) VALUES('testuser1', 'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869', 1, now());

INSERT INTO category (category_frontend, category_backend, category_devops, created_at)
VALUES(1,0,0,NOW());

INSERT INTO portfolio (portfolio_source, portfolio_file, created_at)
VALUES('github주소입니다', '포트폴리오', NOW());

INSERT INTO career (one_year_less, two_year_over, three_year_over, five_year_over, created_at)
VALUES(1,0,0,0,NOW());

INSERT INTO resumes (resumes_title, resumes_picture, resumes_introduce, resumes_place, resumes_category_id, personal_id, career_id, portfolio_id, created_at)
VALUES('resumes_title_example1', '사진파일', '안녕하세요 저는 1입니다.', '부산', 1, 1, 1, 1, NOW());

INSERT INTO category (category_frontend, category_backend, category_devops, created_at)
VALUES(0,1,0,NOW());

INSERT INTO portfolio (portfolio_source, portfolio_file, created_at)
VALUES('github222222', '포트폴리오222222', NOW());

INSERT INTO career (one_year_less, two_year_over, three_year_over, five_year_over, created_at)
VALUES(0,1,0,0,NOW());

INSERT INTO resumes (resumes_title, resumes_picture, resumes_introduce, resumes_place, resumes_category_id, personal_id, career_id, portfolio_id, created_at)
VALUES('resumes_title_example2', '사진파일22222', '안녕하세요 저는 222222입니다.', '부산', 2, 1, 2, 2, NOW());