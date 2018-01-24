package com.metier.biblio;
/**  Classe POJO Auteur
 * 
 * @author Philippe LAFFEZ
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
   * @author Philippe LAFFEZ
   * @version 1
   * Date: 18/01/2018
   * Pas de paramètre
   */
  public Auteur()
  {
	  
	  this.nom="";
	  this.prenom="";
	  this.id=0;
	  this.id_pays=0;
	  this.annee_naiss=0;
	  this.annee_deces=0;
	  this.infos="";
  }
   
 /** Constructeur a minima
  * @author Philippe LAFFEZ
  * @version 1
  * Date: 18/01/2018
  * @param nom
  *   Le nom de l'auteur
  * @param prenom
  *   Le prenom de l'auteur
  */
 public Auteur(String nom, String prenom)
  {
	  this.nom=nom;
	  this.prenom=prenom;
	  this.id=0;
	  this.id_pays=0;
	  this.annee_naiss=0;
	  this.annee_deces=0;
	  this.infos="";
  }
  
/** Consturcteur complet
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
  
  
public Auteur(int id,String nom, String prenom,int idp, int anais, int adec,String infos)
  {
	  this.nom=nom;
	  this.prenom=prenom;
	  this.id=id;
	  this.id_pays=idp;
	  this.annee_naiss=anais;
	  this.annee_deces=adec;
	  this.infos=infos;
  }
  

  
  
  // Getters et setters
  /**  affecte l'identificateur d'enregistrement (id)*/
  public void setId(int id)
  {
	  this.id=id;
  }
  
  /** retourne l'identificateur de l'enregistrement */
 
  public int getId()
  {
	  return this.id;
  }
  
  
  /**  affecte le nom del'auteur*/
  public void setNom(String nom)
  {
   this.nom=nom;
  }
  
  /** retourne le nom de l'auteur */
 
  public String getNom()
  {
	  return this.nom;
  }
  
  
  /**  affecte le prenom del'auteur*/
  public void setPrenom(String prenom)
  {
   this.prenom=prenom;
  }
  
  /** retourne le prenom de l'auteur */
 
  public String getPrenom()
  {
	  return this.prenom;
  }
  
  
  
  /**  affecte l'identificateur du pays */
  public void setId_pays(int idp)
  {
	  this.id_pays=idp;

  }
  
  /** retourne l'identificateur du pays */
 
  public int getId_pays()
  {
	  return this.id_pays;
  }

  /**  affecte l'année de naissance*/
  public void setAnnee_naiss(int anais)
  {
	  this.annee_naiss=anais;
  }
  
  /** retourne l'année de naissance */
 
  public int getAnnee_naiss()
  {
	  return this.annee_naiss;
  }
  
  /**  affecte l'année de naissance*/
  public void setAnnee_deces(int ades)
  {
	  this.annee_deces=ades;
  }
  
  /** retourne l'année de décès */
 
  public int getAnnee_deces()
  {
	  return this.annee_deces;
  }
  
  /**  affecte le champ info*/
  public void setInfo(String infos)
  {
    this.infos = infos;
  }
  
  /** retourne le champ infos*/
 
  public String getInfo()
  {
	  return this.infos;
  }
  
  /** toString pour récuper quelque chose pour un println   */
  
  public String toString()
  {
	  String chaine = "Nom, prenom: "+this.nom+" "+this.prenom+"\n";
	  chaine = chaine+"id= "+this.id+"  idpays= "+this.id_pays;
	  chaine = chaine +" naissance: "+this.annee_naiss+" Dece: "+this.annee_deces+"\n";
	  chaine= chaine+" Infos:\n\n"+this.infos;
	  return chaine;
  }
}


