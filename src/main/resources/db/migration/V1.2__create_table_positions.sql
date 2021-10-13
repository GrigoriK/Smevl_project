create table IF NOT EXISTS positions
(
    "position_id" uuid NOT NULL,
    position_name character varying(255) NOT NULL,
    PRIMARY KEY ("position_id")
)
