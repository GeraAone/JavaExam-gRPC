INSERT INTO users (first_name, age, country, username, password, email)
VALUES
    ('Mike', 21, 'RUSSIA', 'user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
    ('Jane', 33, 'AUSTRALIA', 'admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

INSERT INTO users (first_name, age, country)

VALUES
    ('Alice', 19, 'INDIA'),
    ('Bob', 18, 'CANADA'),
    ('Charlie', 22, 'JAPAN');

INSERT INTO roles (role_name)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2);