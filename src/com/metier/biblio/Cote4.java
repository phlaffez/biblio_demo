package com.metier.biblio;

public class Cote4{
    private int idCote4;
    private int cote3;
    private float compteur;

   public Cote4(){
       this.idCote4=0;
       this.cote3=0;
       this.compteur=0.0f;
   }
   public Cote4(int idCote4, int cote3, float compteur){
       this.idCote4=idCote4;
       this.cote3=cote3;
       this.compteur=compteur;
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
    	    
    	    return Integer.toString(this.getIdCote4())+" / "+Integer.toString(this.getCote3())+" / "+Float.toString(this.getCompteur());
       }


}
