package com.metier.biblio;


/*
 * ************************************************************
 *                                                            *
 *                     Catalogues Yvert et sections           *
 *  classe testée ok le                             *
 * ***********************************************************             
 */
public class Catyvert {

	private int id;
	private String catalogue;
	private String categorie;
	private String infos;


public Catyvert()
{
	this.id = 0;
	this.catalogue = "";
	this.categorie = "";
	this.infos = "";
	
}

public Catyvert(int id, String catalogue, String categorie, String infos)
{
	this.id = id;
	this.catalogue = catalogue;
	this.categorie = categorie;
	this.infos = infos;
	
}


public Catyvert( String catalogue, String categorie, String infos)
{
	this.id = 0;
	this.catalogue = catalogue;
	this.categorie = categorie;
	this.infos = infos;
	
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

public String getCatalogue()
{
	return this.catalogue;
}

public void setCatalogue(String catalogue)
{
	this.catalogue = catalogue;
}


public String getCategorie()
{
	return this.categorie;
}

public void setCategorie(String categorie)
{
	this.categorie = categorie;
}

public String getInfos()
{
	return this.infos;
}

public void setInfos(String infos)
{
	this.infos = infos;
}


public String toString()
{
 String l;
  l =Integer.toString(this.id)+" / "+this.catalogue+" / ";
  l = l+this.categorie+" / "+this.infos;
 return l;
}

}