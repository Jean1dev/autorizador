CREATE TABLE account_event (
    id UUID PRIMARY KEY,
    active_card BOOLEAN NOT NULL,
    available_limit DECIMAL(10, 2) NOT NULL
);