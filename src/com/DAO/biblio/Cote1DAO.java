package com.DAO.biblio;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.biblio.DAO;
import com.DAO.biblio.DAO_Noms;
import com.DAO.biblio.OptionRecherche;
import com.metier.biblio.Auteur;
import com.metier.biblio.Cote1;

import phl.outils.panneaux.outilsStandards.FenetreMessage;

public class Cote1DAO  extends DAO<Cote1> implements DAO_Noms<Cote1>
{

	public Cote1DAO(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(Cote1 obj) {
		// Ecrite le 07/12/2108
		// testée le 11/12/2018  -->ok
		Boolean retour = false;
		String req;
		String message;
		int mes;
		int res;
		FenetreMessage fen;
		ResultSet res1;
		//on vérifie que le nom n'est pas déjà enregistré
		
		req = "SELECT * FROM cote1 where nom = \'"+obj.getNom()+"\'";
		try
		{
			res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
			if(res1.first())
			{
				message = "La reféférence de cotation 1 :+"+obj.getNom()+" existe déjà";
				 fen = new FenetreMessage("Cote1DAO","Attention",message,300,300,Color.lightGray,Color.black);
			}
			else
			{
				req = "SELECT * FROM cote1 where code = \'"+obj.getCode()+"\'";
				try
				{
					res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
					if(res1.first())
					{
						message = "Le code de cotation 1 :+"+obj.getCode()+" existe déjà";
						 fen = new FenetreMessage("Cote1DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
						// on vérifie la longueur du code:
						if(obj.getCode().length()>3)
						{
							message = "Le code de cotation 1 :+"+obj.getCode()+" a un code trop long";
							 fen = new FenetreMessage("Cote1DAO","Attention",message,300,300,Color.lightGray,Color.black);

						}
						else
						{
						// peut créer la fiche
						String requete= "INSERT INTO cote1 (code, nom) VALUES (\'"+obj.getCode()+"\',\'"+obj.getNom()+"\')";
						// je ne comprend pas, ça ne fonctionne pas, or la requete passée directement fonctionne
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
							System.out.println("Erreur SQL lors de la creation d'un enregistrement cote 1: "+obj.getNom()+" "+obj.getCode());
							mes=3;
						}
					}
					}
				}
				catch (SQLException e)
				{
					// remplacer par un popup
					System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 1: "+obj.getNom()+" "+obj.getCode());
					mes=2;
				}
			}
			
			
		}
		catch (SQLException e)
		{
			// remplacer par un popup
			System.out.println("Erreur SQL lors de la recherche d'un enregistrement cote 1: "+obj.getNom()+" "+obj.getCode());
			mes=1;
		}
		
		
		return retour;
	}

	@Override
	public boolean update(Cote1 obj) {
		// Testée le 11/12/2018  -->ok
		boolean retour = false;
		int res;
		int mes=0;
		String message = "";
		FenetreMessage fen;
		// On ne peut faire d'update que si l'enregistrement existe. 
				Cote1 cote1 = findId(obj.getIdCote1());
				if(cote1 != null)
				{
					if(obj.getCode().length()>3)
					{
						message = "Le code de cotation 1 :+"+obj.getCode()+" a un code trop long";
						 fen = new FenetreMessage("Cote1DAO","Attention",message,300,300,Color.lightGray,Color.black);
					}
					else
					{
					try
					{
					
						String requete = "UPDATE Cote1 set code ='"+obj.getCode()+"'";
						      requete = requete+",nom='"+obj.getNom()+"'";
						      requete = requete + "WHERE id_cote1 = "+Integer.toString(obj.getIdCote1());
						// la requete fonctionne en direct mais pas ici ??		
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
						System.out.println("Erreur dans la mise à jour de la cote1 "+obj.toString());
						}
					}
					}
				
				
				return retour;
	}

	@Override
	public boolean delete(Cote1 obj) {
		// Testee le 11/12/2018 -->OK
		boolean retour = false;
		int res;
		int mes=0;
		// On ne peut supprimer que si l'enregistrement existe. 
				Cote1 cote1 = findId(obj.getIdCote1());
				if(cote1 != null)
				{
					try
					{
						String requete = "DELETE FROM Cote1 WHERE id_cote1 ="+Integer.toString(obj.getIdCote1());
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
						System.out.println("Erreur dans la suppression  de la cote1 "+obj.toString());
						}
				}
				else
				{
					System.out.println("L'objet "+obj.toString()+" n'existe pas dans la BDD. Suppression impossible");
				}
		return retour;
	}

	@Override
	public Cote1 findId(int id) {
		// Testee le 11/12/2018 --> OK
		int mes=0;
		
		Cote1 cote1 = null;
		String requete= "SELECT * FROM  cote1 WHERE id_cote1 ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
							
				cote1 = new Cote1(id,res.getString("code"),res.getString("nom"));
										  
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche de la cote1 id_cote1= "+Integer.toString(id));
		}
		
		return cote1;
	}

	@Override
	public int lastId() {
		// Testee le 11/12/2018 -- OK
		int mes=0;
		int res=0;
		String requete = "SELECT * FROM Cote1 ORDER BY id_cote1 DESC LIMIT 1";
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			
		{
			res  = res1.getInt("id_cote1");
		}
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors dela recherche du dernier id cote1");;
		}
		return res;
	}

	@Override
	public List<Cote1> selectAll() {
	//	Testee le 11/12/2018  --> OK
		List<Cote1> cotes1 = new ArrayList<Cote1>();
		Cote1 cote1 = null;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM cote1";	
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{

				cote1 = new  Cote1(res.getInt("id_cote1"),res.getString("code"),  res.getString("nom"));	
				cotes1.add(cote1);
				}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table cote1");;
		}
		
		
		return cotes1;
	}

	@Override
	public Object getByNom(String n) {
		// Testée le 11/12/2018  --> OK
		Cote1 cote1=null;
		List<Cote1> cotes1 = new ArrayList<Cote1>();
				int mes=0;
		String requete= "SELECT * FROM cote1 WHERE nom =\'"+n+"\'";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote1 = new  Cote1(res.getInt("id_cote1"),res.getString("code"),res.getString("nom"));	
					cotes1.add(cote1);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche des cotes1 nom "+n);
			}
			
			
			return cotes1;
	}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// Testée le 11/12/2018  --> OK
		Cote1 cote1=null;
		List<Cote1> cotes1 = new ArrayList<Cote1>();
				int mes=0;
				String nn=n.toUpperCase();
		String requete= "SELECT * FROM cote1 WHERE nom LIKE \'";
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

					cote1 = new  Cote1(res.getInt("id_cote1"),res.getString("code"),res.getString("nom"));	
					cotes1.add(cote1);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche LIKE des cotes1 nom "+n);
			}
			return cotes1;
			
	}
	
	// il faut des recherches par le code en plus des recherches par le nom
	
	public Object getByCode(String n) {
		// Testée le 11/12/2018  --> OK
		// je renvoie une liste, bien que le code soit unique. Justepour avoir un truc homogène
		Cote1 cote1=null;
		List<Cote1> cotes1 = new ArrayList<Cote1>();
				int mes=0;
		String requete= "SELECT * FROM cote1 WHERE code =\'"+n+"\'";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					cote1 = new  Cote1(res.getInt("id_cote1"),res.getString("code"),res.getString("nom"));	
					cotes1.add(cote1);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche des cotes1 code "+n);
			}
			
			
			return cotes1;
	}

	
	public Object getByCodeLike(String n, OptionRecherche opr) {
	//	Testée OK le 11/12/2018
		Cote1 cote1=null;
		List<Cote1> cotes1 = new ArrayList<Cote1>();
				int mes=0;
				String nn=n.toUpperCase();
		String requete= "SELECT * FROM Cote1 WHERE code LIKE \'";
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

					cote1 = new  Cote1(res.getInt("id_cote1"),res.getString("code"),res.getString("nom"));	
					cotes1.add(cote1);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche LIKE des cotes1 code "+n);
			}
			return cotes1;
			
	}
	
	
	
	
	
	

}

