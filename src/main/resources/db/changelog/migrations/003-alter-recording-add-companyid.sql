ALTER TABLE recording
ADD company_id BIGINT
DEFAULT (2);

ALTER TABLE recording
ADD FOREIGN KEY (company_id)
REFERENCES company (id);