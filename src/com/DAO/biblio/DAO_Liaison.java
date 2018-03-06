package com.DAO.biblio;

import com.outils.biblio.Cles;

/**  Interface pourles recherches dans les tables de liaisons
 * Les méthodes peremettent de faire des recherches dans cestables
 * selon une clé
 * @author Philippe LAFFEZ
 * @version 1
 * Date: 28/02/2018
 *
 */

public interface DAO_Liaison <T>{
	
	public Object getByCleLiaison(Cles cle, int id);
	/** Les tables de laison ont deux champs. cle représente le nom du champ
	 * selon lequel se fera la recherche, id la valeur recherchée.
	 * Renverra uneliste d'entiers correspondants au deuxième champ de la table, donc une 
	 * recherche dans le style SELECT cle1 FROM table WHERE cle2 = id
	 * si la valeurcle1 est passée comme paramètre de clé
	 * */
	
	public Object getListeByCleLiaison(Cles cle, int id);
	/** renvoie une liste d'objet métier pour une clé donnée, par exemple tous
	 * les livres de l'auteur id "id"
	 * 
	 * @param cle: la clé suivant laquelle on fait la recherche, par exemple id_auteur
	 * @return une Liste d'objet correspondant à cette clé fixe, par exemple une
	 * liste de livre pour la table livre_auteurs avec la clé id_auteur, ou la liste
	 * des auteurs pour la même table avec la clé id_livre
	 * id: la valeur de la clé
	 * 
	 * 
	 */
	
	public boolean deleteByCleLiaison(Cles cle, int id);
	/**
	 * Permet d'effacer tous les enregistrement de la table de liaison pour 
	 * une valeur de clé donnée
	 * @param cle: la clé servant àla recherche. Par exemple id_livre pour supprimer
	 * tous les auteurs d'un livre donné dans la table livre_auteurs
	 * @param id  La valeur de la clé (identificateur de l'auteur dans l'exemple ci dessus
	 * @return un boolean, true si ça s'est bien passé
	 */
	
	public boolean isPresent(T obj);
	
}

	


