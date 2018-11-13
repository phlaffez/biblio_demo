package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Genre;
import com.metier.biblio.Pays;

public class GenreDAO  extends DAO<Genre> implements DAO_Noms<Genre>{
	
	

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Genre obj) {
		// testé le 3/4/2018 ok
		boolean retour = false; // par défaut. Si le genre existe déjà, on ne la crée pas
		// mettre la langue en majuscules
		obj.setNomGenre(obj.getNomGenre().toUpperCase());
		// chercher si il est dans la base de données
		// getByNom renvoie un objet, donc il faut caster
		Genre genre = (Genre)getByNom(obj.getNomGenre());
		if(genre ==null)
		{
			//on crée seulement si le genre n'est pas déjà présent
			String requete = "INSERT INTO genres (nom_genre) VALUES (\'"+obj.getNomGenre()+"\')";
			int res = 0;
			int mes=0;
			try
			{
			 	
			 res = this.connex.createStatement(). executeUpdate(requete);
			 if(res==1)
				 {
				 retour = true;
				 mes=1;
				 }
		}
			catch (SQLException e)
			{
				// remplacer par un popup
				System.out.println("Erreur SQL lors de la création du genre: "+obj.getNomGenre());
				mes=2;
			}
		}
		
		// ici mettre un popup si mes=0 (Genre existant) ou 2 (pb SQL)
		return retour;
	}

	
	@Override
	public boolean update(Genre obj) {
		// testé le  3/4/2018 ok
		boolean retour = false;
		int res;
		int mes=0;
			// On ne peut faire d'update que si le pays existe. 
		Genre genre = findId(obj.getId());
		if(genre != null)
		{
			try 
			{
				String requete = "UPDATE genres SET nom_genre =\""+obj.getNomGenre()+"\"";
				requete = requete+" WHERE id ="+Integer.toString(obj.getId());
				res = this.connex.createStatement(). executeUpdate(requete);
				if (res==1)
				{
					retour = true;
					mes=1;
				}
			}
			catch (SQLException e)
			{
				// Prévoir un popup
				System.out.println("Erreur SQL lors de la mise à jour du genre "+obj.getNomGenre());
				mes=2;
			}
		}  // prévoir un popup si mes = 0 (pays inexistant) ou 2 (pb SQL)
		return retour;
	}

	
	@Override
	public boolean delete(Genre obj) {
		// testé le  3/4/2018 ok
		// On vérifie l'existence
		boolean retour = false;
			Genre genre = findId(obj.getId());
			int res=0;
		int mes = 0;
		if(genre == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("Le genre "+obj.getNomGenre()+" n'est pas dans la base de donnés");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			try
			{
								String requete = "DELETE FROM genres WHERE id = "+Integer.toString(obj.getId());
				
				res = this.connex.createStatement(). executeUpdate(requete);
		
				if(res==1)
				{
					mes=1;
					retour = true;
				}
			}
			catch (SQLException e)
			{
				mes=2;
				// popup à mettre
				System.out.println("Problème SQL lors de la suppression du genre "+obj.getNomGenre());;
	
			}
		}
		return retour;
	}
	

	@Override
	public Genre findId(int id) {
		Genre genre=null;
		int mes=0;
		String requete= "SELECT * FROM genres WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				genre = new Genre(id,res.getString("nom_genre"));
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche du genre id= "+Integer.toString(id));
		}

		return genre;
	}
	

	@Override
	public int lastId() {
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM genres ORDER BY id DESC LIMIT 1";
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			
		{
			res  = res1.getInt("id");
		}
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors dela recherche du dernier id genres");;
		}
		return res;
	}
	

	@Override
	public List<Genre> selectAll() {
		List<Genre> genres = new ArrayList<Genre>();  // ce qui sera renvoyé
		Genre unGenre;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM genres";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				unGenre = new Genre(res.getInt("id"),res.getString("nom_genre"));
				genres.add(unGenre);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table genres");;
		}
		
		
		
		
		return genres;
	}
	

	@Override
	public Object getByNom(String n) {
		Genre genre=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM genres WHERE nom_genre =\'"+n+"\'";
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				genre = new Genre(res.getInt("id"),n);
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche du genre "+n);
		}
		return genre;
	}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) 
	{
		Genre genre=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM genres WHERE nom_genre LIKE\'";
		if(opr == OptionRecherche.CONTIEND)
		{
			requete = requete+"%";
		}
		
		requete=requete+n+"%\'";
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				genre = new Genre(res.getInt("id"),n);
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche du genre "+n);
		}
		return genre;
	}

}
