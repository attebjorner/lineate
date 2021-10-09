ALTER TABLE recording
DROP COLUMN company_id;

CREATE TABLE company_recording
(
    company_id BIGINT REFERENCES company (id),
    recording_id BIGINT REFERENCES recording (id)
);

INSERT INTO company_recording (company_id, recording_id) VALUES
(1, 2),
(2, 3),
(3, 4),
(4, 1),
(1, 1),
(1, 3),
(4, 3);
commit;
