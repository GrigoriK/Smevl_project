DO $$
 BEGIN

  BEGIN
    ALTER TABLE project_leads
    ADD CONSTRAINT project_id FOREIGN KEY (project_id)
    REFERENCES projects (project_id) MATCH SIMPLE;
  EXCEPTION
   WHEN duplicate_object THEN RAISE NOTICE 'Table constraint employee_id already exists';
   END;

END $$;



DO $$
 BEGIN

  BEGIN
   ALTER TABLE project_leads
   ADD CONSTRAINT project_id FOREIGN KEY (project_id)
   REFERENCES projects (project_id) MATCH SIMPLE;
  EXCEPTION
   WHEN duplicate_object THEN RAISE NOTICE 'Table constraint project_id already exists';
   END;

END $$;
