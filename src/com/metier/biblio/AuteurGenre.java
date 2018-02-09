package com.metier.biblio;

public class AuteurGenre {
	private int idAuteur;
	private int idGenre;
	
	// constructeurs:
	
	public AuteurGenre()
	{
		this.idAuteur=0;
		this.idGenre=0;
	}
	
	public AuteurGenre(int ida, int idg)
	{
		this.idAuteur=ida;
		this.idGenre= idg;
	}
	
	// getters et setter
	
	public void setIdAuteur(int ida)
	{
		this.idAuteur=ida;
	}
	
	public int getIdAuteur()
	{
		return this.idAuteur;
	}
	
	public void setIdGenre(int idg)
	{
		this.idGenre=idg;
	}
	
	public int GetIdGenre()
	{
		return this.idGenre;
	}
	
	public String toString()
	{
		return "idAuteur = "+Integer.toString(this.idAuteur)+" / idGenre = "+Integer.toString(this.idGenre);
	}

}
