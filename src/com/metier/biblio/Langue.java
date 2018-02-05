package com.metier.biblio;
/**  Classe POJO Langue
 * 
 * @author Philippe LAFFEZ
 * @version 1
 * Date: 25/01/2018
 *
 */

public class Langue {
	
	private int idLangue;
	private String nomLangue;
	private int nbre;
	
	
	 /**  Constructeur par défaut
	   * @author Philippe LAFFEZ
	   * @version 1
	   * Date: 25/01/2018
	   * Pas de paramètre - probablement inutile
	   */
	public Langue()
	{
		this.idLangue = 0;
		this.nomLangue = "";
		this.nbre=0;
	}
	
	
	/** Consturcteur complet
	 * @author Philippe
	 * @version 1
	 * Date: 25/01/2018
	 * @param id
	 *   identifiant unique dans la table, ou 0 si objet créé par le 
	 *   programme pour une nouvelle langue
	 *  @param nom
	  *   Le nom de la langue
	 * 
	 */
	public Langue(int id, String nom,int nbre)
	{
		this.idLangue = id;
		this.nomLangue = nom;
		this.nbre=nbre;
	}
	
	
	// Getters et setters
	  /**  affecte l'identificateur d'enregistrement (id)*/
	  public void setId(int id)
	  {
		  this.idLangue=id;
	  }
	  
	  /** retourne l'identificateur de l'enregistrement */
	 
	  public int getId()
	  {
		  return this.idLangue;
	  }
	  
	  
	  /**  affecte le nom dela langue */
	  public void setNom(String nom)
	  {
	   this.nomLangue=nom;
	  }
	  
	  /** retourne le nom de la langue */
	 
	  public String getNom()
	  {
		  return this.nomLangue;
	  }
	  
	  /** affecte le nombre d'ouvrage dans cette langue */
	  
	  public void setNombre(int nombre)
	  {
		  this.nbre = nombre;
	  }
	  /** renvoie le nombre d'ouvrages dans cette langue */
	  
	  public int getNombre()
	  {
		  return this.nbre;
	  }
	  
	  public String toString()
	  {
	    String l = "Langue: "+this.getNom()+"\n";
	    l=l+"Id = "+this.getId()+"   Nombre: "+this.getNombre();
	    return l;
	  }
	  
	   

}

