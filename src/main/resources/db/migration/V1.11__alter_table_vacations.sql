DO $$
  BEGIN

   BEGIN
    ALTER TABLE vacations
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id)
    REFERENCES employees (employee_id) MATCH SIMPLE;
   EXCEPTION
    WHEN duplicate_object THEN RAISE NOTICE 'Table constraint employee_id already exists';
    END;

END $$;
