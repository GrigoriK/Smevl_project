INSERT into employees VALUES
('2bb71036-1321-11ec-82a8-0242ac130003','Peter','Nikolson','2217747c-1320-11ec-82a8-0242ac130003',1,'c298fec0-1320-11ec-82a8-0242ac130003'),
('2bb7127a-1321-11ec-82a8-0242ac130003','Vladimir','Borisenko','221777ba-1320-11ec-82a8-0242ac130003',7,'c299008c-1320-11ec-82a8-0242ac130003'),
('2bb71374-1321-11ec-82a8-0242ac130003','Lindar','Noris','2217747c-1320-11ec-82a8-0242ac130003',4,'c299008c-1320-11ec-82a8-0242ac130003'),
('2bb71432-1321-11ec-82a8-0242ac130003','Ingvar','Bronson','2217747c-1320-11ec-82a8-0242ac130003',5,'c299008c-1320-11ec-82a8-0242ac130003'),
('2bb715fe-1321-11ec-82a8-0242ac130003','Nestor','Depp','22177882-1320-11ec-82a8-0242ac130003',2,'c299014a-1320-11ec-82a8-0242ac130003'),
('2bb716c6-1321-11ec-82a8-0242ac130003','Igor','Rampid','2217747c-1320-11ec-82a8-0242ac130003',6,'c298fec0-1320-11ec-82a8-0242ac130003'),
('2bb7177a-1321-11ec-82a8-0242ac130003','Gregory','Londvar','22177882-1320-11ec-82a8-0242ac130003',1,'c299008c-1320-11ec-82a8-0242ac130003')
ON CONFLICT (employee_id)
DO NOTHING;