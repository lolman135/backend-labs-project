ALTER TABLE users
    ADD CONSTRAINT unique_name UNIQUE (name);

ALTER TABLE users
    ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE roles
    ADD CONSTRAINT unique_name UNIQUE (name);