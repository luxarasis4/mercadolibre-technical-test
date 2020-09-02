DROP TABLE IF EXISTS item;
	 
CREATE TABLE item (
	id VARCHAR(250) PRIMARY KEY,
	price FLOAT NOT NULL
);
	 
INSERT INTO item (id, price) VALUES
	('MLA1', 100),
	('MLA2', 210),
	('MLA3', 260),
	('MLA4', 80),
	('MLA5', 90);