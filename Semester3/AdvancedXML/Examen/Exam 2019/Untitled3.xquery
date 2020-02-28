for $t in doc("Annexe_Ex_2.xml")//titre
for $att in $t/(@*) | $t
return if ($att instance of attribute()) then element {name($att)}{string($att)}
else element att {attribute {name($t)} {string($t)}, string($t)}