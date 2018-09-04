package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Langue;
import com.metier.biblio.Localisation;

public class LocalisationDAO extends DAO<Localisation> implements DAO_Noms<Localisation> {

	public LocalisationDAO(Connection conn) {
		super(conn);
	}



	@Override
	public boolean create(Localisation obj) {
		
		boolean retour = false; // par défaut. Si le existe déjà, on ne le crée pas
		// mettre le lieu en majuscules
		obj.setLieu(obj.getLieu().toUpperCase());
		// chercher si il  est dans la base de données
		// getByNom renvoie un objet, donc il faut caster
		Localisation lieu = (Localisation)getByNom(obj.getLieu());
		if(lieu ==null)
		{
			//on crée car lelieu n'est pasenregistré dans la base
			String requete = "INSERT INTO Localisation (nom_lieu) VALUES (\'"+obj.getLieu()+"\')";
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
				System.out.println("Erreur SQL lors de la création du lieu "+obj.getLieu());
				mes=2;
			}
		}
		
		// ici mettre un popup si mes=0 (lieu existant) ou 2 (pb SQL)
		return retour;
		
	}

	@Override
	public boolean update(Localisation obj) {
		boolean retour = false;
		int res;
		int mes=0;
		// On ne peut faire d'update que si le lieu existe. 
		Localisation lieu = findId(obj.getId());
		if(lieu != null)
		{
			try 
			{
				String requete = "UPDATE Localisation SET nom_lieu =\""+obj.getLieu()+"\"";
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
				System.out.println("Erreur SQL lors de la mise à jour de la localisation  "+obj.getLieu());
				mes=2;
			}
		}  // prévoir un popup si mes = 0 (lieu inexistant) ou 2 (pb SQL)
		return retour;
		

	}

	@Override
	public boolean delete(Localisation obj) {
		// On vérifie l'existance
				boolean retour = false;
				Localisation lieu = findId(obj.getId());
				int res=0;
				int mes = 0;
				if(lieu == null)
				{
					// rien à faire, mais quand même un popup
					mes=3;
					System.out.println("La localisation "+obj.getLieu()+" n'est pas dans la base de donnés");
					System.out.println("Suppression inutile");
					retour = true;
				}
				else
				{
					try
					{
						String requete = "DELETE FROM localisation WHERE id = "+Integer.toString(obj.getId());
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
						System.out.println("Problème SQL lors de la suppression de la localisation "+obj.getLieu());;
			
					}
				}
				return retour;
		
		
	}

	@Override
	public Localisation findId(int id) {
		Localisation lieu=null;
		int mes=0;
		String requete= "SELECT * FROM localisation WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				lieu = new Localisation(id,res.getString("nom_lieu"));
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche de la localisation id= "+Integer.toString(id));
		}

		return lieu;
	}

	@Override
	public int lastId() {
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM localisation ORDER BY id DESC LIMIT 1";
		System.out.println(requete);
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
			System.out.println("Erreur SQL lors dela recherche du dernier id Localisation");;
		}
		return res;
		
	}

	@Override
	public List<Localisation> selectAll() {
		List<Localisation> lieux = new ArrayList<Localisation>();  // ce qui sera renvoyé
		Localisation unLieu;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM localisation";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				unLieu = new Localisation(res.getInt("id"),res.getString("nom_lieu"));
				lieux.add(unLieu);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table Localisation");;
		}
		
		
		
		
		return lieux;
	}



	@Override
	public Object getByNom(String n) {
		Localisation lieu=null;
		n=n.toUpperCase();
		int mes=0;
		String requete= "SELECT * FROM localisation WHERE nom_lieu =\'"+n+"\'";
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				lieu = new Localisation(res.getInt("id"),n);
				mes=1;
			}
		}
		catch (SQLException e)
		{
			mes=2;
			// popup à mettre
			System.out.println("Problème SQL lors de la recherche de la localisation "+n);
		}
		return lieu;
	}

}
