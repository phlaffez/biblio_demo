package com.metier.biblio;

public class ResumeLivre {

	private int id;
	private String resume;    // resume et un champ text mysql. Un String est
	                          // il suffisant ? A vérifier et modifier
	                         // en conséquence si nécessaire:

	
	// constructeurs
	
	public void ResumeLivre()
	{
		this.id=0;
		this.resume="";
	}
	
	public void ResumeLivre(int id, String r)
	{
		this.id=id;
		this.resume=r;
	}
	
	// getters et setters:
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setResume(String r)
	{
		this.resume=r;
	}
	
	public String getResume()
	{
		return this.resume;
	}
	
	public String toString()
	{
		String s;
		s=Integer.toString(this.id)+":\n";
		s=s+this.resume;
		return s;
		}
	
	
}