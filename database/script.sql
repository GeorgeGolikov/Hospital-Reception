DROP TABLE IF EXISTS person;
CREATE TABLE person
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    father_name VARCHAR(20),
    diagnosis_id INT,
    ward_id INT,
    FOREIGN KEY (ward_id) REFERENCES ward (id)
);

DROP TABLE IF EXISTS diagnosis;
CREATE TABLE diagnosis
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS ward;
CREATE TABLE ward
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    max_count INT NOT NULL
);

DROP TABLE IF EXISTS has;
CREATE TABLE has
(
    person_id INTEGER NOT NULL,
    diagnosis_id   INTEGER NOT NULL,
    CONSTRAINT PERSON_FOREIGN_KEY FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT DIAGNOSIS_FOREIGN_KEY FOREIGN KEY (diagnosis_id) REFERENCES diagnosis (id)
);
