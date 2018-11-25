-- Delete Database Gestion Commercial if exists
DROP DATABASE IF EXISTS gestionCommercialDb;

-- Create database Gestion Commercial
CREATE DATABASE gestionCommercialDb;

-- Branchée vers la base de donnée Gestion Commercial
USE gestionCommercialDb;

-- Structure of table Socièté
CREATE TABLE Societe (
	NomSociete VARCHAR(100) PRIMARY KEY,
	Adresse VARCHAR(150),
	CodePostal VARCHAR(5),
	Ville VARCHAR(40),
	Telephone VARCHAR(20),
	Fax VARCHAR(20),
	Email VARCHAR(40),
	Logo BLOB
);

-- Structure of table Login
CREATE TABLE Login (
	IDLogin INT(8) AUTO_INCREMENT PRIMARY KEY,
	NomUtilisateur VARCHAR(40),
	MotDePasse VARCHAR(40)
);

-- Structure of table Cedex
CREATE TABLE Cedex (
	IDCedex INT(8) AUTO_INCREMENT PRIMARY KEY,
	CodePostal VARCHAR(5),
	Ville VARCHAR(40)
);

-- Structure of table Action Possible
CREATE TABLE ActionPossible (
	IDActionPossible INT(8) AUTO_INCREMENT PRIMARY KEY,
	LibAction VARCHAR(40)
);

-- Structure of table Action Réalisee
CREATE TABLE ActionRealisee (
	IDActionRealise INT(8) AUTO_INCREMENT PRIMARY KEY,
	IDActionPossible INT(8),
	NumClient INT(8),
	Login VARCHAR(50) DEFAULT 0,
	DateAction DATE,
	CleOrdre VARCHAR(16),
	FOREIGN KEY (IDActionPossible) REFERENCES ActionPossible(IDActionPossible),
	FOREIGN KEY (NumClient) REFERENCES Client(NumClient)
);

-- Structure of table Ligne Facture
CREATE TABLE LigneFac (
	IDLigneFac INT(8) AUTO_INCREMENT PRIMARY KEY,
	NumFacture INT(8),
	Reference VARCHAR(20),
	LibProd VARCHAR(40),
	Quantite INT(4),
	PrixVente DOUBLE,
	Remise DOUBLE,
	TauxTva DOUBLE,
	IDLigneCde DOUBLE,
	OrdreAffichage INT(2) DEFAULT 0,
	FOREIGN KEY (Reference) REFERENCES Produit (Reference),
	FOREIGN KEY (NumFacture) REFERENCES Facture (NumFacture),
	FOREIGN KEY (IDLigneCde) REFERENCES LigneCde (IDLigneCde)
);

-- Structure of table Avoir
CREATE TABLE Avoir (
	NumAvoir INT(8) AUTO_INCREMENT PRIMARY KEY,
	DateAvoir DATE,
	NumClient INT(8),
	IDAdresseFacturation INT(8),
	IDModeReglement INT(8),
	TotalHt DOUBLE,
	TotalTva DOUBLE,
	TotalTtc DOUBLE,
	Utilise BOOLEAN,
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	Observations TEXT,
	FOREIGN KEY (NumClient) REFERENCES Client (NumClient)
);

-- Structure of table Client
CREATE TABLE Client (
	NumClient INT(8) AUTO_INCREMENT PRIMARY KEY,
	Societe VARCHAR(40),
	Civilite VARCHAR(5),
	NomClient VARCHAR(40),
	Prenom VARCHAR(50),
	Adresse VARCHAR(150),
	CodePostal VARCHAR(5),
	Ville VARCHAR(40),
	Pays VARCHAR(40),
	Telephone VARCHAR(20),
	Mobile VARCHAR(20),
	Fax VARCHAR(20),
	Email VARCHAR(40),
	Type INT(2) DEFAULT 0,
	LivreMemeAdresse BOOLEAN DEFAULT 0,
	FactureMemeAdresse  BOOLEAN DEFAULT 0,
	ExemptTva  BOOLEAN DEFAULT 0,
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	AuteurModif VARCHAR(40),
	DateModif DATE,
	Observations TEXT,
	FOREIGN KEY (NumClient) REFERENCES Client (NumClient)
);

-- Structure of table Adresse Livraison
CREATE TABLE Adr_Livraison (
	IDAdresseLivraison INT(8) AUTO_INCREMENT PRIMARY KEY,
	NumClient INT(8),
	Civilite VARCHAR(5),
	Contact VARCHAR(40),
	Adresse VARCHAR(150),
	CodePostal VARCHAR(5),
	Ville VARCHAR(40),
	Pays VARCHAR(40),
	Telephone VARCHAR(20),
	Mobile VARCHAR(20),
	Fax VARCHAR(20),
	Email VARCHAR(40),
	Observations TEXT,
  	FOREIGN KEY (NumClient) REFERENCES Client (NumClient)
);

-- Structure of table Devis
CREATE table Devis (
	IDDevis INT(8) AUTO_INCREMENT PRIMARY KEY,
	DateDevis DATE,
	NumClient INT(8),
	TotalHt DOUBLE,
	TotalTva DOUBLE,
	TotalTtc DOUBLE,
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	Observations TEXT,
  	FOREIGN KEY (NumClient) REFERENCES Client (NumClient)
);

-- Structure of table Adresse Facturation
CREATE TABLE Adr_Facturation (
	IDAdresseFacturation INT(8) AUTO_INCREMENT PRIMARY KEY,
	NumClient INT(8),
	Civilite VARCHAR(5),
	Contact VARCHAR(40),
	Adresse VARCqHAR(150),
	CodePostal VARCHAR(5),
	Ville VARCHAR(40),
	Pays VARCHAR(40),
	Telephone VARCHAR(20),
	Mobile VARCHAR(20),
	Fax VARCHAR(20),
	Email VARCHAR(40),
	Observations TEXT,
  	FOREIGN KEY (NumClient) REFERENCES Client (NumClient)
);

-- Structure of table Commande
CREATE TABLE Commande (
	NumCommande INT(8) AUTO_INCREMENT PRIMARY KEY,
	DateCommande DATE,
	NumClient INT(8),
	IDAdresseLivraison INT(8),
	IDModeLivraison INT(8),
	IDAdresseFacturation INT(8),
	IDModeReglement INT(8),
	TotalHt DOUBLE,
	TotalTva DOUBLE,
	TotalFraisPort DOUBLE,
	TotalTtc DOUBLE,
	EtatCommande INT(2),
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	Observations TEXT,
   	FOREIGN KEY (NumClient) REFERENCES Client (NumClient),
	FOREIGN KEY (IDModeReglement) REFERENCES ModeReglement (IDModeReglement),
	FOREIGN KEY (IDAdresseFacturation) REFERENCES Adr_Facturation (IDAdresseFacturation),
  	FOREIGN KEY (IDAdresseLivraison) REFERENCES Adr_Livraison (IDAdresseLivraison)
);

-- Structure of table Ligne Commande
CREATE TABLE LigneCde (
	IDLigneCde INT(8) PRIMARY KEY,
	NumCommande INT(8),
	Reference VARCHAR(20),
	LibProd VARCHAR(40),
	Quantite INT(4),
	PrixVente DOUBLE DEFAULT 0.000000,
	Remise DOUBLE,
	TauxTva DOUBLE,
	Livre BOOLEAN,
	Total DOUBLE,
	OrdreAffichage INT(2),
	CleNumCommandeOrdreAffi DOUBLE(10),
	OptimCleCompOrdreNumCo DOUBLE(10),
	FOREIGN KEY (TauxTVA) REFERENCES TVA (TauxTVA),
	FOREIGN KEY (Reference) REFERENCES Produit (Reference),
	FOREIGN KEY (NumCommande) REFERENCES Commande (NumCommande)
);

-- Structure of table Mode Règlement
CREATE TABLE ModeReglement (
	IDModeReglement INT(8) AUTO_INCREMENT PRIMARY KEY,
	LibModeReglement VARCHAR(40)
);

-- Structure of table Ligne Avoir
CREATE TABLE LigneAvoir (
	IDLigneAvoir INT(8) PRIMARY KEY,
	NumAvoir INT(8),
	Reference VARCHAR(255),
	LibProd VARCHAR(255),
	Quantite INT(4),
	Prix DOUBLE,
	TauxTva DOUBLE,
	IDLigneFac INT(8),
	FOREIGN KEY (Reference) REFERENCES Produit (Reference),
	FOREIGN KEY (NumAvoir) REFERENCES Avoir (NumAvoir),
	FOREIGN KEY (IDLigneFac) REFERENCES LigneFac (IDLigneFac)
);

-- Structure of table Stock
CREATE TABLE Stock (
	Reference VARCHAR(20) PRIMARY KEY,
	QteEnStock INT(4),
	QteStockVirtuel INT(4),
	AuteurModif VARCHAR(40),
	DateModif DATE
);

-- Structure of table Mode Livraison
CREATE TABLE ModeLivraison (
	IDModeLivraison INT(8) AUTO_INCREMENT PRIMARY KEY,
	LibModeReglement VARCHAR(40)
);

-- Structure of table Produit
CREATE TABLE Produit (
	Reference VARCHAR(20) PRIMARY KEY,
	genCode VARCHAR(40),
	codeBarre VARCHAR(40),
	LibProd VARCHAR(40),
	description TEXT,
	PrixHt DOUBLE DEFAULT 0.000000,
	QteReappro INT(4) DEFAULT 0,
	QteMini INT(4) DEFAULT 0,
	TauxTva DOUBLE DEFAULT 0.000000,
	photo BLOB,
	NumFournisseur INT(8) DEFAULT 0,
	plusAuCatalogue BOOLEAN DEFAULT 0,
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	CodeFamille VARCHAR(40),
	CodePort VARCHAR(20),
	FOREIGN KEY (TauxTVA) REFERENCES TVA (TauxTVA),
	FOREIGN KEY (CodePort) REFERENCES FraisPort (CodePort),
	FOREIGN KEY (CodeFamille) REFERENCES Famille (CodeFamille),
	FOREIGN KEY (NumFournisseur) REFERENCES Fournisseur (NumFournisseur)
);

-- Structure of table Sortie Stock
CREATE TABLE SortieStock (
	IDSortie INT(8) AUTO_INCREMENT PRIMARY KEY,
	Reference VARCHAR(20),
	Quantite INT(4),
	DateSortie DATE,
	Modif VARCHAR(40),
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	Observations TEXT,
	FOREIGN KEY (Reference) REFERENCES Produit (Reference)
);

-- Structure of table Famille
CREATE TABLE Famille (
	CodeFamille VARCHAR(40) PRIMARY KEY,
	Libelle VARCHAR(50)
);

-- Structure of table Frais Port
CREATE table FraisPort (
	CodePort VARCHAR(20) PRIMARY KEY,
	LibFraisPort VARCHAR(40),
	Montant DOUBLE
);

-- Structure of table TVA
CREATE TABLE TVA (
	TauxTva DOUBLE PRIMARY KEY
);

-- Structure of table Entrée Stock
CREATE TABLE EntreeStock (
	IDEntree INT(8) AUTO_INCREMENT PRIMARY key,
	DateAppro DATE,
	Reference VARCHAR(20),
	Quantite INT(4),
	PrixAchat DOUBLE,
	NumFournisseur INT(8),
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	Observations TEXT,
	FOREIGN KEY (NumFournisseur) REFERENCES Fournisseur (NumFournisseur),
	FOREIGN KEY (Reference) REFERENCES Produit (Reference)
);

-- Structure of table Fournisseur
CREATE TABLE Fournisseur (
	NumFournisseur INT(8) AUTO_INCREMENT PRIMARY KEY,
	Societe VARCHAR(40),
	Civilite VARCHAR(5),
	Nom VARCHAR(40),
	Prenom VARCHAR(50),
	Adresse VARCHAR(150),
	CodePostal VARCHAR(5),
	Ville VARCHAR(40),
	Pays VARCHAR(40),
	Telephone VARCHAR(20),
	Mobile VARCHAR(20),
	Fax VARCHAR(20),
	Email VARCHAR(40),
	Observations TEXT
);

-- Structure of table Ligne Devis
CREATE TABLE LigneDevis (
	IDLigneDevis INT(4) PRIMARY KEY,
	Reference VARCHAR(20),
	LibProd VARCHAR(40),
	Quantite INT(4),
	Remise DOUBLE,
	TauxTva DOUBLE,
	IDDevis INT(8),
	PrixVente DOUBLE DEFAULT 0,
	OrdreAffichage INT(2),
	FOREIGN KEY (Reference) REFERENCES Produit (Reference),
	FOREIGN KEY (TauxTVA) REFERENCES TVA (TauxTVA),
	FOREIGN KEY (IDDevis) REFERENCES Devis (IDDevis)
);

-- Structure of table Règlement
CREATE TABLE Reglement (
	IDReglement INT(8) AUTO_INCREMENT PRIMARY KEY,
	DateReglement DATE,
	IDModeReglement INT(8),
	NumFacture INT(8), 
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	Observations TEXT
);

-- Structure of table Facture
CREATE TABLE Facture (
	NumFacture INT(8) AUTO_INCREMENT PRIMARY KEY,
	DateFacture DATE AUTO_INCREMENT,
	NumClient INT(8) AUTO_INCREMENT,
	IDAdresseFacturation INT(8),
	IDModeReglement INT(8),
	TotalHt DOUBLE,
	TotalTva DOUBLE,
	TotalFraisPort DOUBLE,
	TotalTtc DOUBLE,
	Remise DOUBLE,
	Acquittee BOOLEAN,
	SaisiPar VARCHAR(40),
	SaisiLe DATE,
	Observations TEXT,
	NumCommande INT(8),
	FOREIGN KEY (NumClient) REFERENCES Client (NumClient),
	FOREIGN KEY (IDModeReglement) REFERENCES ModeReglement (IDModeReglement),
	FOREIGN KEY (IDAdresseFacturation) REFERENCES Adr_Facturation (IDAdresseFacturation)
);

-- Structure of table Paramètre
CREATE TABLE Parametre (
	IDParametre INT(4) AUTO_INCREMENT PRIMARY KEY,
	NomParametre VARCHAR(30),
	Valuer TEXT
);

