create table IF NOT EXISTS projects
(
    "project_id" uuid NOT NULL,
    project_name character varying(255) NOT NULL,
    project_code character varying(255) NOT NULL,
    PRIMARY KEY ("project_id")
)
