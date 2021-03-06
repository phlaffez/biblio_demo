package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Couleurs;
import com.metier.biblio.Pays;

public class PaysDAO extends DAO<Pays> implements DAO_Noms<Pays>{

	public PaysDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public boolean create(Pays obj) {
		// reecrit le 08/08/2019 suite à effcementpr erreur ! 
				// testé le 
						boolean retour = false; // par défaut. Si pays  existe déjà, on ne la crée pas
						// mettre le pays en  en majuscules
						obj.setNom(obj.getNom().toUpperCase());
						// chercher si il est dans la base de données
						// getByNom renvoie un objet, donc il faut caster
						Pays pays = (Pays)getByNom(obj.getNom());
						if(pays ==null)
						{
							//on crée seulement si le pays   n'est pas déjà présent
							String requete = "INSERT INTO pays (nom) VALUES (\'"+obj.getNom()+"\')";
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
								System.out.println("Erreur SQL lors de la création du pays: "+obj.getNom());
								mes=2;
							}
						}
						
						// ici mettre un popup si mes=0 (pays existant) ou 2 (pb SQL)
						return retour;	
				
				

	}
	
	
	
	

	@Override
	public boolean update(Pays obj) {
		// testé le 07/02/2018 OK
		boolean retour = false;
		int res;
		int mes=0;
			// On ne peut faire d'update que si le pays existe. 
		Pays pays = findId(obj.getId());
		if(pays != null)
		{
			try 
			{
				String requete = "UPDATE pays SET nom_p =\""+obj.getNom()+"\"";
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
				System.out.println("Erreur SQL lors de la mise à jour du pays "+obj.getNom());
				mes=2;
			}
		}  // prévoir un popup si mes = 0 (pays inexistant) ou 2 (pb SQL)
		return retour;
	}
	
	

	@Override
	public boolean delete(Pays obj) {
		// testé le 07/02/2018 OK
		// On vérifie l'existance
		boolean retour = false;
			Pays pays = findId(obj.getId());
			int res=0;
		int mes = 0;
		if(pays == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("Le pays "+obj.getNom()+" n'est pas dans la base de donnés");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			try
			{
								String requete = "DELETE FROM pays WHERE id = "+Integer.toString(obj.getId());
				
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
				System.out.println("Problème SQL lors de la suppression du pays "+obj.getNom());;
	
			}
		}
		return retour;
	}
	
	
	

	@Override
	public Pays findId(int id) {
		Pays pays=null;
		int mes=0;
		String requete= "SELECT * FROM pays WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				pays = new Pays(id,res.getString("nom_p"));
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche du pays id= "+Integer.toString(id));
		}

		return pays;
	}
	
	
	

	@Override
	public int lastId() {
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM pays ORDER BY id DESC LIMIT 1";
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
			System.out.println("Erreur SQL lors dela recherche du dernier id Pays");;
		}
		return res;
	}
	
	
	

	@Override
	public List<Pays> selectAll() {
		List<Pays> pays = new ArrayList<Pays>();  // ce qui sera renvoyé
		Pays unPays;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM pays";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				unPays = new Pays(res.getInt("id"),res.getString("nom_p"));
				pays.add(unPays);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table Pays");;
		}
		
		
		
		
		return pays;
	}
	
	@Override
	public Object getByNom(String n) {
		Pays pays=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM pays WHERE nom_p =\'"+n+"\'";
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				pays = new Pays(res.getInt("id"),n);
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche du pays "+n);
		}
		return pays;
	}



	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		Pays pays=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM pays WHERE nom_p LIKE\'";
		if(opr==OptionRecherche.CONTIEND)
		{
			requete = requete + "%";
		}
		requete = requete+n+"%\'";
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				pays = new Pays(res.getInt("id"),n);
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche du pays "+n);
		}
		return pays;
	}

}
