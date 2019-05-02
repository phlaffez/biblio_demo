package com.metier.biblio;

public class Cote1{
	
	// générée en partir par outils delphi
    private int idCote1;
    private String code;
    private String nom;
    private String infos;

   public Cote1(){
       this.idCote1=0;
       this.code="";
       this.nom="";
       this.infos="";
   }
   public Cote1(int idCote1, String code, String nom, String infos){
       this.idCote1=idCote1;
       this.code=code.toUpperCase();
       this.nom=nom.toUpperCase();
       this.infos=infos;
       };
   public Cote1(String code,String nom){
       this.code=code.toUpperCase();
       this.nom=nom.toUpperCase();
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
    	   this.code=code.toUpperCase();
       }
       
       public String getNom()
       {
    	   return this.nom;
       }
       
       public void setNom(String nom)
       {
    	   this.nom = nom.toUpperCase();
       }
       
       public String getInfos() {
    	   return this.infos;
       }
       
       public void setInfos(String infos)
       {
    	   this.infos = infos;
       }
       
       
       
       public String toString()
       {
    	   return Integer.toString(this.getIdCote1())+" / "+this.getCode()+" / "+this.getNom()+" / "+this.infos;
       }

}
