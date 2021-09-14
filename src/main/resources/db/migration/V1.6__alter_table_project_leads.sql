ALTER TABLE project_leads
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id)
    REFERENCES employees (employee_id) MATCH SIMPLE;

ALTER TABLE project_leads
    ADD CONSTRAINT project_id FOREIGN KEY (project_id)
    REFERENCES projects (project_id) MATCH SIMPLE;