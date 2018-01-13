package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

public abstract class DAO <T> 
{
	protected Connection connex= null;
	
	public DAO(Connection conn)
	{

	    this.connex = conn;

	  }
	
	// Les 4 methodes de creation, modification,recherche et effacement
	// pour les DAO standards et 3 méthodes utiles
	
	public abstract boolean create(T obj);     // crée un enregistrement
	public abstract boolean update(T obj);     // met à  jour 
	public abstract boolean delete(T obj);     // efface
	public abstract T findId(int id);          // Cherche par l'id
	public abstract int lastId();              // retourne le dernier enregistré 
	public abstract List <T> selectAll();



}
