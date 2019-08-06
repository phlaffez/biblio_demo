package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Couleurs;
import com.metier.biblio.Pays;

public class CouleursDAO extends DAO<Couleurs> implements DAO_Noms<Couleurs>{

	public CouleursDAO(Connection conn) {
		super(conn);
		
		
		
	}

	@Override
	public boolean create(Couleurs obj) {
		
		
		// ecrit le 01/08/2019
		// testé le 
				boolean retour = false; // par défaut. Si la couleur  existe déjà, on ne la crée pas
				// mettre la couleur en majuscules
				obj.setNom(obj.getNom().toUpperCase());
				// chercher si elle est dans la base de données
				// getByNom renvoie un objet, donc il faut caster
				Couleurs couleur = (Couleurs)getByNom(obj.getNom());
				if(couleur ==null)
				{
					//on crée seulement si la couleur  n'est pas déjà présente
					String requete = "INSERT INTO couleurs (nom) VALUES (\'"+obj.getNom()+"\')";
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
						System.out.println("Erreur SQL lors de la création de la couleurs: "+obj.getNom());
						mes=2;
					}
				}
				
				// ici mettre un popup si mes=0 (couleur existante) ou 2 (pb SQL)
				return retour;	
		
		
	
	}

	@Override
	public boolean update(Couleurs obj) {
		//  ecrit le 06/08/2019
		// testé le
		
				boolean retour = false;
				int res;
				int mes=0;
					// On ne peut faire d'update que si la couleur existe. 
				Couleurs couleur = findId(obj.getId());
				if(couleur != null)
				{
					try 
					{
						String requete = "UPDATE couleurs SET nom =\""+obj.getNom()+"\"";
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
						System.out.println("Erreur SQL lors de la mise à jour de la couleur "+obj.getNom());
						mes=2;
					}
				}  // prévoir un popup si mes = 0 (couleur inexistant) ou 2 (pb SQL)
				return retour;
		
	}

	@Override
	public boolean delete(Couleurs obj) {
		// testé le 
		// On vérifie l'existance
		boolean retour = false;
			Couleurs couleur = findId(obj.getId());
			int res=0;
		int mes = 0;
		if(couleur == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("La couleur "+obj.getNom()+" n'est pas dans la base de donnés");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			try
			{
								String requete = "DELETE FROM couleurs WHERE id = "+Integer.toString(obj.getId());
				
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
				System.out.println("Problème SQL lors de la suppression de la couleur "+obj.getNom());;
	
			}
		}
		return retour;
	}
	
	

	@Override
	public Couleurs findId(int id) {
		// ecrite le 06/08/2019
		// testee le
		Couleurs couleur=null;
		int mes=0;
		String requete= "SELECT * FROM couleurs WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				couleur = new Couleurs(id,res.getString("nom"));
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche de la couleur id= "+Integer.toString(id));
		}

		return couleur;
	}

	@Override
	public int lastId() {
		
		// créé le 06/08/2019
		// testé le
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM couleurs ORDER BY id DESC LIMIT 1";
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
			System.out.println("Erreur SQL lors dela recherche du dernier id couleurs");;
		}
		return res;
	}

	@Override
	public List<Couleurs> selectAll() {
		// créé le 06/08/2019
		// testé le
		
		
		List<Couleurs> couleurs = new ArrayList<Couleurs>();  // ce qui sera renvoyé
		Couleurs couleur;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM couleurs";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				couleur = new Couleurs(res.getInt("id"),res.getString("nom"));
				couleurs.add(couleur);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table Couleurs");;
		}
		
		
		
		
		return couleurs;
	}

	@Override
	public Object getByNom(String n) {
		// créé le 06/08/2019
		// testé le
		
		
		Couleurs couleur=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM couleurs WHERE nom =\'"+n+"\'";
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				couleur = new Couleurs(res.getInt("id"),n);
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche de la couleur "+n);
		}
		return couleur;
	}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// ecrit le
		// teste le
		
		
		Couleurs couleur=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM couleurs WHERE nom LIKE\'";
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
				couleur = new Couleurs(res.getInt("id"),n);
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche de la couleur "+n);
		}
		return couleur;


}
}


