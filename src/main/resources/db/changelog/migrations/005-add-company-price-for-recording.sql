ALTER TABLE company
ADD COLUMN recording_price int
DEFAULT (200);

UPDATE company
SET recording_price = 300
WHERE id = 2;
UPDATE company
SET recording_price = 400
WHERE id = 3;
UPDATE company
SET recording_price = 1300
WHERE id = 4;
