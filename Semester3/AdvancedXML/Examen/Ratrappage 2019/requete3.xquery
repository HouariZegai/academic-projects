for $p in doc("Exercice_2.xml")//Personne/Adresse
where $p/code_postal=29000
return <Adresse>{$p/rue}</Adresse>