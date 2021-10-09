ALTER TABLE recording
ADD company_id BIGINT REFERENCES company (id);