## Part 1: Test it with SQL
SELECT DATA_TYPE, COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE table_name = 'job'

## Part 2: Test it with SQL
SELECT name
FROM `techjob_1`.`employes`
WHERE location='St. Louis City'

## Part 3: Test it with SQL
DROP TABLE `techjob_1`.`job`;

## Part 4: Test it with SQL
SELECT distinct s.name, s.description
FROM skill s
JOIN job_skills j ON  j.skills_id = s.id
ORDER by s.name, s.description;
