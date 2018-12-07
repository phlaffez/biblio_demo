package com.metier.biblio;

public class Cote3{
    private int idCote3;
    private int cote2;
    private String code;
    private String nom;

   public Cote3(){
       this.idCote3=0;
       this.cote2=0;
       this.code="";
       this.nom="";
   }
   public Cote3(int idCote3, int cote2, String code, String nom){
       this.idCote3=idCote3;
       this.cote2=cote2;
       this.code=code;
       this.nom=nom;
       };
       
       // getters et setters
       
       public int getIdCote3()
       {
    	   return this.hashCode();
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
       
       private String getCode()
       {
    	   return this.code;
       }
       
       private void setCode(String code)
       {
    	   this.code = code;
       }
       
       private String getNom()
       {
    	   return this.nom;
       }
       
       private void setNom(String nom)
       {
    	   this.nom = nom;
       }
       
       
}