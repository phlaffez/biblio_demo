package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Pays;
import com.metier.biblio.ResumeLivre;

public class ResumeLivreDAO extends DAO<ResumeLivre>
{
	// Ici il n'y a pas d'interface supplémentaire à implémenter

	public ResumeLivreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(ResumeLivre obj) {
		// testé le 
		boolean retour = false; // par défaut. Si le résumé existe déjà, on ne le crée pas
	
		// chercher siil est dans la base de données.
		// on ne peut chercher que par id. Il faudra caster
		ResumeLivre resume = (ResumeLivre)findId(obj.getId());
		if(resume ==null)
		{
			// on crée uniquement si le résumé n'est pas déjà là
			String requete = "INSERT INTO resume_livres (id_livre,resume) VALUES (";
			requete = requete+Integer.toString(obj.getId())+",";
				requete = requete	+ "\'"+obj.getResume()+"\')";
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
				System.out.println("Erreur SQL lors de la création du résumé du livre id : "+obj.getId());
				mes=2;
			}
		}
		
		// ici mettre un popup si mes=0 (resumé existant) ou 2 (pb SQL)
		return retour;
	}

	@Override
	public boolean update(ResumeLivre obj) {
		// testé le 
		boolean retour = false;
		int res;
		int mes=0;
			// On ne peut faire d'update que si le résumé existe. 
		ResumeLivre resume = findId(obj.getId());
		if(resume != null)
		{
			try 
			{
				String requete = "UPDATE resume_livres SET resume =\""+obj.getResume()+"\"";
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
				System.out.println("Erreur SQL lors de la mise à jour du résumé du livre id = "+obj.getId());
				mes=2;
			}
		}  // prévoir un popup si mes = 0 (resumé inexistant) ou 2 (pb SQL)
		return retour;
	}

	@Override
	public boolean delete(ResumeLivre obj) {
		// testé le 
		// On vérifie l'existence
		boolean retour = false;
			ResumeLivre resume = findId(obj.getId());
			int res=0;
		int mes = 0;
		if(resume == null)
		{
			// rien à faire, mais quand même un popup
			mes=3;
			System.out.println("Le résumé du livre id  "+obj.getId()+" n'est pas dans la base de donnés");
			System.out.println("Suppression inutile");
			retour = true;
		}
		else
		{
			try
			{
								String requete = "DELETE FROM resume_livres WHERE id = "+Integer.toString(obj.getId());
				
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
				System.out.println("Problème SQL lors de la suppression du résumé du livre id  "+obj.getId());
	
			}
		}
		return retour;
	}

	@Override
	public ResumeLivre findId(int id) {
		ResumeLivre resume=null;
		int mes=0;
		String requete= "SELECT * FROM resume_livres WHERE id ="+Integer.toString(id);
		try
		{
			ResultSet res =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res.first())
			{
				resume = new ResumeLivre(id,res.getString("resume"));
				mes=1;
			}
			
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Problème SQL lors de la recherche du résumé du livre  id= "+Integer.toString(id));
		}

		return resume;
	}

	@Override
	public int lastId() 
		// A priori pas utile, mais je l'écrit quand même, vu que c'est simple
	{
		int res = -1;  // ce qui sera retourné si on ne trouve pas 
		int mes=0;
		String requete = "SELECT * FROM resume_livres ORDER BY id DESC LIMIT 1";
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			
		{
			res  = res1.getInt("id_livre");
		}
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors dela recherche du dernier id de resume_livres");;
		}
		return res;
	}
	@Override
	public List<ResumeLivre> selectAll() {
		// probablement inutile. et dangereux à employer ??
		List<ResumeLivre> resumes = new ArrayList<ResumeLivre>();  // ce qui sera renvoyé
		ResumeLivre unResume;
		int mes=0;    // s'il est nécessaire d'afficher des messages
		String requete = "SELECT * FROM resume_livre";
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				unResume = new ResumeLivre(res.getInt("id_livre"),res.getString("resume"));
				resumes.add(unResume);
			}
			res.close();
		}
		catch (SQLException e)
		{
			mes=2;
			System.out.println("Erreur SQL lors de la recherche de la totalité de la table resume_livres");;
		}
		
		
		
		
		return resumes;
	}

}
