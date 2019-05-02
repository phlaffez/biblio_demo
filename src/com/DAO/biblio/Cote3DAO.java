package com.DAO.biblio;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Cote2;
import com.metier.biblio.Cote3;

import phl.outils.panneaux.outilsStandards.FenetreMessage;

public class Cote3DAO extends DAO<Cote3> implements DAO_Noms<Cote3>{

	public Cote3DAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public boolean create(Cote3 obj) {
		// Ecrite le 13/12/2018
		// testée le 13/12/2018 ok
		// modifié et testé le 02/05/2019
				
				Boolean retour = false;
				String req;
				String message;
				int mes;
				int res;
				FenetreMessage fen;
				ResultSet res1;
				//on vérifie que le nom n'est pas déjà enregistré pour cete cote2
				// qui est liée à une cote1
				
				req = "SELECT * FROM cote3 where nom = \'"+obj.getNom()+"\'";
				req = req + "AND cote2 = "+Integer.toString(obj.getCote2());
		//		System.out.println(req);
				try
				{
					res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
					if(res1.first())
					{
						message = "La reféférence de cotation 3 :+"+obj.getNom()+" existe déjà";
						 fen = new FenetreMessage("Cote3DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
						req = "SELECT * FROM cote3 where code = \'"+obj.getCode()+"\'";
						req = req + "AND cote2 = "+Integer.toString(obj.getCote2());
		//				System.out.println(req);
						try
						{
							res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
							if(res1.first())
							{
								message = "Le code de cotation 3 :+"+obj.getCode()+" existe déjà";
								 fen = new FenetreMessage("Cote3DAO","Attention",message,300,300,Color.lightGray,Color.black);
							}
							else
							{
								// on vérifie la longueur du code:
								if(obj.getCode().length()>5)
								{
									message = "Le code de cotation 3 :+"+obj.getCode()+" a un code trop long";
									 fen = new FenetreMessage("Cote3DAO","Attention",message,300,300,Color.lightGray,Color.black);

								}
								else
								{
								// peut créer la fiche
								String requete= "INSERT INTO cote3 (cote2,code, nom,infos) VALUES ("+Integer.toString(obj.getCote2())+",'"+obj.getCode()+"\',\'"+obj.getNom();
								requete = requete+"\',\'"+obj.getInfos()+"\')";
								try
								{
			//						System.out.println(requete);
									res = this.connex.createStatement(). executeUpdate(requete);
									if (res ==1)
									{
										retour = true;
									}
								
								}
								catch(SQLException e)
								{
									// remplacer par un popup
									System.out.println("Erreur SQL lors de la creation d'un enregistrement cote 3: "+obj.getNom()+" "+obj.getCode());
									mes=3;
								}
							}
							}
						}
						catch (SQLException e)
						{
							// remplacer par un popup
							System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 3: "+obj.getNom()+" "+obj.getCode());
							mes=2;
						}
					}
					
					
				}
				catch (SQLException e)
				{
					// remplacer par un popup
					System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 3: "+obj.getNom()+" "+obj.getCode());
					mes=1;
				}
				
				
				return retour;
	}
	
	
	

	@Override
	public boolean update(Cote3 obj) {
		// // écrit le 13/1/2018
		// testee le 13/12/2018  ok
		// modifiée et testée le 02/05/2019
		boolean retour = false;
		int res;
		int mes=0;
		String message = "";
		FenetreMessage fen;
		// On ne peut faire d'update que si l'enregistrement existe. 
				Cote3 cote3 = findId(obj.getIdCote3());
				if(cote3 != null)
				{
					if(obj.getCode().length()>5)
					{
						message = "Le code de cotation 3 :+"+obj.getCode()+" a un code trop long";
						 fen = new FenetreMessage("Cote3DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
					try
					{
					
						String requete = "UPDATE Cote3 set code ='"+obj.getCode()+"'";
						      requete = requete+",nom='"+obj.getNom()+"'";
						      requete = requete+",Infos='"+obj.getInfos()+"'";
						      requete = requete+",cote2 ="+Integer.toString(obj.getCote2());
						      requete = requete + " WHERE id_cote3 = "+Integer.toString(obj.getIdCote3());	
	//					      System.out.println(requete);
						res = this.connex.createStatement(). executeUpdate(requete);
						if(res==1)
						{
							retour = true;
							mes = 1;	
					}
					}
					catch (SQLException e)
					{
					// mettre fenetre d'erreur
						System.out.println("Erreur dans la mise à jour de la cote3 "+obj.toString());
						}
					}
					}
				
				
				return retour;
	}

	@Override
	public boolean delete(Cote3 obj) {
		
		// ecrite le 13/12/2018
		// testee le 13/12/2018  ok
		
				boolean retour = false;
				int res;
				int mes=0;
				// On ne peut supprimer que si l'enregistrement existe. 
						Cote3 cote3 = findId(obj.getIdCote3());
						if(cote3 != null)
						{
							try
							{
								String requete = "DELETE FROM Cote3 WHERE id_cote3 ="+Integer.toString(obj.getIdCote3());
							
								res = this.connex.createStatement(). executeUpdate(requete);
								
								if(res==1)
								{
									mes=1;
									retour = true;
								}
							}
							catch (SQLException e)
							{
							// mettre fenetre d'erreur
								System.out.println("Erreur dans la suppression  de la cote3 "+obj.toString());
								}
						}
						else
						{
							System.out.println("L'objet "+obj.toString()+" n'existe pas dans la BDD. Suppression impossible");
						}
				return retour;
	}

	@Override
	public Cote3 findId(int id) {
		
		// ecrite le 13/12/2018
		// testee  le 13/12/2018 ok
		// modifiee le 2/5/2019  Correction d'un bug et modification pour champ supplémentaire
		// Testee 
				int mes=0;
				
				Cote3 cote3 = null;
				String requete= "SELECT * FROM  cote3 WHERE id_cote3 ="+Integer.toString(id);
//				System.out.println(requete);

				try
				{
					ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
					if (res.first())
					{		
				cote3 = new Cote3(id,res.getInt("cote2"),res.getString("code"),res.getString("nom"),res.getString("infos"));
									
						mes=1;
					}
					
				}
				catch (SQLException e)
				{
					mes=2;
					System.out.println("Problème SQL lors de la recherche de la cote3 id_cote3= "+Integer.toString(id));
				}
				
				return cote3;
	}

	@Override
	public int lastId() {
		// ecrite le 13/12/2018
		// testee le 13/12/2018  ok
		int mes=0;
		int res=0;
		String requete = "SELECT * FROM Cote3 ORDER BY id_cote3 DESC LIMIT 1";
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			
		{
			res  = res1.getInt("id_cote3");
		}
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors dela recherche du dernier id cote3");;
		}
		return res;
	}

	@Override
	public List<Cote3> selectAll() {
		// ecrite le 13/12/2018
		// testee le 13/12/2018  ok
		// modifiée et testée le 02/05/2019 OK
//		Testee le 
			List<Cote3> cotes3 = new ArrayList<Cote3>();
			Cote3 cote3 = null;
			int mes=0;    // s'il est nécessaire d'afficher des messages
			String requete = "SELECT * FROM cote3";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote3 = new  Cote3(res.getInt("id_cote3"),res.getInt("cote2"),res.getString("code"),  res.getString("nom"),res.getString("infos"));	
					cotes3.add(cote3);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche de la totalité de la table cote3");;
			}
			
			
			return cotes3;
	}
	
	@Override
	public Object getByNom(String n)  {
		// ecrite ok le 13/12/2018
		// testee le 13/12/2018  ok
		// modifiée et testée le 02/05/2019
		Cote3 cote3=null;
		List<Cote3> cotes3= new ArrayList<Cote3>();
				int mes=0;
		String requete= "SELECT * FROM cote3 WHERE nom =\'"+n+"\'";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote3 = new  Cote3(res.getInt("id_cote3"),res.getInt("cote2"),res.getString("code"),res.getString("nom"),res.getString("infos"));	
					cotes3.add(cote3);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche des cotes3 nom "+n);
			}
			
			
			return cotes3;
}
	@Override
	public Object getByNomLike(String n, OptionRecherche opr)  {
		// ecrite le 13/12/2018 
		// testee le 13/12/2018  ok
		// modifiée et testée le 02/05/2019
		
		Cote3 cote3=null;
		List<Cote3> cotes3 = new ArrayList<Cote3>();
				int mes=0;
				String nn=n.toUpperCase();
		String requete= "SELECT * FROM cote3 WHERE nom LIKE \'";
		if(opr == OptionRecherche.CONTIEND)
		{
			requete = requete + "%";
		}
		requete = requete+nn+"%\'";	
				
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote3 = new  Cote3(res.getInt("id_cote3"),res.getInt("cote2"),res.getString("code"),res.getString("nom"),res.getString("infos"));	
					cotes3.add(cote3);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche LIKE des cotes3 nom "+n);
			}
			return cotes3;
}
	
	
public Object getByCote2(int c1) {
		
		// ecrite le 13/12/2018
		// récupération de toutes les cotes 3 sous la cote2 c1
	// modifiée et testée le 02/05/2019
	
				Cote3 cote3=null;
				List<Cote3> cotes3= new ArrayList<Cote3>();
						int mes=0;
				String requete= "SELECT * FROM cote3 WHERE cote2 = "+Integer.toString(c1);	
					try
					{
						ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
						while (res.next())
						{

							cote3 = new  Cote3(res.getInt("id_cote3"),res.getInt("cote2"),res.getString("code"),res.getString("nom"),res.getString("infos"));	
							cotes3.add(cote3);
							}
						res.close();
					}
					catch (SQLException e)
					{
						mes=2;
						System.out.println("Erreur SQL lors de la recherche des cotes3 cote2 = "+Integer.toString(c1));
					}
					
					
					return cotes3;
	}
	
}



