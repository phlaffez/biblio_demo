package com.metier.biblio;
/**  Classe POJO Pays
 * 
 * @author Philippe LAFFEZ
 * @version 1
 * Date: 07/02/2018
 *
 */

public class Pays {

	private int idPays;
	private String nomPays;
	
	
	
	 /**  Constructeur par défaut
	   * @author Philippe LAFFEZ
	   * @version 1
	   * Date: 07/02/2018
	   * Pas de paramètre - probablement inutile
	   */
	public Pays()
	{
		this.idPays = 0;
		this.nomPays = "";
		
	}
	
	/** Consturcteur complet
	 * @author Philippe
	 * @version 1
	 * Date: 07/02/2018
	 * @param id
	 *   identifiant unique dans la table, ou 0 si objet créé par le 
	 *   programme pour un nouveau pays
	 *  @param nom
	  *   Le nom du pays
	  *  
	  
	 * 
	 */
	public Pays(int id, String nom)
	{
		this.idPays = id;
		this.nomPays = nom;
		
	}
	
	
	// Getters et setters  07/02/2018
	
		  /**  affecte l'identificateur d'enregistrement (id)*/
		  public void setId(int id)
		  {
			  this.idPays=id;
		  }
		  
		  /** retourne l'identificateur de l'enregistrement */
		 
		  public int getId()
		  {
			  return this.idPays;
		  }
		  
		  
		  /**  affecte le nom du pays */
		  public void setNom(String nom)
		  {
		   this.nomPays=nom;
		  }
		  
		  /** retourne le nom du pays */
		 
		  public String getNom()
		  {
			  return this.nomPays;
		  }
		  
		  /** affecte le nombre   */
		  
		
		  
		  public String toString()
		  {
		    String l = "Pays: "+this.getNom()+"\n";
		    l=l+"Id = "+this.getId()+"   Nombre: ";
		    return l;
		  }
	
	
	
	
}
