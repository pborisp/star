-- create tables here for PostgreSQL
CREATE TABLE IF NOT EXISTS RULE_SET (
                                        id INT PRIMARY KEY,
                                        query VARCHAR(255),
                                        arguments VARCHAR(255),
                                        negate BOOLEAN
);