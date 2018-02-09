package com.metier.biblio;

public class LivreAuteur {

	private int idAuteur;
	private int idLivre;
	
	// constructeurs:
	
	public LivreAuteur()
	{
		this.idAuteur=0;
		this.idLivre=0;
	}
	
	public LivreAuteur(int ida, int idl)
	{
		this.idAuteur=ida;
		this.idLivre=idl;
	}
	
	//getters et setters:
	
	public void setIdAuteur(int ida)
{
	this.idAuteur = ida;
}
	public int getIdAuteur()
	{
		return this.idAuteur;
	}
	
	public void SetIdLivre(int idl)
	{
		this.idLivre=idl;
	}
	
	public int getIdLivre()
	{
		return this.idLivre;
	}
	
	public String toString()
	{
		return "idAuteur = "+Integer.toString(this.idAuteur)+" / idLivre = "+Integer.toString(this.idLivre);
	}
	
}
