package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Langue;

public class LangueDAO extends DAO<Langue> implements DAO_Noms<Langue>{

	public LangueDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Langue obj) {
		boolean retour = false; // par défaut. Si la langue existe déjà, on ne la crée pas
		// mettre la langue en majuscules
		obj.setNom(obj.getNom().toUpperCase());
		// chercher si elle est dans la base de données
		// getByNom renvoie un objet, donc il faut caster
		Langue langue = (Langue)getByNom(obj.getNom());
		if(langue ==null)
		{
			//on crée seulement si la langue n'est pas déjà présente
			String requete = "INSERT INTO langues (nom_lan,nbre) VALUES (\'"+obj.getNom()+"\',0)";
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
				System.out.println("Erreur SQL lors de la création de la langue "+obj.getNom());
				mes=2;
			}
		}
		
		// ici mettre un popup si mes=0 (langue existante) ou 2 (pb SQL)
		return retour;
	}

	@Override
	public boolean update(Langue obj) {
		boolean retour = false;
		int res;
		int mes=0;
		// On ne peut faire d'update que si la langue existe. 
		Langue langue = findId(obj.getId());
		if(langue != null)
		{
			try 
			{
				String requete = "UPDATE langues SET nom_lan =\""+obj.getNom()+"\"";
				requete = requete + ", nbre = "+Integer.toString(obj.getNombre());
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
				System.out.println("Erreur SQL lors de la mise à jour de la langue "+obj.getNom());
				mes=2;
			}
		}  // prévoir un popup si mes = 0 (langue inexistante) ou 2 (pb SQL)
		return retour;
	}

	@Override
	public boolean delete(Langue obj) {
		// On vérifie l'existance
		boolean retour = false;
		Langue langue = findId(obj.getId());
		int res=0;
		int mes = 0;
		if(langue == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("La langue "+obj.getNom()+" n'est pas dans la base de donnés");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			try
			{
				String requete = "DELETE FROM langue WHERE id = "+Integer.toString(obj.getId());
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
				// popupàmettre
				System.out.println("Problème SQL lors de la suppression de la langue "+obj.getNom());;
	
			}
		}
		return retour;
	}

	
	@Override
	public Langue findId(int id) {
		Langue langue=null;
		int mes=0;
		String requete= "SELECT * FROM langues WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				langue = new Langue(id,res.getString("nom_lan"),res.getInt("nbre"));
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche de la langue id= "+Integer.toString(id));
		}

		return langue;
	}

	@Override
	public int lastId() {
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM langues ORDER BY id DESC LIMIT 1";
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
			System.out.println("Erreur SQL lors dela recherche du dernier id Langues");;
		}
		return res;
	}
	

	@Override
	public List<Langue> selectAll() {
		List<Langue> langues = new ArrayList<Langue>();  // ce qui sera renvoyé
		Langue uneLangue;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM langues";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				uneLangue = new Langue(res.getInt("id"),res.getString("nom_lan"),res.getInt("nbre"));
				langues.add(uneLangue);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table Langues");;
		}
		
		
		
		
		return langues;
	}

	@Override
	public Object getByNom(String n) {
		Langue langue=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM langues WHERE nom_lan =\'"+n+"\'";
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				langue = new Langue(res.getInt("id"),n,res.getInt("nbre"));
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche de la langue "+n);
		}
		return langue;
	}




}
