BEGIN;
CREATE SEQUENCE IF NOT EXISTS users_id_seq;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT DEFAULT NEXT VALUE FOR users_id_seq PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    age INT NOT NULL DEFAULT 0,
    country VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
);

CREATE OR REPLACE INDEX user_age_idx ON users USING btree (age, first_name);

COMMIT;