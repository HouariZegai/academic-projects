for $p in doc("Exercice_2.xml")//Personne/Adresse
for $k in doc("Exercice_2.xml")//Personne/Adresse/ville
return($p/rue, $k/rue)