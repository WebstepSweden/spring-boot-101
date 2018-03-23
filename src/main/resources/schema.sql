CREATE TABLE websteper (
   id           INT IDENTITY PRIMARY KEY,
   first_name   VARCHAR(64) NOT NULL,
   last_name    VARCHAR(64) NOT NULL,
   interests    TEXT        NULL
);