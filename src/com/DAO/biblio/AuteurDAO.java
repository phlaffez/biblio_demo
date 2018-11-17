package com.DAO.biblio;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Auteur;
import com.metier.biblio.Genre;
import com.metier.biblio.Livre;
import com.metier.biblio.LivreAuteur;

import phl.outils.panneaux.outilsStandards.FenetreMessage;

public class AuteurDAO  extends DAO<Auteur> implements DAO_Noms<Auteur>, DAO_Noms_Prenoms <Auteur>{
	
	// pour la fenêtre de messages
	private String titre="Bibliothèque: Auteurs";
	private String titre2="Création / Modification d'une fiche auteur";
	

	public AuteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Auteur obj) {
		// testé le 16/03/2018 - OK
		boolean retour = false; // par défaut. Si l'auteur existe déjà, on ne le crée pas
		// On fait la distinction avec nom, prenom et annee de naissance
		
		// chercher si l'auteur est dans la base de données 
		// getByNomPrenom renvoie un objet, donc il faut caster
		String req = "SELECT * FROM auteurs WHERE nom_aut = \'";
		req=req+obj.getNom()+"\' AND prenom_aut = \'";
		req = req+obj.getPrenom()+"\' AND annee_naiss = ";
		req = req+Integer.toString(obj.getAnnee_naiss());
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(req);
		
		
		if(res1.first())
		{
			String titre;
			String titre2;
			String mess  ="La fiche auteur "+obj.getNom()+" "+obj.getPrenom()+" né en "+obj.getAnnee_naiss()+" \n existe déjà";
			FenetreMessage fen = new FenetreMessage(this.titre,this.titre2,mess,
					300,300,Color.lightGray,Color.black);
		}
		
		else
		{
			
			String requete = "INSERT INTO auteurs";
					requete = requete +"(nom_aut,prenom_aut,pays_aut,annee_naiss, annee_deces,infos)";
					requete = requete +" VALUES (";
					requete = requete + "'"+obj.getNom()+"',\'";
					requete = requete + obj.getPrenom()+"\',";
					requete = requete + Integer.toString(obj.getId_pays())+",";
					requete = requete + ""+obj.getAnnee_naiss()+",";
					requete = requete + obj.getAnnee_deces()+",";
					requete = requete + "'"+obj.getInfo()+"'";
					requete = requete +")";
					System.out.println(requete);
							
							
						
							
			int res = 0;
			int mes=0;
			try
			{
			 	
			 res = this.connex.createStatement(). executeUpdate(requete);
			 if(res==1)
				 {
				 
				 // la première partie est créée
				 // récupération de l'id
			
				 retour = true ;
				 mes=1;
				 }
		}
			catch (SQLException e)
			{
				// remplacer par un popup
				System.out.println("Erreur SQL lors de la création de l'auteur: "+obj.getNom()+" "+obj.getPrenom());
				mes=2;
			}
		}
		}
		
		catch (SQLException e)
		{
			System.out.println("Probleme SQL lors de la verification de l'existance de la fiche auteur");
			System.out.println(obj.getNom()+" "+obj.getPrenom()+" né en "+obj.getAnnee_naiss());
		}
		
		// ici mettre un popup si mes=0 (auteur existant) ou 2 (pb SQL)
		return retour;
	}
	
	

	@Override
	public boolean update(Auteur obj) {
		// testé le 16/03/2018 OK
		boolean retour = false;
		int res;
		int mes=0;
			// On ne peut faire d'update que si le l'auteur existe. 
		Auteur auteur = findId(obj.getId());
		if(auteur != null)
		{
			try
			{
				String requete = "UPDATE  auteurs SET nom_aut = ";
				requete = requete 	+"'"+obj.getNom()+"',";
				
				requete = requete + "prenom_aut = '"+ obj.getPrenom()+"',";
				requete = requete + "pays_aut ="+Integer.toString(obj.getId_pays())+",";
				requete = requete + "annee_naiss ="+obj.getAnnee_naiss()+",";
				requete = requete + "annee_deces = "+obj.getAnnee_deces()+",";
				requete = requete + "infos = "+"'"+obj.getInfo()+"'";
				
	requete = requete  + " WHERE id ="+Integer.toString(obj.getId());			
	System.out.println(requete);
				res = this.connex.createStatement(). executeUpdate(requete);
				if (res==1)
				{
					retour = true;
					mes=1;
				}
			}
			
			catch (SQLException de)
			{
				// remplacer par un popup
				System.out.println("Erreur SQL lors de la mise à jour de l'auteur: "+obj.getNom()+" "+obj.getPrenom());
				mes=2;
			}
			
			
		}
		return retour;
		
	}

	@Override
	public boolean delete(Auteur obj) {
		// testé le 16/03/2018 - OK - version basique
		// Il faut modifier 
		// On vérifie l'existence d'enregistrement dans les tables de liaison
		// AuteurLangue et AuteurGenre avant d'essacer, car il y a des contraintes
		// de clés étrangères.
		// idem LivreAuteur et AuteurGenre
		boolean retour = false;
			Auteur auteur = findId(obj.getId());
			int res=0;
		int mes = 0;
		if(auteur == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("L'auteur  id="+Integer.toString(auteur.getId())+" "+obj.getNom()+" "+obj.getPrenom()+" n'est pas dans la base de donnés");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			try
			{
								String requete = "DELETE FROM auteurs WHERE id = "+Integer.toString(obj.getId());
							//	System.out.println(requete);
				
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
				System.out.println("Problème SQL lors de la suppression de l'auteur "+
				                  obj.getNom()+" "+obj.getPrenom()+" id= "+Integer.toString(auteur.getId()));;
	
			}
		}
		return retour;
	}

	@Override
	public Auteur findId(int id) {
		// Testée le 16/03/2018 OK
		Auteur auteur=null;
		int mes=0;
		String requete= "SELECT * FROM auteurs WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
							
				auteur = new Auteur(id,res.getString("nom_aut"),
						  res.getString("prenom_aut"),res.getInt("pays_aut"),
						  res.getInt("annee_naiss"),
						  res.getInt("annee_deces"),
						  res.getString("infos"));
				
						  
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche de l'auteur id= "+Integer.toString(id));
			System.out.println(auteur.getNom()+" "+auteur.getPrenom());
		}

		return auteur;
	}

	@Override
	public int lastId() {
		// test le 16/3/2018 OK
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM auteurs ORDER BY id DESC LIMIT 1";
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
			System.out.println("Erreur SQL lors dela recherche du dernier id auteurs");;
		}
		return res;
	}

	@Override
	public List<Auteur> selectAll() {
		//A manier avec précautions
		List<Auteur> auteurs = new ArrayList<Auteur>();
		Auteur auteur = null;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM auteurs";	
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{

				auteur = new  Auteur(res.getInt("id"),res.getString("nom_aut"),
						  res.getString("prenom_aut"),res.getInt("pays_aut"),
						  res.getInt("annee_naiss"),
						  res.getInt("annee_deces"),
						  res.getString("infos"));	
				auteurs.add(auteur);
				}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table auteurs");;
		}
		
		
		return auteurs;
	}

	@Override
	public Object getByNom(String n) {
		
	//	Testée le 16/03/2018   OK 
		Auteur auteur=null;
		List<Auteur> auteurs = new ArrayList<Auteur>();
				int mes=0;
		String requete= "SELECT * FROM auteurs WHERE nom_aut =\'"+n+"\'";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					auteur = new  Auteur(res.getInt("id"),res.getString("nom_aut"),
							  res.getString("prenom_aut"),res.getInt("pays_aut"),
							  res.getInt("annee_naiss"),
							  res.getInt("annee_deces"),
							  res.getString("infos"));	
					auteurs.add(auteur);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche des auteurs nommés "+n);
			}
			
			
			return auteurs;
		}
	
	

	@Override
	public Object getByNomPrenom(String n, String p) {
		
		// testée le 16/03/2018  OK
		Auteur auteur=null;
		List<Auteur> auteurs = new ArrayList<Auteur>();
		// je cherche une liste même si a priori on ne doit pas avoir 2 auteurs de mêmes 
		// noms et prenoms. Quoique cela puisse finallement quand même ariver 
				int mes=0;
		String requete= "SELECT * FROM auteurs WHERE nom_aut =\'"+n+"\'";
				requete = requete +"AND prenom_aut =\'"+p+"\'";
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					auteur = new  Auteur(res.getInt("id"),res.getString("nom_aut"),
							  res.getString("prenom_aut"),res.getInt("pays_aut"),
							  res.getInt("annee_naiss"),
							  res.getInt("annee_deces"),
							  res.getString("infos"));	
					auteurs.add(auteur);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche des auteurs nommés "+n);
			}
			
			
			return auteurs;
		}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		
	//	crééele 23/11/2018
		Auteur auteur=null;
		List<Auteur> auteurs = new ArrayList<Auteur>();
				int mes=0;
		String requete= "SELECT * FROM auteurs WHERE nom_aut LIKE \'";
		if(opr == OptionRecherche.CONTIEND)
		{
			requete = requete + "%";
		}
		requete = requete+n+"%\'";	
			try
			{
				ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				while (res.next())
				{

					auteur = new  Auteur(res.getInt("id"),res.getString("nom_aut"),
							  res.getString("prenom_aut"),res.getInt("pays_aut"),
							  res.getInt("annee_naiss"),
							  res.getInt("annee_deces"),
							  res.getString("infos"));	
					auteurs.add(auteur);
					}
				res.close();
			}
			catch (SQLException e)
			{
				mes=2;
				System.out.println("Erreur SQL lors de la recherche des auteurs nommés "+n);
			}
			
			
			return auteurs;
		}

	public List<Auteur> selectAuteursLivre(int idLivre) {
		//Renvoie tous les auteurs d'un titre donné
		List<Auteur> auteurs = new ArrayList<Auteur>();
		Auteur auteur = null;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM auteurs INNER JOIN livre_auteurs ";
				requete = requete + "ON auteurs.id = livre_auteurs.id_auteur WHERE livre_auteurs.id_livre = ";
				requete = requete + Integer.toString(idLivre);
	//			System.out.println(requete);
		
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{

				auteur = new  Auteur(res.getInt("id"),res.getString("nom_aut"),
						  res.getString("prenom_aut"),res.getInt("pays_aut"),
						  res.getInt("annee_naiss"),
						  res.getInt("annee_deces"),
						  res.getString("infos"));	
				auteurs.add(auteur);
				}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité des auteurs d'un livre");;
		}
		
		
		return auteurs;
	}
	

}
