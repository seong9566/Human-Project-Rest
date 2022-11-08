INSERT INTO company(
        company_name,
        company_picture,
        company_email,
        company_phone_number,
        company_address,
        created_at
    )
VALUES(
        'company01',
        'd210a466-5707-4c42-85a4-e30790daa0f0.png',
        'company01@naver.com',
        '010-9459-5116',
        '대구대구',
        now()
    );
INSERT INTO users (login_id, login_password, company_id, created_at)
VALUES(
        'company',
        '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4',
        1,
        now()
    );
INSERT INTO category (
        category_frontend,
        category_backend,
        category_devops,
        created_at
    )
VALUES(1, 0, 0, NOW());
INSERT INTO career (
        one_year_less,
        two_year_over,
        three_year_over,
        five_year_over,
        created_at
    )
VALUES(1, 0, 0, 0, NOW());
INSERT INTO job_posting_board (
        company_id,
        job_posting_board_category_id,
        job_posting_board_career_id,
        job_posting_board_title,
        job_posting_salary,
        job_posting_board_place,
        job_posting_board_content,
        job_posting_board_deadline,
        created_at
    )
VALUES(1, 1, 1, 'title', 5500, 'place', 'content', NOW(), NOW());