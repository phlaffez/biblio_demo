package com.DAO.biblio;

// Comme certaines tables (correspondances livres/aueurs par exemple, n'ont
// pas de champs de nom, je place ici les accès par noms

public interface DAO_Noms <T> {

	
	public Object getByNom(String n);

	// une List<T> n'est pas acceptée. Que faut il faire ? Un Objet pour essayer
}
