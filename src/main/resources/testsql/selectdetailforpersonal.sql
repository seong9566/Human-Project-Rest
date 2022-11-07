INSERT INTO personal (
        personal_name,
        personal_email,
        personal_education,
        personal_phone_number,
        personal_address,
        created_at
    )
VALUES(
        'testUsername',
        'testEmail@example.com',
        '4년제졸업',
        '010-4444-6666',
        'test-addressggggg,123,123',
        now()
    );
INSERT INTO users (
        login_id,
        login_password,
        personal_id,
        created_at
    )
VALUES(
        'testuser1',
        'b3e38b54c333cb244320e4e1d091e9bf86eacf0fffeae37dbc04847f1cdf2869',
        1,
        now()
    );