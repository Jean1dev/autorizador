CREATE TABLE account_event (
    id CHAR(32) NOT NULL PRIMARY KEY,
    active_card BOOLEAN NOT NULL,
    available_limit DECIMAL(10, 2) NOT NULL
);