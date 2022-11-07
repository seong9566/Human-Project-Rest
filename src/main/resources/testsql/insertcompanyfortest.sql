INSERT INTO company (company_name, company_picture, company_email, company_phone_number, company_address, created_at) 
VALUES('testCompanyname','testCompanyPicture' , 'testEmail@example.com', '010-4444-6666l', 'my,test,address,', now());

INSERT INTO users (login_id, login_password, company_id, created_at) VALUES('testcompany1', 'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869', 1, now());