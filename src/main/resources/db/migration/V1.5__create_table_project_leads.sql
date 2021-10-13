create table IF NOT EXISTS project_leads
(
    "project_lead_id" uuid NOT NULL,
    "employee_id" uuid NOT NULL,
    project_id uuid NOT NULL,
    PRIMARY KEY ("project_lead_id")
)
