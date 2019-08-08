package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Couleurs;
import com.metier.biblio.Monnaies;

public class MonnaiesDAO extends DAO<Monnaies> implements DAO_Noms<Couleurs>{

	public MonnaiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Monnaies obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Monnaies obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Monnaies obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Monnaies findId(int id) {
		// ecrite le 08/08/2019
		// testee ok le 08/08/2019  -- attention qd même au cas ou on ne trouve pas, qui n'est pas géré
				
		Monnaies monnaie=null;
		int mes=0;
		String requete= "SELECT * FROM monnaies WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				monnaie = new Monnaies(id,res.getString("nom"),res.getString("abrev"));
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche de la monnaie id= "+Integer.toString(id));
		}

		return monnaie;
	}

	@Override
	public int lastId() {
		// créé le 08/08/2019
				// testé le 08/08/2019
				int res = -1;  // ce qui sera retourné si on ne trouve pas 
				int mes=0;
				String requete = "SELECT * FROM monnaies ORDER BY id DESC LIMIT 1";
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
					System.out.println("Erreur SQL lors dela recherche du dernier id monnaies");;
				}
				return res;
	}

	@Override
	public List<Monnaies> selectAll() {
		// créé le 08/08/2019
		//	 testé le 08/08/2019  OK
				
				
				List<Monnaies> objs = new ArrayList<Monnaies>();  // ce qui sera renvoyé
				Monnaies obj;
				int mes=0;    // s'il est nécessaire d'afficher des messages
				String requete = "SELECT * FROM monnaies";
				try
				{
					ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
					while (res.next())
					{
						obj = new Monnaies(res.getInt("id"),res.getString("nom"),res.getString("abrev"));
						objs.add(obj);
					}
					res.close();
				}
				catch (SQLException e)
				{
					mes=2;
					System.out.println("Erreur SQL lors de la recherche de la totalité de la table Monnaies");;
				}
				
				
				
				
				return objs;
	}

	@Override
	public Object getByNom(String n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// TODO Auto-generated method stub
		return null;
	}

}
