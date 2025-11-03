CREATE TABLE IF NOT EXISTS currencies(
                                         id UUID NOT NULL PRIMARY KEY,
                                         code VARCHAR(5) NOT NULL,
                                         name VARCHAR(25) NOT NULL,
                                         symbol VARCHAR(1) NOT NULL
);
