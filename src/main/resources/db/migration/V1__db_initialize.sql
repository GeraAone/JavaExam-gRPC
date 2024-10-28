BEGIN;

CREATE SEQUENCE IF NOT EXISTS users_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS roles_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    NO CYCLE;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT DEFAULT nextval('users_id_seq') PRIMARY KEY,
    first_name VARCHAR(100),
    age INT DEFAULT 0,
    country VARCHAR(100),
    username VARCHAR(100) UNIQUE,
    password VARCHAR(100) ,
    email VARCHAR(100),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS user_age_idx ON users USING btree (age, first_name);

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT DEFAULT nextval('roles_id_seq') PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
    );

COMMIT;