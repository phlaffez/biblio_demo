package com.metier.biblio;
/**  Classe POJO Auteur
 * 
 * @author Philippe
 * @version 1
 * Date: 18/01/2018
 *
 */
public class Auteur 
{
	// Les 7 varialbes correspondantes aux 7 champs de ma table
  private int id;
  private String nom;
  private String prenom;
  private int id_pays;
  private int annee_naiss;
  private int annee_deces;
  private String infos;
  
  /**  Constructeur par défaut
   * @author Philippe
   * @version 1
   * Date: 18/01/2018
   * Pas de paramètre
   */
  public void Auteur()
  {
	  
  }
   
 /** Constructeur a minima
  * @author Philippe
  * @version 1
  * Date: 18/01/2018
  * @param nom
  *   Le nom de l'auteur
  * @param prenom
  *   Le prenom de l'auteur
  */
  public void Auteur(String nom, String prenom)
  {
	  
  }
  
/** Consteurcteur complet
 * @author Philippe
 * @version 1
 * Date: 18/01/2018
 * @param id
 *   identifiant unique dans la table, ou 0 si objet créé par le 
 *   programme poun un nouvel auteur
 *  @param nom
  *   Le nom de l'auteur
  * @param prenom
  *   Le prenom de l'auteur
  *   @param id_pays
  *     l'identifiant du pays de l'auteur dans la table pays
  *     @param annee_naiss
  *       L'année de naissance de l'auteur
  *       @param annee_deces
  *         lL'année de décès de l'auteur
  *         @param infos
  *           Informations diverses sur l'auteur
 * 
 */
  
  public void Auteur (int id, String nom, String prenom, int id_pays,
		  int annee_naiss,int annee_deces,String infos)
  {
	  
  }
  
  
  // Getters et setters
  /**  affecte l'identificateur d'enregistrement (id)*/
  public void setId()
  {
	  
  }
  
  /** retourne l'identificateur de l'enregistrement */
 
  public int getId()
  {
	  return 0;
  }
  
  
  /**  affecte le nom del'auteur*/
  public void setNom()
  {

  }
  
  /** retourne le nom de l'auteur */
 
  public String getNom()
  {
	  return "";
  }
  
  
  /**  affecte le prenom del'auteur*/
  public void setPrenom()
  {

  }
  
  /** retourne le prenom de l'auteur */
 
  public String getPrenom()
  {
	  return "";
  }
  
  
  
  /**  affecte l'identificateur du pays */
  public void setId_pays()
  {
	  
  }
  
  /** retourne l'identificateur du pays */
 
  public int getId_pays()
  {
	  return 0;
  }

  /**  affecte l'année de naissance*/
  public void setAnnee_naiss()
  {
	  
  }
  
  /** retourne l'année de naissance */
 
  public int getAnnee_naiss()
  {
	  return 0;
  }
  
  /**  affecte le champ info*/
  public void setInfo()
  {

  }
  
  /** retourne le champ */
 
  public String getInfo()
  {
	  return "";
  }
  
  /** toString pour récuper quelque chose pour un println   */
  
  public String toString()
  {
	  return "";
  }
}


