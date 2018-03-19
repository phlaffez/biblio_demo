package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;
import com.metier.biblio.LivreAuteur;
import com.metier.biblio.Pays;
import com.outils.biblio.Cles;

public class LivreDAO  extends DAO<Livre> implements DAO_Noms<Livre>{

	public LivreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public boolean create(Livre obj) {
		// testé le 07/02/2018 OK
		boolean retour = false; // par défaut. Si le livre existe déjà, on ne la crée pas
		// la érification est cependant délicate, car on peut avoir fait des fautes 
		// d'othographe dans le titre. Il faudra donc remplacerla méthode de vérification 
		// par quelque chose de plus précis.
		
		// chercher si le titre est dans la base de données
		// getByNom renvoie un objet, donc il faut caster
		Livre livre = (Livre)getByNom(obj.getNomLivre());
		if(livre ==null)
		{
			//on crée seulement si le livre n'est pas déjà présent dans la base
			String requete = "INSERT INTO livres";
					requete = requete +"(nom_liv,genre,langue,date_pub,un_resume,classement)";
					requete = requete +" VALUES (";
					requete = requete + "'"+obj.getNomLivre()+"',";
					requete = requete + Integer.toString(obj.getGenre())+",";
					requete = requete + Integer.toString(obj.getLangue())+",";
					requete = requete + "'"+obj.getDatePublication()+"',";
					requete = requete + obj.getUnResume()+",";
					requete = requete + "'"+obj.getClassement()+"'";
					requete = requete +")";
							
							
						
							
			int res = 0;
			int mes=0;
			try
			{
			 	
			 res = this.connex.createStatement(). executeUpdate(requete);
			 if(res==1)
				 {
				 
				 // la première partie est créée
				 // récupération de l'id
				 boolean totok = true;
				 if (obj.getAuteurs().size()!=0)
				 {
					 int idl = this.lastId();
					 int ida=0;
					 
					 DAO livreAuteurDAO =new LivreAuteurDAO(connex);
					 LivreAuteur livreAuteur = null;
					 for (int i=0;i<obj.getAuteurs().size();i++)
					 { 	 ida=obj.getAuteurs().get(i).getId();
					     livreAuteur = new LivreAuteur(ida,idl);
					     totok = (totok & livreAuteurDAO.create(livreAuteur));
					     
					 }
				 }
					// création des enregistrements dans AuteurLivre
				 if (totok == false)
				 {
					 System.out.println("Problème dans l'enregistrement des auteurs");
					 System.out.println("du livre: "+obj.getNomLivre());
				 }
				 retour = true & totok;
				 mes=1;
				 }
		}
			catch (SQLException e)
			{
				// remplacer par un popup
				System.out.println("Erreur SQL lors de la création du livre: "+obj.getNomLivre());
				mes=2;
			}
		}
		
		// ici mettre un popup si mes=0 (livre existant) ou 2 (pb SQL)
		return retour;
	}
	
	
	
	
	
	

	@Override
	public boolean update(Livre obj) {
		// testé le 07/02/2018 OK
		boolean retour = false;
		int res;
		int mes=0;
			// On ne peut faire d'update que si le LIVRE existe. 
		Livre livre = findId(obj.getId());
		if(livre != null)
		{
			try 
			{

				
				
				String requete = "UPDATE  livres SET nom_liv = ";
				requete = requete 	+obj.getNomLivre()+"',";
				
				requete = requete + "genre = "+ Integer.toString(obj.getGenre())+",";
				requete = requete + "langue ="+Integer.toString(obj.getLangue())+",";
				requete = requete + "date_pub"+obj.getDatePublication()+"',";
				requete = requete + "un_resume = "+obj.getUnResume()+",";
				requete = requete + "classement = "+"'"+obj.getClassement()+"'";
				
				
	requete = requete  + " WHERE id ="+Integer.toString(obj.getId());
				
				
				
				
				
				
				res = this.connex.createStatement(). executeUpdate(requete);
				if (res==1)
				{
					retour = true;
					mes=1;
			
					LivreAuteurDAO livreAuteurDAO =new LivreAuteurDAO(connex);
					// suppression ancienne liste
					boolean res1 =  livreAuteurDAO.deleteByCleLiaison(Cles.id_livre,obj.getId());
					
					
					// ajout nouvelle liste
					ArrayList<Auteur> la = (ArrayList<Auteur>)obj.getAuteurs();
					if(la!=null)
					{
						LivreAuteur li;
						for(int i=0;i<la.size();i++)
						{
							li=new LivreAuteur(la.get(i).getId(),obj.getId());
						    res1=livreAuteurDAO.create(li);
						}
					}
					
				}
			}
			catch (SQLException e)
			{
				// Prévoir un popup
				System.out.println("Erreur SQL lors de la mise à jour du livre "+obj.getNomLivre());
				mes=2;
			}
		}  // prévoir un popup si mes = 0 (livre inexistant) ou 2 (pb SQL)
		return retour;
	}

	
	
	@Override
	public boolean delete(Livre obj) {
		// testé le
		// On vérifie l'existence
		boolean retour = false;
			Livre livre = findId(obj.getId());
			int res=0;
		int mes = 0;
		if(livre == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("Le livre  id="+Integer.toString(livre.getId())+" "+obj.getNomLivre()+" n'est pas dans la base de donnés");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			// il faut d'abord supprimer les liaisons auteur livre
			LivreAuteurDAO livreAuteurDAO =new LivreAuteurDAO(connex);
			// suppression ancienne liste
			boolean res1 =  livreAuteurDAO.deleteByCleLiaison(Cles.id_livre,obj.getId());
			try
			{
								String requete = "DELETE FROM livres WHERE id = "+Integer.toString(obj.getId());
				
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
				System.out.println("Problème SQL lors de la suppression du livre "+
				                  obj.getNomLivre()+" id= "+Integer.toString(livre.getId()));;
	
			}
		}
		return retour;
	}
	

	@Override
	public Livre findId(int id) {
		Livre livre=null;
		int mes=0;
		String requete= "SELECT * FROM livres WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				LivreAuteurDAO livreauteurdao = new LivreAuteurDAO(connex);
				ArrayList<Auteur> auteurs=(ArrayList<Auteur>) livreauteurdao.getListeByCleLiaison(Cles.id_livre, id);
				String titre = res.getString("nom_liv");
				if(titre==null) titre="";
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				String datepub = res.getDate("date_pub").toString();
		        if (datepub == null) datepub = "Date de publication non renseignée";
				boolean resum = res.getBoolean("un_resume");
				String classe = res.getString("classement");
				if(classe==null) classe="Emplacement nonrenseigné";
				
				livre = new Livre(id,titre,genre,langue,datepub,resum,classe, auteurs);
				
				
						  
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche du livre id= "+Integer.toString(id));
		}

		return livre;
	}

	@Override
	public int lastId() {
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM livres ORDER BY id DESC LIMIT 1";
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
			System.out.println("Erreur SQL lors dela recherche du dernier id livres");;
		}
		return res;
	}
	
	

	@Override
	public List<Livre> selectAll() {
		
		// testée le 16/03/2018 - OK
		
		ArrayList<Auteur> auteurs=null;
		Livre livre;
		
		// a utiliser avec précautions. Que se passe-t-il s'il y a trop de livres
		// dans la base de données ?
		ArrayList<Livre> livres = new ArrayList<Livre>();  // ce qui sera renvoyé
		Livre unLivre;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM livres";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{

				int id = res.getInt("id");
				LivreAuteurDAO livreauteurdao = new LivreAuteurDAO(connex);
				 auteurs=(ArrayList<Auteur>) livreauteurdao.getListeByCleLiaison(Cles.id_livre, id);
				String titre = res.getString("nom_liv");
				if(titre==null) titre="";
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				String datepub = res.getDate("date_pub").toString();
		        if (datepub == null) datepub = "Date de publication non renseignée";
				boolean resum = res.getBoolean("un_resume");
				String classe = res.getString("classement");
				if(classe==null) classe="Emplacement nonrenseigné";
				
				livre = new Livre(id,titre,genre,langue,datepub,resum,classe, auteurs);
				
				livres.add(livre);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table livres");;
		}
		
		
		
		
		
		
		return livres;
	}

	@Override
	public Object getByNom(String n) {
		// TODO Auto-generated method stub:
		// voir comment on peut implémenter ça. Pour le moment je laisse avec
		// une recherche stricte
		
		ArrayList<Auteur> auteurs=null;
		Livre livre;
		
		// a utiliser avec précautions. Que se passe-t-il s'il y a trop de livres
		// dans la base de données ?
		List<Livre> livres = new ArrayList<Livre>();  // ce qui sera renvoyé
		Livre unLivre;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM livres WHERE nom_liv = '"+n+"'";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{

				int id = res.getInt("id");
				LivreAuteurDAO livreauteurdao = new LivreAuteurDAO(connex);
				 auteurs=(ArrayList<Auteur>) livreauteurdao.getListeByCleLiaison(Cles.id_livre, id);
				String titre = res.getString("nom_liv");
				if(titre==null) titre="";
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				String datepub = res.getDate("date_pub").toString();
		        if (datepub == null) datepub = "Date de publication non renseignée";
				boolean resum = res.getBoolean("un_resume");
				String classe = res.getString("classement");
				if(classe==null) classe="Emplacement nonrenseigné";
				
				livre = new Livre(id,titre,genre,langue,datepub,resum,classe, auteurs);
				livres.add(livre);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table livres");;
		}
		
		
		
		
		
		
		return livres;
	}


}
