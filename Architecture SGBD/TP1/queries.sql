-- Database: tp1_sgbd

-- CREATE DATABASE tp1_sgbd
--     WITH 
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'C'
--     LC_CTYPE = 'C'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;

-- ============================================================
-- Create table Client

CREATE TABLE Client(
	num_client SERIAL PRIMARY KEY,
	civ VARCHAR(25) NOT NULL,
	prenom_client VARCHAR(25) NOT NULL,
	nom_client VARCHAR(25) NOT NULL,
	date_naiss DATE,
	adresse VARCHAR(300) NOT NULL,
	tel_prof VARCHAR(20),
	tel_priv VARCHAR(20),
	fax VARCHAR(20)
);

-- Insert in Client table

INSERT INTO Client 	(civ, prenom_client, nom_client, date_naiss, adresse, tel_prof, tel_priv) 
VALUES 	('Mme', 'Cherifa', 'MOHABOUBA', '1957-08-08', 'CITE 1013 LOGTS BT 61 Alger', '0561381813', '0562458714');

INSERT INTO Client (civ, prenom_client, nom_client, date_naiss, adresse, tel_prof, tel_priv) 
VALUES 	('Mme', 'Lamia', 'TAHMI', '1955-12-31', 'CITE BOGHEDJARAH BATIMENT 38-Bach Djerrah-Alger', '0562467849', '0561392487');

INSERT INTO Client 	(civ, prenom_client, nom_client, date_naiss, adresse, tel_prof, tel_priv, fax) 
VALUES 	('Mle', 'Ghania', 'DIAF AMROUNI', '1955-12-31', '43 RUE ABDERRAHMANE SBAA BELLE VUE-EL HARRACH-ALGER', '0523894562', '0619430945', '0562784254');

-- ============================================================
-- Create table Employe

CREATE TABLE Employe(
	num_employe SERIAL PRIMARY KEY,
	nom_employe VARCHAR(25) NOT NULL,
	prenom_employe VARCHAR(25) NOT NULL,
	categorie VARCHAR(25),
	salarie INTEGER NOT NULL
);

-- Insert in table Employe

INSERT INTO Employe (nom_employe, prenom_employe, categorie, salarie) VALUES ('LACHEMI', 'Bouzid', 'Mécanicien', 25000);
INSERT INTO Employe (nom_employe, prenom_employe, categorie, salarie) VALUES ('BOUCHEMLA', 'Elias', 'Assistant', 10000);
INSERT INTO Employe (nom_employe, prenom_employe, salarie) VALUES ('LARDJOUNE', 'Karim', 25000);

-- ============================================================
-- Create table Marque

CREATE TABLE Marque (
	num_marque SERIAL PRIMARY KEY,
	marque VARCHAR(200) NOT NULL,
	pays VARCHAR(200) NOT NULL
);

-- Insert into table Marque

INSERT INTO Marque (marque, pays) VALUES ('LAMBORGHINI', 'ITALIE');
INSERT INTO Marque (marque, pays) VALUES ('AUDI', 'ALLEMAGNE');
INSERT INTO Marque (marque, pays) VALUES ('ROLLS-ROYCE', 'GRANDE-BRETAGE');

-- ============================================================
-- Create table Modele

CREATE TABLE Modele (
	num_modele SERIAL PRIMARY KEY,
	num_marque INTEGER REFERENCES Marque (num_marque),
	modele VARCHAR(200)
);

-- Insert in table Modele

INSERT INTO Modele (num_marque, modele) VALUES (1, 'Diablo');
INSERT INTO Modele (num_marque, modele) VALUES (2, 'Série 5');
INSERT INTO Modele (num_marque, modele) VALUES (3, 'NSX');

-- ============================================================
-- Create table Vehicule

CREATE TABLE Vehicule (
	num_vehicule SERIAL PRIMARY KEY,
	num_client INTEGER NOT NULL REFERENCES Client(num_client),
	num_modele INTEGER NOT NULL REFERENCES Modele(num_modele),
	num_immat VARCHAR(20) NOT NULL,
	annee SMALLINT NOT NULL
);

-- Insert in table Vehicule

INSERT INTO Vehicule (num_vehicule, num_client, num_modele, num_immat, annee) VALUES (1, 2, 2, '00125112151', 1990);
INSERT INTO Vehicule (num_vehicule, num_client, num_modele, num_immat, annee) VALUES (2, 3, 1, '04584818111', 1995);


-- ============================================================
-- Create table Interventions

CREATE TABLE Interventions (
	num_intervention SERIAL PRIMARY KEY,
	num_vehicule INTEGER NOT NULL REFERENCES Vehicule(num_vehicule),
	type_intervention VARCHAR(255) NOT NULL,
	date_debinterv DATE NOT NULL,
	date_fininterv DATE NOT NULL,
	cout_initerv VARCHAR(255)
);

-- Insert in table Interventions

INSERT INTO Interventions (num_vehicule, type_intervention, date_debinterv, date_fininterv, cout_initerv) VALUES (2, 'Réparation', '2006-02-25 09:00:00', '2006-02-26 12:00:00', 30000);
INSERT INTO Interventions (num_vehicule, type_intervention, date_debinterv, date_fininterv, cout_initerv) VALUES (1, 'Réparation', '2006-02-23 09:00:00', '2006-02-24 18:00:00', 10000);

-- ============================================================
-- Create table Interventions

CREATE TABLE Intervenants (
	num_intervention SERIAL REFERENCES Interventions(num_intervention),
	num_employe INTEGER NOT NULL REFERENCES Employe(num_employe),
	date_debut DATE NOT NULL,
	date_fin DATE NOT NULL,
	PRIMARY KEY (num_intervention, num_employe)
);

-- Insert in table Intervenants

INSERT INTO Intervenants (num_intervention, num_employe, date_debut, date_fin) VALUES (1, 1, '2006-02-26 09:00:00', '2006-02-26 12:00:00');
INSERT INTO Intervenants (num_intervention, num_employe, date_debut, date_fin) VALUES (1, 2, '2006-02-25 09:00:00', '2006-02-25 18:00:00');