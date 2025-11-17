DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM pg_constraint
            WHERE conname = 'unique_name'
        ) THEN
            ALTER TABLE users ADD CONSTRAINT unique_name UNIQUE (name);
        END IF;
    END $$;

DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM pg_constraint
            WHERE conname = 'unique_email'
        ) THEN
            ALTER TABLE users ADD CONSTRAINT unique_email UNIQUE (email);
        END IF;
    END $$;

DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM pg_constraint
            WHERE conname = 'unique_name_roles'
        ) THEN
            ALTER TABLE roles ADD CONSTRAINT unique_name_roles UNIQUE (name);
        END IF;
    END $$;
