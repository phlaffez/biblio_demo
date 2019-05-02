package com.metier.biblio;

public class Cote2
{
	
	// générée en partie par programme delphi
    private int idCote2;
    private int cote1;
    private String code;
    private String nom;
    private String infos;

   public Cote2(){
       this.idCote2=0;
       this.cote1=0;
       this.code="";
       this.nom="";
       this.infos="";
   }
   public Cote2(int idCote2, int cote1, String code, String nom, String infos){
       this.idCote2=idCote2;
       this.cote1=cote1;
       this.code=code;
       this.nom=nom;
       this.infos = infos;
       };
       
       // getters et setters

       public int getIdCote2()
       {
    	   return this.idCote2;
       }
       
       public void setIdCote2(int id)
       {
    	   this.idCote2=id;
       }
       
       public int getCote1()
       {
    	   return this.cote1;
       }
       
       public void setCote1(int cote1)
       {
    	   this.cote1=cote1;
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
       
       public String getInfos() {
    	   return this.infos;
       }
       
       public void setInfos(String infos)
       {
    	   this.infos = infos;
       }
       
       public String toString()
       {
    	   return Integer.toString(this.getIdCote2())+" / "+Integer.toString(this.getCote1())+" / "+this.getCode()+" / "+this.getNom()+" / "+this.infos;
       }
       
       

}
