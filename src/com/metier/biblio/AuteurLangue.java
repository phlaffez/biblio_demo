package com.metier.biblio;

public class AuteurLangue {
	// classe pour la liaison auteur langues. Un auteur peut écrire dans plusieurs langue
	private int idAuteur;
	private int idLangue;
	
	public void AuteurLangue()
	{
		this.idAuteur=0;
		this.idLangue=0;
	}
	
	public void AuteurLangue(int idA, int idL)
	{
		this.idAuteur = idA;
		this.idAuteur = idL;
	}
	
	public void setIdAuteur(int idA)
	{
		this.idAuteur = idA;
	}
	
	public int getIdAuteur()
	{
		return this.idAuteur;
	}
	
	public void setIdLangue(int idL)
	{
		this.idLangue = idL;
	}
	
	public int getIdLangue()
	{
		return this.idLangue;
	}
	

}
