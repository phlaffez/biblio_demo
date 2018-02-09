package com.metier.biblio;

public class AuteurLangue {
	// classe pour la liaison auteur langues. Un auteur peut écrire dans plusieurs langue
	// testée ok le 09/02/2018
	private int idAuteur;
	private int idLangue;
	
	public  AuteurLangue()
	{
		this.idAuteur=0;
		this.idLangue=0;
	}
	
	public  AuteurLangue(int idA, int idL)
	{
		this.idAuteur = idA;
		this.idLangue = idL;
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
	
	public String toString()
	{
		return "idAuteur = "+Integer.toString(this.idAuteur)+" / idLangue = "+Integer.toString(this.idLangue);
	}
	

}
