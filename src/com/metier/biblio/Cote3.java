package com.metier.biblio;

public class Cote3{
    private int idCote3;
    private int cote2;
    private String code;
    private String nom;
    private String infos;

   public Cote3(){
       this.idCote3=0;
       this.cote2=0;
       this.code="";
       this.nom="";
       this.infos="";
   }
   public Cote3(int idCote3, int cote2, String code, String nom, String infos){
       this.idCote3=idCote3;
       this.cote2=cote2;
       this.code=code;
       this.nom=nom;
       this.infos = infos;
       };
       
       // getters et setters
       
       public int getIdCote3()
       {
    	   return this.idCote3;
       }
       
       public void setIdCote3(int id)
       {
    	   this.idCote3 = id;
       }
       
       public int getCote2()
       {
    	   return this.cote2;
       }
       
       public void setCote2(int cote2)
       {
    	   this.cote2=cote2;
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
       
       public String getInfos() {
    	   return this.infos;
       }
       
       public void setInfos(String infos)
       {
    	   this.infos = infos;
       }
       
       public String toString()
       {
    	   
    	    return Integer.toString(this.idCote3)+" / "+Integer.toString(this.cote2)+" / "+this.getCode()+" / "+this.getNom()+" / "+this.infos;
       }
}