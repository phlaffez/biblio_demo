package com.DAO.biblio;

public interface DAO_Noms_Prenoms<T> {
  // Il est necessaire de pouvoir faire une recherche spécifique nom et prenom sur la table auteurs
	
	public Object getByNomPrenom(String n,String p);
}
