-- Structure of table etudiant
CREATE TABLE etu (
	pknumsecu CHAR(13) PRIMARY KEY,
	knumetu CHAR(20) UNIQUE NOT NULL,
	nom VARCHAR(50),
	prenom VARCHAR(50)
);

-- Data of table etudiant
INSERT INTO etu (pknumsecu, knumetu, nom, prenom)
	VALUES ('1800675001066', 'AB937098X', 'Dupont', 'Pierre');
INSERT INTO etu (pknumsecu, knumetu, nom, prenom)
	VALUES ('282047500124', 'XGB67668', 'Durand', 'Anne');

-- Structure of table uv
CREATE TABLE uv (
	pkcode CHAR(4) NOT NULL,
	fketu CHAR(13) NOT NULL,
	PRIMARY KEY (pkcode, fketu),
	FOREIGN KEY (fketu) REFERENCES etu(pknumsecu)
);

-- Data of table uv
INSERT INTO uv (pkcode, fketu)
	VALUES ('NF17', '1800675001066');
INSERT INTO uv (pkcode, fketu)
	VALUES ('NF26', '1800675001066');
INSERT INTO uv (pkcode, fketu)
	VALUES ('NF29', '1800675001066');

-- Delete all data from table uv
DELETE FROM uv;
-- Delete all data from table etu
DELETE FROM etu;

-- -------------------------------------
