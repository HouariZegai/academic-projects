#--- Exercise 1 ---

# Question 1
SET AUTOCOMMIT=0;
INSERT INTO R VALUES(5, 6);
SAVEPOINT my_savepoint1;
INSERT INTO R VALUES(7, 8);
SAVEPOINT my_savepoint2;
INSERT INTO R VALUES(9, 10);
ROLLBACK TO my_savepoint1;
INSERT INTO R VALUES(11, 12);
INSERT INTO R VALUES(23, 6);
COMMIT;

# question 1 .2
SET AUTOCOMMIT=0;
START TRANSACTION;
SAVEPOINT sp1;
INSERT INTO villes(cp, nom, ville) VALUES ('14000', 'TIARET', 'TIARET');
SAVEPOINT sp2;
INSERT INTO villes(cp, nom, ville) VALUES ('14002', 'SOUGEUR', 'TIARET');
ROLLBACK TO SAVEPOINT sp2;
COMMIT;
SELECT * FROM villes;

#---- Exercise 2 ------

# Question a)
CREATE TRIGGER historique
	AFTER UPDATE ON compte
FOR EACH ROW
BEGIN
	INSERT INTO
		journal_compte(operation, num_compte, date, old_solde, new_solde)
	VALUES
		('update', NEW.num_compte, NOW(), OLD.solde, NEW.solde);
END;

# Question b)
DROP TRIGGER historique;
ALTER TABLE compte DISABLE ALL TRIGGERS;
ALTER TRIGGER historique ENABLE;
ALTER TABLE compte ENABLE ALL TRIGGERS;
ALTER TRIGGER historique DISABLE;
SHOW TRIGGERS;
SHOW TRIGGERS FROM tp2dba;
SELECT * FROM information_schema.`TRIGGERS` WHERE TRIGGER_SCHEMA='tp2dba';

# Exercise 3
CREATE TRIGGER montant_max AFTER UPDATE ON compte
FOR EACH ROW
BEGIN
	IF (OLD.solde - NEW.solde) > 25000.00 THEN
		UPDATE compte SET solde = OLD.solde WHERE num_compte = OLD.num_compte;
	END IF;
END;