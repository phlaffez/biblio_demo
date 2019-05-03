package com.metier.biblio;

public class Cote4{
    private int idCote4;           // le 13/12/2018 je me rend compte que ce champ est inutile.
                                   // car la cote4 est un compteur, et le chiffre cote3
                                   // est unique si on utilise une cote a 3 niveaux + compteur
                                   // du style SER/ESP/OSS/12  La 4eme table est même inutile
                                   // ou alors je fais une cote à 5 crans et il faut un 
                                   // champs nom et un champ code dans la table Cote4. Que faire ?
                                  // modifier Cote4 pour ajouter les 2 champs supplémentaires
    private int cote3;
    private float compteur;
    private String code;
    private String nom;
    private String infos;

   public Cote4(){
       this.idCote4=0;
       this.cote3=0;
       this.compteur=0.0f;
       this.nom="";
       this.code="";
       this.infos="";
       
   }
   public Cote4(int idCote4, String code, String nom, int cote3, float compteur,String infos){
       this.idCote4=idCote4;
       this.cote3=cote3;
       this.compteur=compteur;
       this.nom=nom;
       this.code=code;
       this.infos=infos;
       };
       
       // getters et setters
       
       public int getIdCote4()
       {
    	   return this.idCote4;
       }
       
       public void setIdCote4(int id)
       {
    	   this.idCote4=id;
       }
       
       
       public String getCode()
       {
    	   return this.code;
       }
       
       public void setCode(String code)
       {
    	   this.code = code;
       }
       
       public String getNom()
       {
    	   return this.nom;
       }
       
       public void setNom(String nom)
       {
    	   this.nom = nom;
       }
       
       public int getCote3()
       {
    	   return this.cote3;
       }
       
       public void setCote3(int id)
       {
    	   this.cote3=id;
       }
       
       public float getCompteur()
       {
    	   return this.compteur;
       }
       
       public void setCompteur(float c)
       {
    	   this.compteur=c;
       }
       
       public String toString()
       {
    	    
    	    return Integer.toString(this.getIdCote4())+" / "+this.code+" / "+this.nom+" / "+Integer.toString(this.getCote3())+" / "+Float.toString(this.getCompteur())+
    	    		" / "+this.infos;
       }
       
       
       public String getInfos()
       {
    	   return this.infos;
       }
       
       public void setInfos(String infos)
       {
    	   this.infos = infos;
       }
       


}
