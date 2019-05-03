package com.DAO.biblio;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Cote3;
import com.metier.biblio.Cote4;

import phl.outils.panneaux.outilsStandards.FenetreMessage;

public class Cote4DAO extends DAO<Cote4> implements DAO_Noms<Cote4>{

	public Cote4DAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}



	@Override
	public boolean create(Cote4 obj) {
		// Ecrite le 16/12/2018
		// testée le 16/12/2018 OK
		// modifiée et testéele 03/05/2019
				
				Boolean retour = false;
				String req;
				String message;
				int mes;
				int res=-9999;
				FenetreMessage fen;
				ResultSet res1;
				//on vérifie que le nom n'est pas déjà enregistré pour cette cote4
				// qui est liée à une cote3, donc une cote2 et une cote1
				
				req = "SELECT * FROM cote4 where nom = \'"+obj.getNom()+"\'";
				req = req + "AND cote3 = "+Integer.toString(obj.getCote3());
				
//				System.out.println(req);
				try
				{
					res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
					if(res1.first())
					{
						message = "La reféférence de cotation 4 :+"+obj.getNom()+" existe déjà";
						 fen = new FenetreMessage("Cote4DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
						req = "SELECT * FROM cote4 where code = \'"+obj.getCode()+"\'";
						req = req + "AND cote3 = "+Integer.toString(obj.getCote3());
	//					System.out.println(req);
						try
						{
							res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
							if(res1.first())
							{
								message = "Le code de cotation 4 :+"+obj.getCode()+" existe déjà";
								 fen = new FenetreMessage("Cote4DAO","Attention",message,300,300,Color.lightGray,Color.black);
							}
							else
							{
								// on vérifie la longueur du code:
								if(obj.getCode().length()>5)
								{
									message = "Le code de cotation 4 :+"+obj.getCode()+" a un code trop long";
									 fen = new FenetreMessage("Cote4DAO","Attention",message,300,300,Color.lightGray,Color.black);

								}
								else
								{
								// peut créer la fiche
								String requete= "INSERT INTO cote4 (cote3,code, nom,compteur,infos) VALUES (";
								requete = requete +Integer.toString(obj.getCote3())+",'"+obj.getCode()+"\',\'";
								requete = requete +obj.getNom()+"\',"+Float.toString(obj.getCompteur())+",'"+obj.getInfos();
								requete = requete +"')";
					//			System.out.println(requete);
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
									System.out.println("Erreur SQL lors de la creation d'un enregistrement cote 4: "+obj.getNom()+" "+obj.getCode());
	
									mes=3;
								}
							}
							}
						}
						catch (SQLException e)
						{
							// remplacer par un popup
							System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 4: "+obj.getNom()+" "+obj.getCode());
							mes=2;
						}
					}
					
					
				}
				catch (SQLException e)
				{
					// remplacer par un popup
					System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 4: "+obj.getNom()+" "+obj.getCode());
					mes=1;
				}
				
				
				return retour;
	}

	@Override
	public boolean update(Cote4 obj) {
		// // écrit le 16/12/2018
		// testee le 16/12/2018  OK
		// modifiée et testée le 03/05/2019
		boolean retour = false;
		int res;
		int mes=0;
		String message = "";
		FenetreMessage fen;
		// On ne peut faire d'update que si l'enregistrement existe. 
				Cote4 cote4 = findId(obj.getIdCote4());
				if(cote4 != null)
				{
					if(obj.getCode().length()>5)
					{
						message = "Le code de cotation 4 :+"+obj.getCode()+" a un code trop long";
						 fen = new FenetreMessage("Cot43DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
					try
					{
					
						String requete = "UPDATE Cote4 set code ='"+obj.getCode()+"'";
						      requete = requete+",nom='"+obj.getNom()+"'";
						      requete = requete+",cote3 ="+Integer.toString(obj.getCote3());
						      requete = requete +",compteur = "+Float.toString(obj.getCompteur());
						      requete = requete+",infos ='"+obj.getInfos()+"'";
						      requete = requete + " WHERE id_cote4 = "+Integer.toString(obj.getIdCote4());	
		
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
						System.out.println("Erreur dans la mise à jour de la cote4 "+obj.toString());
						}
					}
					}
				
				
				return retour;
	}

	@Override
	public boolean delete(Cote4 obj) {
		
		// ecrite le 16/12/2018
		// testee le  16/12/2018  OK
		
				boolean retour = false;
				int res;
				int mes=0;
				// On ne peut supprimer que si l'enregistrement existe. 
						Cote4 cote4 = findId(obj.getIdCote4());
						if(cote4 != null)
						{
							try
							{
								String requete = "DELETE FROM Cote4 WHERE id_cote4 ="+Integer.toString(obj.getIdCote4());
							
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
								System.out.println("Erreur dans la suppression  de la cote43 "+obj.toString());
								}
						}
						else
						{
							System.out.println("L'objet "+obj.toString()+" n'existe pas dans la BDD. Suppression impossible");
						}
				return retour;
	}

	@Override
	public Cote4 findId(int id) {
		
		// ecrite le 16/12/2018
		// testee  le 16/12/2018  OK
		// modifiée et testée le 3/05/2019
				int mes=0;
				
				Cote4 cote4 = null;
				String requete= "SELECT * FROM  cote4 WHERE id_cote4 ="+Integer.toString(id);
				try
				{
					ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
					if (res.first())
					{
									
						cote4 = new Cote4(id,res.getString("code"),res.getString("nom"),res.getInt("cote3"),res.getFloat("compteur"),res.getString("infos"));
												  
						mes=1;
					}
					
				}
				catch (SQLException e)
				{
					mes=2;
					System.out.println("Problème SQL lors de la recherche de la cote4 id_cote4= "+Integer.toString(id));
				}
				
				return cote4;
	}

	@Override
	public int lastId() {
		// ecrite le 16/12/2018
		// testee le 16/12/2018  OK
		int mes=0;
		int res=0;
		String requete = "SELECT * FROM Cote4 ORDER BY id_cote4 DESC LIMIT 1";
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			
		{
			res  = res1.getInt("id_cote4");
		}
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors dela recherche du dernier id cote4");;
		}
		return res;
	}

	@Override
	public List<Cote4> selectAll() {
		// ecrite le 16/12/2018
		// testee le 16/12/2018  OK
		// modifiée et testée le 03/05/2019

			List<Cote4> cotes4 = new ArrayList<Cote4>();
			Cote4 cote4 = null;
			int mes=0;    // s'il est nécessaire d'afficher des messages
			String requete = "SELECT * FROM cote4";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote4 = new  Cote4(res.getInt("id_cote4"),res.getString("code"),res.getString("nom"),res.getInt("cote3"),res.getFloat("compteur"),res.getString("infos"));	
					cotes4.add(cote4);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche de la totalité de la table cote3");;
			}
			
			
			return cotes4;
	}

	@Override
	public Object getByNom(String n) {
		// ecrite ok le 16/12/2018
		// modifiée et testée le 03/05/2019
		Cote4 cote4=null;
		List<Cote4> cotes4= new ArrayList<Cote4>();
				int mes=0;
		String requete= "SELECT * FROM cote4 WHERE nom =\'"+n+"\'";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote4 = new  Cote4(res.getInt("id_cote4"),res.getString("code"),res.getString("nom"),res.getInt("cote3"),res.getFloat("compteur"),res.getString("infos"));	
					cotes4.add(cote4);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche des cotes4 nom "+n);
			}
			
			
			return cotes4;
}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// ecrite le 16/12/2018 
		// testee le 16/12/2018  OK
		// modifiée et testée le 03/05/2019
		Cote4 cote4=null;
		List<Cote4> cotes4 = new ArrayList<Cote4>();
				int mes=0;
				String nn=n.toUpperCase();
		String requete= "SELECT * FROM cote4 WHERE nom LIKE \'";
		if(opr == OptionRecherche.CONTIEND)
		{
			requete = requete + "%";
		}
		requete = requete+nn+"%\'";	
//		System.out.println(requete);
				
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote4 = new  Cote4(res.getInt("id_cote4"),res.getString("code"),res.getString("nom"),res.getInt("cote3"),res.getFloat("compteur"),res.getString("infos"));	
					cotes4.add(cote4);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche LIKE des cotes4 nom "+n);
			}
			return cotes4;
}
	
	
public Object getByCote3(int c1) {
		
		// ecrite le 16/12/2018
	// testee le 16/12/2018
		// récupération de toutes les cotes 4 sous la cote3 c1
	
				Cote4 cote4=null;
				List<Cote4> cotes4= new ArrayList<Cote4>();
						int mes=0;
				String requete= "SELECT * FROM cote4 WHERE cote3 = "+Integer.toString(c1);	
					try
					{
						ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
						while (res.next())
						{

							cote4 = new  Cote4(res.getInt("id_cote4"),res.getString("code"),res.getString("nom"),res.getInt("cote3"),res.getFloat("compteur"),res.getString("infos"));
							
							cotes4.add(cote4);
							}
						res.close();
					}
					catch (SQLException e)
					{
						mes=2;
						System.out.println("Erreur SQL lors de la recherche des cotes4 cote3 = "+Integer.toString(c1));
					}
					
					
					return cotes4;
	}
	
	
}
