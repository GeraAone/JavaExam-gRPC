BEGIN;

CREATE SEQUENCE IF NOT EXISTS users_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    NO CYCLE;

CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT DEFAULT nextval('users_id_seq') PRIMARY KEY,
                                     first_name VARCHAR(100) NOT NULL,
                                     age INT NOT NULL DEFAULT 0,
                                     country VARCHAR(100) NOT NULL,
                                     created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS user_age_idx ON users USING btree (age, first_name);

COMMIT;