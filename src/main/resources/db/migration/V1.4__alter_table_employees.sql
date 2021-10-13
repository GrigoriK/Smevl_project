DO $$
 BEGIN

  BEGIN
   ALTER TABLE employees
   ADD CONSTRAINT position_id FOREIGN KEY (position_id)
   REFERENCES positions (position_id) MATCH SIMPLE;
  EXCEPTION
   WHEN duplicate_object THEN RAISE NOTICE 'Table constraint position_id already exists';
  END;

END $$;



DO $$
 BEGIN

  BEGIN
   ALTER TABLE employees
   ADD CONSTRAINT project_id FOREIGN KEY (project_id)
   REFERENCES projects (project_id) MATCH SIMPLE;
  EXCEPTION
   WHEN duplicate_object THEN RAISE NOTICE 'Table constraint project_id already exists';
  END;

END $$;