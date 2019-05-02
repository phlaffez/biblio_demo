package com.DAO.biblio;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Cote1;
import com.metier.biblio.Cote2;

import phl.outils.panneaux.outilsStandards.FenetreMessage;

public class Cote2DAO extends DAO<Cote2> implements DAO_Noms<Cote2>
{

	public Cote2DAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public boolean create(Cote2 obj) {
		// Ecrite le 11/12/2018
		// testée le 13/12/2018 OK
				
				Boolean retour = false;
				String req;
				String message;
				int mes;
				int res;
				FenetreMessage fen;
				ResultSet res1;
				//on vérifie que le nom n'est pas déjà enregistré pour cette cote1 
				
				req = "SELECT * FROM cote2 where nom = \'"+obj.getNom()+"\'";
				req = req + "AND cote1 = "+Integer.toString(obj.getCote1());
		//		System.out.println(req);
				try
				{
					res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
					if(res1.first())
					{
						message = "La reféférence de cotation 2 :+"+obj.getNom()+" existe déjà";
						 fen = new FenetreMessage("Cote2DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
						req = "SELECT * FROM cote2 where code = \'"+obj.getCode()+"\'";
						req = req + "AND cote1 = "+Integer.toString(obj.getCote1());
						System.out.println(req);
						try
						{
							res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
							if(res1.first())
							{
								message = "Le code de cotation 2 :+"+obj.getCode()+" existe déjà";
								 fen = new FenetreMessage("Cote2DAO","Attention",message,300,300,Color.lightGray,Color.black);
							}
							else
							{
								// on vérifie la longueur du code:
								if(obj.getCode().length()>3)
								{
									message = "Le code de cotation 2 :+"+obj.getCode()+" a un code trop long";
									 fen = new FenetreMessage("Cote2DAO","Attention",message,300,300,Color.lightGray,Color.black);

								}
								else
								{
								// peut créer la fiche
								String requete= "INSERT INTO cote2 (cote1,code, nom) VALUES ("+Integer.toString(obj.getCote1())+",'"+obj.getCode()+"\',\'"+obj.getNom()+"\')";
								try
								{
					//				System.out.println(requete);
									res = this.connex.createStatement(). executeUpdate(requete);
									if (res ==1)
									{
										retour = true;
									}
								
								}
								catch(SQLException e)
								{
									// remplacer par un popup
									System.out.println("Erreur SQL lors de la creation d'un enregistrement cote 2: "+obj.getNom()+" "+obj.getCode());
									mes=3;
								}
							}
							}
						}
						catch (SQLException e)
						{
							// remplacer par un popup
							System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 2: "+obj.getNom()+" "+obj.getCode());
							mes=2;
						}
					}
					
					
				}
				catch (SQLException e)
				{
					// remplacer par un popup
					System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 2: "+obj.getNom()+" "+obj.getCode());
					mes=1;
				}
				
				
				return retour;
	}

	@Override
	public boolean update(Cote2 obj) {
		// // écrit le 11/1/2018
		// testee le 13/12/2018 ok
		boolean retour = false;
		int res;
		int mes=0;
		String message = "";
		FenetreMessage fen;
		// On ne peut faire d'update que si l'enregistrement existe. 
				Cote2 cote2 = findId(obj.getIdCote2());
				if(cote2 != null)
				{
					if(obj.getCode().length()>3)
					{
						message = "Le code de cotation 2 :+"+obj.getCode()+" a un code trop long";
						 fen = new FenetreMessage("Cote2DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
					try
					{
					
						String requete = "UPDATE Cote2 set code ='"+obj.getCode()+"'";
						      requete = requete+",nom='"+obj.getNom()+"'";
						      requete = requete+",cote1 ="+Integer.toString(obj.getCote1());
						      requete = requete + " WHERE id_cote2 = "+Integer.toString(obj.getIdCote2());	
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
						System.out.println("Erreur dans la mise à jour de la cote2 "+obj.toString());
						}
					}
					}
				
				
				return retour;
	}

	@Override
	public boolean delete(Cote2 obj) {
		
		// testee le 13/12/2018 OK
		
				boolean retour = false;
				int res;
				int mes=0;
				// On ne peut supprimer que si l'enregistrement existe. 
						Cote2 cote2 = findId(obj.getIdCote2());
						if(cote2 != null)
						{
							try
							{
								String requete = "DELETE FROM Cote2 WHERE id_cote2 ="+Integer.toString(obj.getIdCote2());
							
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
								System.out.println("Erreur dans la suppression  de la cote2 "+obj.toString());
								}
						}
						else
						{
							System.out.println("L'objet "+obj.toString()+" n'existe pas dans la BDD. Suppression impossible");
						}
				return retour;
	}

	@Override
	public Cote2 findId(int id) {
		
		// testee le 13/12/2018 OK
		// Testee 
				int mes=0;
				
				Cote2 cote2 = null;
				String requete= "SELECT * FROM  cote2 WHERE id_cote2 ="+Integer.toString(id);
				try
				{
					ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
					if (res.first())
					{
									
						cote2 = new Cote2(id,res.getInt("cote1"),res.getString("code"),res.getString("nom"),res.getString("infos"));
												  
						mes=1;
					}
					
				}
				catch (SQLException e)
				{
					mes=2;
					System.out.println("Problème SQL lors de la recherche de la cote2 id_cote2= "+Integer.toString(id));
				}
				
				return cote2;
	}

	@Override
	public int lastId() {
		// testee le 13/12/2018 OK
		int mes=0;
		int res=0;
		String requete = "SELECT * FROM Cote2 ORDER BY id_cote2 DESC LIMIT 1";
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			
		{
			res  = res1.getInt("id_cote2");
		}
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors dela recherche du dernier id cote2");;
		}
		return res;
	}

	@Override
	public List<Cote2> selectAll() {
//		Testee le 
			List<Cote2> cotes2 = new ArrayList<Cote2>();
			Cote2 cote2 = null;
			int mes=0;    // s'il est nécessaire d'afficher des messages
			String requete = "SELECT * FROM cote2";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote2 = new  Cote2(res.getInt("id_cote2"),res.getInt("cote1"),res.getString("code"),  res.getString("nom"),res.getString("infos"));	
					cotes2.add(cote2);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche de la totalité de la table cote2");;
			}
			
			
			return cotes2;
	}
	
	@Override
	public Object getByNom(String n) {
		// teste ok le 13/12/2018
				Cote2 cote2=null;
				List<Cote2> cotes2= new ArrayList<Cote2>();
						int mes=0;
				String requete= "SELECT * FROM cote2 WHERE nom =\'"+n+"\'";	
					try
					{
						ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
						while (res.next())
						{

							cote2 = new  Cote2(res.getInt("id_cote2"),res.getInt("cote1"),res.getString("code"),res.getString("nom"),res.getString("infos"));	
							cotes2.add(cote2);
							}
						res.close();
					}
					catch (SQLException e)
					{
						mes=2;
						System.out.println("Erreur SQL lors de la recherche des cotes2 nom "+n);
					}
					
					
					return cotes2;
	}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// Testée le 13/12/2018 OK
				Cote2 cote2=null;
				List<Cote2> cotes2 = new ArrayList<Cote2>();
						int mes=0;
						String nn=n.toUpperCase();
				String requete= "SELECT * FROM cote2 WHERE nom LIKE \'";
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

							cote2 = new  Cote2(res.getInt("id_cote2"),res.getInt("cote1"),res.getString("code"),res.getString("nom"),res.getString("infos"));	
							cotes2.add(cote2);
							}
						res.close();
					}
					catch (SQLException e)
					{
						mes=2;
						System.out.println("Erreur SQL lors de la recherche LIKE des cotes2 nom "+n);
					}
					return cotes2;
	}
	
	
	public Object getByCote1(int c1) {
		
		// testee ok le 13/12/2018
		// récupération de toutes les cotes 2 sous la cote1 c1
				Cote2 cote2=null;
				List<Cote2> cotes2= new ArrayList<Cote2>();
						int mes=0;
				String requete= "SELECT * FROM cote2 WHERE cote1 = "+Integer.toString(c1);	
					try
					{
						ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
						while (res.next())
						{

							cote2 = new  Cote2(res.getInt("id_cote2"),res.getInt("cote1"),res.getString("code"),res.getString("nom"),res.getString("infos"));	
							cotes2.add(cote2);
							}
						res.close();
					}
					catch (SQLException e)
					{
						mes=2;
						System.out.println("Erreur SQL lors de la recherche des cotes2 cote1 = "+Integer.toString(c1));
					}
					
					
					return cotes2;
	}
	
	
	

}
