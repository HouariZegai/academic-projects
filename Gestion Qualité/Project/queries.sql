-- Create Database

DROP DATABASE gestion_commercial_db IF EXISTS;

-- Create database
CREATE DATABASE gestion_commercial_db;

-- Branché vers la base de donnée
USE gestion_commercial_db;

-- Structure of table Socièté
CREATE TABLE Societe (
	num_societe INT PRIMARY KEY,
	adresse VARCHAR(255),
	code_postal VARCHAR(5),
	ville VARCHAR(80),
	telephone VARCHAR(20),
	fax VARCHAR(10),
	email VARCHAR(80),
	logo VARCHAR(255)
);

-- Structure of table Login
CREATE TABLE Login (
	id_login INT PRIMARY KEY,
	nom_utilisateur VARCHAR(250) NOT NULL,
	mot_de_passe VARCHAR(250) NOT NULL
);

-- Structure of table Cedex
CREATE TABLE Cedex (
	id_cedex INT PRIMARY KEY,
	code_postal VARCHAR(20) NOT NULL,
	ville VARCHAR(250) NOT NULL
);

-- Structure of table ActionPossible
CREATE TABLE ActionPossible (
	id_action_possible INT PRIMARY KEY,
	lib_action VARCHAR(40)
);

-- Structure of table ActionRealisee
CREATE TABLE ActionRealisee (
	id_action_realise INT PRIMARY KEY,
	id_action_possible INT,
	num_client INT,
	login VARCHAR(50),
	date_action DATE,
	cle_ordre VARCHAR(16)
);

-- Structure of table LigneFac
CREATE TABLE LigneFac (
	id_ligne_fac INT PRIMARY KEY,
	num_facture INT,
	reference VARCHAR(250),
	lib_prod VARCHAR(250),
	quantite INT
);

-- Structure of table Avoir
CREATE TABLE Avoir (
	num_avoir INT PRIMARY KEY,
	date_avoir DATE,
	num_client INT,
	id_adresse_facturation INT,
	id_mode_reglement INT,
	total_ht REAL,
	total_tva REAL,
	total_ttc REAL,
	utilise BOOLEAN,
	saisi_par VARCHAR(40),
);

-- Structure of table Client
CREATE TABLE Client (
	num_client INT PRIMARY KEY,
	societe VARCHAR(255),
	civilite VARCHAR(255),
	nom_client VARCHAR(255),
	prenom VARCHAR(255),
	adresse VARCHAR(255),
	code_postal VARCHAR(255),
	ville VARCHAR(255),
	pays VARCHAR(255),
	telephone VARCHAR(20),
	mobile VARCHAR(20),
	fax VARCHAR(20),
	email VARCHAR(255),
	type INT,
	livre_meme_adresse INT,
	facture_meme_adresse INT,
	exempt_tva INT,
	saisi_par VARCHAR(255),
	saisi_le DATE,
	auteur_modif VARCHAR(255),
	date_modif DATE
);

-- Structure of table AdrLivraison
CREATE TABLE AdrLivraison (
	id_adresse_livraison INT PRIMARY KEY,
	num_client INT,
	civilite VARCHAR(5),
	contact VARCHAR(40),
	adresse VARCHAR(150),
	code_postal VARCHAR(5),
	ville VARCHAR(40),
	pays VARCHAR(40),
	telephone VARCHAR(20),
	mobile VARCHAR(20),
	fax VARCHAR(20),
	email VARCHAR(40),
	observation TEXT
);

-- Structure of table Devis
CREATE table Devis (
	id_devis INT PRIMARY KEY,
	date_devis DATE,
	num_client INT,
	total_ht REAL
	total_tva REAL,
	total_ttc REAL,
	saisi_par VARCHAR(255),
	saisi_le DATE,
	observation VARCHAR(255)
);

-- Structure of table Adresse Facturation
CREATE TABLE AdrFacturation (
	id_adresse_facturation INT PRIMARY KEY,
	num_client INT,
	civilite VARCHAR(5),
	contact VARCHAR(40),
	adresse VARCHAR(150),
	code_postal VARCHAR(5),
	ville VARCHAR(40),
	pays VARCHAR(40),
	telephone VARCHAR(20),
	mobile VARCHAR(20),
	fax VARCHAR(20),
	email VARCHAR(40)
	observation TEXT
);

-- Structure of table Commande
CREATE TABLE Commande (
	num_commande INT PRIMARY KEY,
	date_commande DATE,
	num_client INT,
	id_adresse_livraison INT,
	id_mode_livraison INT,
	id_adresse_facturation INT,
	id_mode_reglement INT,
	total_ht REAL
);

-- Structure of table LigneCde
CREATE TABLE LigneCde (
	id_ligne_cde INT PRIMARY KEY,
	num_commande INT,
	reference VARCHAR(255),
	lib_prod VARCHAR(255),
	quantite INT
);

-- Structure of table ModeReglement
CREATE TABLE ModeReglement (
	id_mode_reglement INT PRIMARY KEY,
	lib_mode_reglement VARCHAR(255)
);

-- Structure of table LigneAvoir
CREATE TABLE LigneAvoir (
	id_ligne_avoir INT PRIMARY KEY,
	num_avoir INT,
	reference VARCHAR(255),
	lib_prod VARCHAR(255),
	quantite INT
);

-- Structure of table Stock
CREATE TABLE Stock (
	reference VARCHAR PRIMARY KEY,
	qte_en_stock INT,
	qte_stock_virtuel INT,
	auteur_modif VARCHAR(255),
	date_modif DATE
);

-- Structure of table ModeLivraison
CREATE TABLE ModeLivraison (
	id_mode_livraison INT PRIMARY KEY,
	lib_mode_reglement VARCHAR(255)
);

-- Structure of table Produit
CREATE TABLE Produit (
	reference VARCHAR(255) PRIMARY KEY,
	gen_code VARCHAR(255),
	code_barre VARCHAR(255),
	lib_prod VARCHAR(255),
	description VARCHAR(255),
	prix_ht REAL,
	qte_reappro VARCHAR(255),
	qte_mini VARCHAR(255)
	taux_tva .,
	photo VARCHAR(255),
	num_fournisseur INT
);

-- Structure of table SortieStock
CREATE TABLE SortieStock (
	id_sortie INT PRIMARY KEY,
	reference VARCHAR(255),
	quantite INT,
	date_sortie DATE,
	modif VARCHAR(255)
);

-- Structure of table Famille
CREATE TABLE Famille (
	code_famille INT PRIMARY KEY,
	libelle VARCHAR(255)
);

-- Structure of table Frais Port
CREATE table FraisPort (
	code_port VARCHAR(255) PRIMARY KEY,
	lib_frais_port VARCHAR(255),
	montant REAL
);

-- Structure of table TVA
CREATE TABLE TVA (
	taux_tva .
);

-- Structure of table Entrée Stock
CREATE TABLE EntreeStock (
	id_entree INT PRIMARY key,
	date_appro DATE,
	reference VARCHAR(255),
	quantite INT,
	prix_achat .,
	num_fournisseur INT,
	saisi_par VARCHAR(255),
	saisi_le DATE,
	observation VARCHAR(255)
);

-- Structure of table Fournisseur
CREATE TABLE Fournisseur (
	num_fournisseur INT PRIMARY KEY,
	societe VARCHAR(255),
	civilite VARCHAR(255),
	nom VARCHAR(255),
	prenom VARCHAR(255),
	adresse VARCHAR(255),
	code_postal VARCHAR(5),
	ville VARCHAR(255),
	pays VARCHAR(255),
	telephone VARCHAR(20)
);

-- Structure of table LigneDevis
CREATE TABLE LigneDevis (
	id_ligne_devis INT PRIMARY KEY,
	reference VARCHAR(255),
	lib_prod VARCHAR(255)
);

-- Structure of table Reglement
CREATE TABLE Reglement (
	id_reglement INT PRIMARY KEY,
	date_reglement DATE,
	id_mode_reglement INT,
	num_facture INT,
	saisi_par VARCHAR(255)
);

-- Structure of table Facture
CREATE TABLE Facture (
	num_facture INT PRIMARY KEY,
	date_facture DATE,
	num_client INT,
	id_adresse_facturation INT,
	id_mode_reglement INT,
	total_ht REAL,
	total_tva REAL,
	total_frais_port REAL,
	total_ttc REAL,
	remise REAL
);

-- Structure of table Paramètre
CREATE TABLE Parametre (
	id_parametre INT PRIMARY KEY,
	nom_parametre VARCHAR(255),
	valuer VARCHAR(255)
);