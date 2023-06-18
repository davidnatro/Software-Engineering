CREATE TABLE IF NOT EXISTS kpo.flights
(
    id            BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    flight_number VARCHAR(256)                 NOT NULL,
    from_point    VARCHAR(256)                 NOT NULL,
    to_point      VARCHAR(256)                 NOT NULL,
    depature_time TIMESTAMP                    NOT NULL,
    arrival_time  TIMESTAMP                    NOT NULL
);

CREATE TABLE IF NOT EXISTS kpo.passengers
(
    id       BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    fullname VARCHAR(256)                 NOT NULL
);

CREATE TABLE IF NOT EXISTS kpo.passenger_flight
(
    id           BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    passenger_id BIGINT                       NOT NULL,
    CONSTRAINT fk__passenger_id FOREIGN KEY (passenger_id) REFERENCES kpo.passengers (id),
    flight_id    BIGINT                       NOT NULL,
    CONSTRAINT fk__flight_id FOREIGN KEY (flight_id) REFERENCES kpo.flights (id)
);

CREATE INDEX IF NOT EXISTS idx__fullname_passengers ON kpo.passengers (fullname);
CREATE INDEX IF NOT EXISTS idx__flight_number_flights ON kpo.flights (flight_number);
