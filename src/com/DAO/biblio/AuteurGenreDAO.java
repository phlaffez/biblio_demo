package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Auteur;
import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Genre;
import com.metier.biblio.Livre;
import com.outils.biblio.Cles;

public class AuteurGenreDAO extends DAO<AuteurGenre> implements DAO_Liaison<AuteurGenre>
{

	public AuteurGenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(AuteurGenre obj) {
		boolean retour = false;
		int res=0;
		int mes=0;
		if(!this.isPresent(obj))
		{
		String requete = "INSERT INTO auteur_langue (id_auteur, id_genre)";
		requete = requete+" VALUES ("+Integer.toString(obj.getIdAuteur())+",";
		requete = requete + Integer.toString(obj.GetIdGenre())+")";
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
			// il faudrait un popup
			System.out.println("Erreur SQL dans la création de la liaison Auteur Genre");
			System.out.println("Requete:  "+requete);
			mes=2;
		}
		}
		else
		{
			System.out.println("La liaison dans la table auteur_genre existe déjà");
			System.out.println("Auteur id = "+obj.getIdAuteur()+"  Langue id=  "+obj.GetIdGenre());
			mes=1;
		}
		return retour;
	}

	@Override
	public boolean update(AuteurGenre obj) {
		// N'est pas  à implémenter
		return false;
	}

	@Override
	public boolean delete(AuteurGenre obj) {
		int res=0;
		int mes=0;
		boolean retour = false;
		if(this.isPresent(obj))
		{
			String requete = "DELETE FROM auteur_genre WHERE id_auteur = ";
			requete = requete+Integer.toString(obj.getIdAuteur())+" AND id_genre = ";
			requete = requete+Integer.toString(obj.GetIdGenre());
			try
			{
				res = this.connex.createStatement(). executeUpdate(requete);
				if(res==1)
				{
					retour=true;
				}
			}
			
			catch (SQLException e)
			{
				// popup
				System.out.println("Erreur SQL lors de la suppression d'une liaison:");
				System.out.println(requete);
			}
			}
			
		else
		{
			// ne provoque pas une erreur
			retour=true;
			mes=1;
			System.out.println("Demande de suppression d'une liaison inexistante:");
			System.out.println("Auteur id= "+obj.getIdAuteur()+"  Langue id= "+obj.GetIdGenre());
		}
		
		return retour;
		}

	@Override
	public AuteurGenre findId(int id) {
		// N'est pas  à implémenter
		return null;
	}

	@Override
	public int lastId() {
		// N'est pas  à implémenter
		return 0;
	}

	@Override
	public List<AuteurGenre> selectAll() {
		// N'est pas  à implémenter
		return null;
	}

	@Override
	public Object getByCleLiaison(Cles cle, int id) {
		// TODO Auto-generated method stub
		String champCherche;
		List<Integer> resultat = new ArrayList<Integer>();
		int ii;
		String champCle = cle.toString();
		if(cle==Cles.id_auteur)
		{
			champCherche=Cles.id_genre.toString();
		}
		else
		{
			champCherche=Cles.id_auteur.toString();
		}
		String requete = "SELECT "+champCherche+" FROM auteur_genre WHERE ";
		requete= requete + champCle+" ="+Integer.toString(id);
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				ii=res.getInt(1);
				resultat.add(ii);
			}
			res.close();
		}
		catch (SQLException e)
		{    e.printStackTrace();
			System.out.println("Erreur SQL dans la récupération de liaisons:");
			System.out.println(requete);
		}
		
		return resultat;
	}

	@Override
	public boolean isPresent(AuteurGenre obj) {
		boolean retour = false;
		String requete = "SELECT * FROM auteur_genre WHERE ";
		requete = requete + "id_auteur = "+Integer.toString(obj.getIdAuteur());
		requete = requete+" AND id_genre ="+Integer.toString(obj.GetIdGenre());
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			{
				retour = true;
			}
			
		}
		catch (SQLException e)
		{
			// Il faudra mettre un popup
			System.out.println("ErreurSQLdans la recherche dans la table de liaison auteur_genre");
		}
		return retour;
	}

	@Override
	public Object getListeByCleLiaison(Cles cle, int id) {
		// récupérer la liste des entiers correspondants:
		List<Integer> listeres = (List<Integer>)this.getByCleLiaison(cle, id);
		if(listeres.size()==0)
		{
			return null;   // liste vide
		}
		else
		{
			if(cle==Cles.id_auteur)
			{
				// on retourne la liste des genres pratiqués par cet auteur
				DAO genreDAO = new GenreDAO(connex);
				List<Genre> genres = new ArrayList<Genre>();
				for(int i=1;i<listeres.size();i++)
				{
					genres.add((Genre)genreDAO.findId(listeres.get(i)));
				}
				return genres;
			}
			else
				
			{
				// on retourne la liste des auteurs pratiquant ce genre
				DAO auteurDAO = new AuteurDAO(connex);
				List<Auteur> auteurs = new ArrayList<Auteur>();
				for(int i = 1; i<listeres.size();i++)
				{
					auteurs.add(((Auteur)auteurDAO.findId(listeres.get(i))));
				}
				return auteurs;
			}
		}
		}
	
	

	@Override
	public boolean deleteByCleLiaison(Cles cle, int id) {
		String requete;
		int res;
		int mes=0;
		// TODOUne requete à écrire en fonction de la clé de selection
		if(cle == Cles.id_auteur)
		{
			// on efface tous les liaisons livre auteur de cet auteur
			requete = "DELETE * FROM auteur_genre WHERE id_auteur = "+Integer.toString(id);
		}
		else
		{
			// le contraire, id on efface toutes les liaisons pour ce livre
			requete = "DELETE FROM auteur_genre WHERE id_genre ="+Integer.toString(id);
		}
		boolean retour = false;
		try
		{
			res = this.connex.createStatement(). executeUpdate(requete);
			if(res==1)
			{
				retour = true;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Ereur SQLdans la suppression dans la tablede liaison auteurs genres");
			System.out.println(cle.toString()+" = "+id);
			mes=1;
		}
		
		return retour;
	}

}
