package com.metier.biblio;

/*
 * ***************************************************
 *                                                   *
 *                     Monnaies                      *
 *  classe testée ok le                    *
 * ***************************************************             
 */

public class Monnaies {
	
	private int id;
	private String nom;
	private String abrev;
	
	public Monnaies()
	// testé le 08/08/2019  OK
	{
		this.id = 0;
		this.nom = "";
		this.abrev = "";
	}
	
	public Monnaies(int id, String nom, String abrev)
	// testé le 08/08/2019  OK
	{
		this.id = id;
		this.nom = nom;
		this.abrev = abrev;
	}
	
	public Monnaies(String nom, String abrev)
	// testé le 08/08/2019  OK
	{
				this.nom = nom;
		this.abrev = abrev;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public String getAbrev()
	{
		return this.abrev;
	}
	
	public void setAbrev(String abrev)
	{
		this.abrev = abrev;
	}
	
	public String toString()
	{
		// testé le 08/08/2019  OK
		String l=Integer.toString(this.id)+" : "+this.nom+"/  "+this.abrev;
		return l;
	}

}
