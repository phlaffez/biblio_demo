package com.metier.biblio;

public class Cote1{
	
	// générée en partir par outils delphi
    private int idCote1;
    private String code;
    private String nom;

   public Cote1(){
       this.idCote1=0;
       this.code="";
       this.nom="";
   }
   public Cote1(int idCote1, String code, String nom){
       this.idCote1=idCote1;
       this.code=code;
       this.nom=nom;
       };
   public Cote1(String code,String no){
       this.code=code;
       this.nom=nom;
       };
       
       // getter et setters
       
       public int getIdCote1()
       {
    	   return this.idCote1;
       }
       
       public void setIdCote1(int id)
       {
    	   this.idCote1 = id;
       }
       
       public String getCode()
       {
    	   return this.code;
       }
       
       public void setCode(String code)
       {
    	   this.code=code;
       }
       
       public String getNom()
       {
    	   return this.nom;
       }
       
       public void setNom(String nom)
       {
    	   this.nom = nom;
       }

}
