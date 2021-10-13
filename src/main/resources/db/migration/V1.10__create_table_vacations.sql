create  table IF NOT EXISTS  vacations
(
    "vacation_id" uuid NOT NULL,
    "vacation_start_date" TIMESTAMP NOT NULL,
    "vacation_end_date" TIMESTAMP NOT NULL,
    "employee_id" uuid NOT NULL,
    PRIMARY KEY ("vacation_id")
)
