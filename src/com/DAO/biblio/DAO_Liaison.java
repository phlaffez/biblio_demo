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
	
	public boolean isPresent(T obj);
	
}

	


