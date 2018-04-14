package com.DAO.biblio;

// Les tables de la base de données
public enum BddTables {

	AUTEURS("auteurs"),
	AUTEURLANGUE("auteur_langue"),
	AUTEURGENRE("auteur_genre"),
	GENRES("genres"),
	LANGUES("langues"),
	LIVRES("livres"),
	LIVREAUTEURS("livre_auteurs"),
	PAYS("pays"),
	RESUMELIVRES("resume_livres");
	
	private String name = "";

	BddTables(String n) {
		name = n;
	}

	public String toString() {
		return name;
	}
}
