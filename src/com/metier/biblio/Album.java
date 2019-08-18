package com.metier.biblio;
import com.metier.biblio.Genre;
import com.metier.biblio.Localisation;

import test.metier.biblio.GenreStock;

/*
 * ***************************************************
 *                                                   *
 *                     Albums et stocks              *
 *  classe testée ok le 18/08/2019                   *
 * ***************************************************   
 */

public class Album {

	private int id;
	private int genre;
	private String nom;
	private int lieu;       // je me demande si ici je ne devrais pas mettre un objet Lieu ???
	
	public Album()
	{
		this.id = 0;
		this.genre=0;
		this.nom="";
		this.lieu=0;
	}
	
	public Album(int id, int genre, String nom, int lieu)
	{
		this.id = id;
		this.genre=genre;
		this.nom=nom;
		this.lieu=lieu;
	}
	
	public Album(int genre, String nom, int lieu)
	{
		this.id = 0;
		this.genre=genre;
		this.nom=nom;
		this.lieu=lieu;
	}
	
	public Album(int id, GenreStock genre, String nom, Localisation lieu)
	{
		this.id = id;
		this.genre=genre.getId();
		this.nom=nom;
		this.lieu=lieu.getId();
	}
	
	// getters et setters, toString
	
	public String toString()
	{
		String l = Integer.toString(this.id)+" / "+Integer.toString(this.id)+" / "+this.nom;
		l = l+ " / "+Integer.toString(this.lieu);
		return l;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public void setGenre(int genre)
	{
		this.genre=genre;
	}
	
	public void setGenre(Genre genre)
	{
		this.genre=genre.getId();
	}
	
	public int getGenre()
	{
		return this.genre;
	}
	
	public void setLieu(int lieu)
	{
		this.lieu=lieu;
	}
	
	public void setLieu(Localisation lieu)
	{
		this.lieu=lieu.getId();
	}
	
	public int getLieu()
	{
		return this.lieu;
	}
	
	
}
