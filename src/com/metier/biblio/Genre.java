package com.metier.biblio;

public class Genre {
	private int id;
	private String nom_genre;
	
	public  Genre()
	{
		this.id = 0;
		this.nom_genre="";
	}
	
	public  Genre(int id,String ng)
	{
		this.id = id;
		this.nom_genre=ng;
	}
	
	// getters et setters
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setNomGenre(String ng)
	{
		this.nom_genre=ng;
	}
	
	public String getNomGenre()
	{
		return this.nom_genre;
	}
	
	
	// tostring
	
	public String  toString()
	
	{
		return "Genre id= "+Integer.toString(this.id)+" /Nom: "+this.nom_genre;
	}
	
	}