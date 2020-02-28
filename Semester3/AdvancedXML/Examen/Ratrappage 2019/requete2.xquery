let $a := for $b in doc("Exercice_2.xml")//Personne[1]/Adresse
return $b/ville/code_postal

let $c := for $b in (doc("Exercice_2.xml")//ville)[1]
return $b/code_postal

return $a[last()] is $c[last()]