CREATE TABLE IF NOT EXISTS categories(
                                         id UUID NOT NULL PRIMARY KEY,
                                         name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS records(
                                      id UUID NOT NULL PRIMARY KEY,
                                      category_id UUID NOT NULL,
                                      user_id UUID NOT NULL,
                                      currency_id UUID NOT NULL,
                                      creation_time TIMESTAMP NOT NULL,
                                      total_cost INT NOT NULL,
                                      CONSTRAINT fk_record_category
                                          FOREIGN KEY (category_id)
                                              REFERENCES categories(id)
                                              ON DELETE CASCADE
                                              ON UPDATE CASCADE
);