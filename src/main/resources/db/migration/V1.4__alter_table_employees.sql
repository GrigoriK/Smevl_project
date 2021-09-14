ALTER TABLE employees
    ADD CONSTRAINT position_id FOREIGN KEY (position_id)
    REFERENCES positions (position_id) MATCH SIMPLE;

ALTER TABLE employees
    ADD CONSTRAINT project_id FOREIGN KEY (project_id)
    REFERENCES projects (project_id) MATCH SIMPLE;