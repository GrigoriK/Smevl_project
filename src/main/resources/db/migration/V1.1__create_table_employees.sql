create  table  employees
(
    "employee_id" uuid NOT NULL,
    name character varying(255) NOT NULL,
    female character varying(255) NOT NULL,
    position_id uuid NOT NULL,
    work_experience smallint NOT NULL,
    project_id uuid NOT NULL,
    PRIMARY KEY ("employee_id")
)
