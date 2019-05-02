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
		// modifié le 12/12/2018 pour ajout du champ cote
		// testée partiellement le 12/12 (cas livre sans homonyme existant, sans auteur
		// reste à faire le cas avec auteurs, et celui ou il y aurait un titre homonyme dans la BDD
		
		boolean traiter = false;
		boolean retour = false; // par défaut. Si le livre existe déjà, on ne la crée pas
		// la vérification est cependant délicate, car on peut avoir fait des fautes 
		// d'othographe dans le titre. Il faudra donc remplacerla méthode de vérification 
		// par quelque chose de plus précis.
		
		// chercher si le titre est dans la base de données
		// getByNom renvoie un objet, donc il faut caster.Cet objet est une liste de livres
		// il faudra traiter le cas ou le titre est trouvé, savoir si on doit créer un
		// nouvel enregistrement
		ArrayList<Livre>livres = (ArrayList<Livre>)getByNom(obj.getNomLivre());
//		System.out.println(livres.size());
		if(livres.size()==0)
		{
			traiter = true;
		}
		else
		{
			traiter = false;
			// faire une fenetre pour poser la question d'une nouvelle création d'une fiche au même titre
		}
		
if (traiter)
		{
			//on crée seulement si le livre n'est pas déjà présent dans la base
			String requete = "INSERT INTO livres";
					requete = requete +"(nom_liv,cote,genre,langue,date_pub,date_acq,un_resume,classement)";
					requete = requete +" VALUES (";
					requete = requete + "'"+obj.getNomLivre()+"',";
					requete = requete + "'"+obj.getCote()+"',";
					requete = requete + Integer.toString(obj.getGenre())+",";
					requete = requete + Integer.toString(obj.getLangue())+",";
					requete = requete + "'"+obj.getDatePublication()+"',";
					requete = requete + "'"+obj.getDateAcquisition()+"',";
					requete = requete + obj.getUnResume()+",";
					requete = requete +obj.getClassement();
					requete = requete +")";
							
							
						
							
			int res = 0;
			int mes=0;
			try
			{
//			 	System.out.println(requete);
			 res = this.connex.createStatement(). executeUpdate(requete);
			 if(res==1)
				 {
				 
				 // la première partie est créée
				 // récupération de l'id
				 boolean totok = true;
				 if (obj.getAuteurs() !=null)
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
		// modifiée le 19/3/2018: doit être retestée (liste des auteurs)
		// Testéele 12/12/2018 - semble OK
		boolean retour = false;
		int res;
		int mes=0;
			// On ne peut faire d'update que si le LIVRE existe. 
		Livre livre = findId(obj.getId());
		if(livre != null)
		{
			try 
			{

				
				//  PLEIN DE FAUTES DE GUILLEMETS
				String requete = "UPDATE  livres SET nom_liv = '";
				requete = requete 	+obj.getNomLivre()+"',";
				requete = requete + "cote ='"+obj.getCote()+"',";				
				requete = requete + "genre = "+ Integer.toString(obj.getGenre())+",";
				requete = requete + "langue ="+Integer.toString(obj.getLangue())+",";
				requete = requete + "date_pub='"+obj.getDatePublication()+"',";
				requete = requete + "date_acq='"+obj.getDateAcquisition()+"',";
				requete = requete + "un_resume = "+obj.getUnResume()+",";
				requete = requete + "classement = "+Integer.toString(obj.getClassement());
				
				
	requete = requete  + " WHERE id ="+Integer.toString(obj.getId());
				
	//			System.out.println(requete);
				
				
				
				
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
		// testé le 30/1/03/2018  OK
		// modifiée le 1/2018 - testée OK
		// On vérifie l'existence
		boolean retour = false;
			Livre livre = findId(obj.getId());
			int res=0;
		int mes = 0;
		if(livre == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("Appel de delete dans livreDAO avec un objet livre null!");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			// il faut d'abord supprimer les liaisons auteur livre
			LivreAuteurDAO livreAuteurDAO =new LivreAuteurDAO(connex);
			// suppression ancienne liste
			// System.out.println("un");
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
		//correction bug le 30/03/2018
		// modifiée le 12/12/2018 testee OK
		Livre livre=null;
		String datePub="00/00/0000";
		String dateAcq="00/00/0000";
		boolean resum = false;
		int classe=0;
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
				String cote = res.getString("cote");
				if(cote==null) cote="";
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				if(res.getDate("date_pub")!=null)
				{
					 datePub = res.getDate("date_pub").toString();	
				}
				else
				{
					datePub = "Date de publication non renseignée";
				}
					
				if(res.getDate("date_acq")!=null)
				{
					 dateAcq = res.getDate("date_acq").toString();	
				}
				else
				{
					dateAcq = "Date d'acqisition non renseignée";
				}
					
				
				
				
				 resum = res.getBoolean("un_resume");
				 
				 if (res.getString("classement")!=null)
				 {
					 classe = res.getInt("classement"); 
				 }
				
				livre = new Livre(id,titre,genre,langue,datePub,dateAcq,resum,classe, cote, auteurs);
				
				
						  
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
		// modifié le 1212/2018 
		
		
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
				String cote = res.getString("cote");
				if(cote==null) cote="";
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				String datePub = res.getDate("date_pub").toString();
				String dateAcq = res.getDate("date_acq").toString();
		        if (datePub == null) datePub = "Date de publication non renseignée";
				boolean resum = res.getBoolean("un_resume");
				int classe = res.getInt("classement");
				
				livre = new Livre(id,titre,genre,langue,datePub,dateAcq,resum,classe, cote, auteurs);
				
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
		// modifiée et testée ok le 12/12/2018
		
		
		ArrayList<Auteur> auteurs=null;
		Livre livre=null;
		String datePub = "Date de publication non renseignée";
		String dateAcq = "Date d'acquisition non renseignée";
		int classe = 0;
		// a utiliser avec précautions. Que se passe-t-il s'il y a trop de livres
		// dans la base de données ?
		List<Livre> livres = new ArrayList<Livre>();  // ce qui sera renvoyé
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM livres WHERE nom_liv = \'"+n+"\'";
//		System.out.println(requete);
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
				String cote = res.getString("cote");
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				if(res.getDate("date_pub")!=null)
				{
					datePub = res.getDate("date_pub").toString();
				}
				if(res.getDate("date_acq")!=null)
				{
					dateAcq = res.getDate("date_acq").toString();
				}
				 
				boolean resum = res.getBoolean("un_resume");
				if(res.getString("classement")!=null)
						{
					 classe = res.getInt("classement");
						}
	
				
				livre = new Livre(id,titre,genre,langue,datePub,dateAcq,resum,classe, cote,auteurs);
				livres.add(livre);
			
			}
			
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche getByNom dans la table livres");;
		}
		

		return livres;
	}



	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// Modifiée et testée ok le 12/12/2018
		
		
		ArrayList<Auteur> auteurs=null;
		Livre livre;
		String datePub = "Date de publication non renseignée";
		String dateAcq = "Date d'acquisition non renseignée";
		int classe = 0;
		// a utiliser avec précautions. Que se passe-t-il s'il y a trop de livres
		// dans la base de données ?
		List<Livre> livres = new ArrayList<Livre>();  // ce qui sera renvoyé
		Livre unLivre;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM livres WHERE nom_liv LIKE \'";
		if(opr == OptionRecherche.CONTIEND)
		{
			requete = requete+"%";
		}
		requete = requete+n+"%\'";
//		System.out.println(requete);
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
				String cote = res.getString("cote");
				if(cote==null) cote ="";
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				if(res.getDate("date_pub")!=null)
				{
					datePub = res.getDate("date_pub").toString();
				}
				if(res.getDate("date_acq")!=null)
				{
					dateAcq = res.getDate("date_acq").toString();
				}
				 
				boolean resum = res.getBoolean("un_resume");
				if(res.getString("classement")!=null)
						{
					 classe = res.getInt("classement");
						}
	
				
				livre = new Livre(id,titre,genre,langue,datePub,dateAcq,resum,classe, cote, auteurs);
				livres.add(livre);
			
			}
			
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche getByNomLike dans la table livres");;
		}
		

		return livres;
	}
	
	
public List<Livre> selectLivresAuteur(int idAuteur) {
		
		// Renvoie les livres d'un auteur donné
	// TODO  DOI ETRE TESTEE
		
		Livre livre;
		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
		

		ArrayList<Livre> livres = new ArrayList<Livre>();  // ce qui sera renvoyé
		Livre unLivre;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String  requete = "SELECT * FROM livres INNER JOIN livre_auteurs ";
		requete = requete + "ON livres.id = livre_auteurs.id_livre WHERE livre_auteurs.id_auteur = ";
		requete = requete + Integer.toString(idAuteur);
//			System.out.println(requete);
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{

				int id = res.getInt("id");
				AuteurDAO auteurdao = new AuteurDAO(connex);
				 auteurs=(ArrayList<Auteur>) auteurdao.selectAuteursLivre(id);
				String titre = res.getString("nom_liv");
				String cote = res.getString("cote");
				if(cote==null) cote ="";
				int langue = res.getInt("langue");
				int genre = res.getInt("genre");
				
				String datePub = "Date de publication non renseignée";
//				System.out.println(datePub);
				if(res.getDate("date_pub")!= null)
					datePub = res.getDate("date_pub").toString();				
				String dateAcq = "Date de d'acquisition non renseignée";
				if(	res.getDate("date_acq")!=null)
					dateAcq = res.getDate("date_acq").toString();
				boolean resum = res.getBoolean("un_resume");
				int classe = res.getInt("classement");
	//			System.out.println(classe);
				livre = new Livre(id,titre,genre,langue,datePub,dateAcq,resum,classe, cote, auteurs);
				livres.add(livre);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche des livres d'un auteur");;
		}
		
		
		
		
		
		
		return livres;
	}
	
	
public Object getByCoteLike(String n, OptionRecherche opr) {
	// créée le 04/01/2019
	// A TESTER  
	
	
	ArrayList<Auteur> auteurs=null;
	Livre livre;
	String datePub = "Date de publication non renseignée";
	String dateAcq = "Date d'acquisition non renseignée";
	int classe = 0;

	List<Livre> livres = new ArrayList<Livre>();  // ce qui sera renvoyé
	Livre unLivre;
	int mes=0;    // s'il est nécessaire d'afficher des messages
	String requete = "SELECT * FROM livres WHERE cote LIKE \'";
	if(opr == OptionRecherche.CONTIEND)
	{
		requete = requete+"%";
	}
	requete = requete+n+"%\'";
//	System.out.println(requete);
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
			String cote = res.getString("cote");
			if(cote==null) cote ="";
			int langue = res.getInt("langue");
			int genre = res.getInt("genre");
			if(res.getDate("date_pub")!=null)
			{
				datePub = res.getDate("date_pub").toString();
			}
			if(res.getDate("date_acq")!=null)
			{
				dateAcq = res.getDate("date_acq").toString();
			}
			 
			boolean resum = res.getBoolean("un_resume");
			if(res.getString("classement")!=null)
					{
				 classe = res.getInt("classement");
					}

			
			livre = new Livre(id,titre,genre,langue,datePub,dateAcq,resum,classe, cote, auteurs);
			livres.add(livre);
		
		}
		
		res.close();
	}
	catch (SQLException e)
	{
		mes=2;
		System.out.println("Erreur SQL lors de la recherche getByCoteLike dans la table livres");;
	}
	

	return livres;
}





}
