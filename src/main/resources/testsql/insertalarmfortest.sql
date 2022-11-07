INSERT INTO personal (personal_name, personal_email, personal_education, personal_phone_number, personal_address, created_at) 
VALUES('testUsername', 'testEmail@example.com', '4년제졸업','010-4444-6666l', 'test-address', now());

INSERT INTO users (login_id, login_password, personal_id, created_at) VALUES('testuser1', 'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869', 1, now());

INSERT INTO alarm(users_id, alarm_message, alarm_check, created_at) VALUES(1, 'testCompany1 님이 회원님을 좋아요 했습니다.', false, now());

INSERT INTO alarm(users_id, alarm_message, alarm_check, created_at) VALUES(1, 'testCompany2 님이 회원님을 좋아요 했습니다.', false, now());

INSERT INTO alarm(users_id, alarm_message, alarm_check, created_at) VALUES(1, 'testCompany3 님이 회원님을 좋아요 했습니다.', false, now());