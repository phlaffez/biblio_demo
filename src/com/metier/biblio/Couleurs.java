package com.metier.biblio;


/*
 * ***************************************************
 *                                                   *
 *                     Couleurs de timbres           *
 *  classe testée ok le 07/08/2019                   *
 * ***************************************************             
 */
public class Couleurs {
	private int id;
	private String nom;
	
	public Couleurs()
	{
		this.id=0;
		this.nom="";
	}
	
	public Couleurs(int id, String nom) 
	{
		this.id=id;
		this.nom=nom;
	}
	
	// getters et setters
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public String toString()
	{
		// testé le 06/08/2019  OK
		String l=Integer.toString(this.id)+" : "+this.nom;
		return l;
	}

}
