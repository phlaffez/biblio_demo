package com.metier.biblio;

/**  Classe POJO Localisation
 * 
 * @author Philippe LAFFEZ
 * @version 1
 * Date: 04/09/2018
 *
 */

public class Localisation {
	
	private int id;
	private String lieu;
	
	 /**  Constructeur par défaut
	   * @author Philippe LAFFEZ
	   * @version 1
	   * Date: 04/09/2018
	   * Pas de paramètre 
	   */

	public Localisation() {
		 this.id=0;
		 this.lieu="";
	}
		
			/** Constucteur complet
			 * @author Philippe LAFFEZ
			 * @version 1
			 * Date: 04/09/2018
			 * @param id
			 *   identifiant unique dans la table, ou 0 si objet créé par le 
			 *   programme pour un nouveau lieu
			 *  @param lieu
			  *   Le nom de l'emplacement
			 * 
			 */
		
	public Localisation(int id,String lieu) {
		 this.id=id;
		 this.lieu=lieu;
	}	
		
	// getters et setters
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setLieu(String lieu) {
		this.lieu=lieu;
	}
	
	public String getLieu() {
		return this.lieu;
	}
	
	// toString
	
	public String toString() {
		return Integer.toString(this.id)+":"+this.lieu;
	}
		
		
		
		
		
		
		
		
		
	
}
