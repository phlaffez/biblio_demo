package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;
import com.metier.biblio.LivreAuteur;
import com.outils.biblio.Cles;

public class LivreAuteurDAO extends DAO<LivreAuteur> implements DAO_Liaison<LivreAuteur>
{
 
	public LivreAuteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(LivreAuteur obj) {
		
		// teste le 16/3/2018  OK
		boolean retour = false;
		int res=0;
		int mes=0;
		if(!this.isPresent(obj))
		{
		String requete = "INSERT INTO livre_auteurs (id_auteur, id_livre)";
		requete = requete+" VALUES ("+Integer.toString(obj.getIdAuteur())+",";
		requete = requete + Integer.toString(obj.getIdLivre())+")";
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
			// il faudrait un popup
			System.out.println("Erreur SQL dans la création de la liaison Auteur Livre");
			System.out.println("Requete:  "+requete);
			mes=2;
		}
		}
		else
		{
			System.out.println("La liaison dans la table auteur_livre existe déjà");
			System.out.println("Auteur id = "+obj.getIdAuteur()+"  Langue id=  "+obj.getIdLivre());
			mes=1;
		}
		return retour;
	}

	@Override
	public boolean update(LivreAuteur obj) {
		// On ne fait pas d'update dans cette table: soit la liaison Auteur Langur
				// existe, soit elle n'existe pas. Les champs de la table correspondent
				// à des identifiant des enregistrements lés, et sont donc invarriant
		return false;
	}

	@Override
	public boolean delete(LivreAuteur obj) {
		
		// testée le 16/03/2018  OK
		int res=0;
		int mes=0;
		boolean retour = false;
		if(this.isPresent(obj))
		{
			String requete = "DELETE FROM livre_auteurs WHERE id_auteur = ";
			requete = requete+Integer.toString(obj.getIdAuteur())+" AND id_livre = ";
			requete = requete+Integer.toString(obj.getIdLivre());
			try
			{
				res = this.connex.createStatement(). executeUpdate(requete);
				if(res==1)
				{
					retour=true;
				}
			}
			
			catch (SQLException e)
			{
				// popup
				System.out.println("Erreur SQL lors de la suppression d'une liaison:");
				System.out.println(requete);
			}
			}
			
		else
		{
			// ne provoque pas une erreur
			retour=true;
			mes=1;
			System.out.println("Demande de suppression d'une liaison inexistante:");
			System.out.println("Auteur id= "+obj.getIdAuteur()+"  Langue id= "+obj.getIdLivre());
			
		}
		
		// TODO Auto-generated method stub
		return retour;
	}

	@Override
	public LivreAuteur findId(int id) {
		// Pas d'id dans cette table.N'et donc pas à écrire
		return null;
	}

	@Override
	public int lastId() {
		// Pas d'id dans cette table.N'et donc pas à écrire
		return 0;
	}

	@Override
	public List<LivreAuteur> selectAll() {
		// Aucune implémentation à écrire.Cette méthode ne servirait pas
		return null;
	}

	

	@Override
	public boolean isPresent(LivreAuteur obj) {
		
		// teste le 16/3/2018 OK
		boolean retour = false;
		String requete = "SELECT * FROM livre_auteurs WHERE ";
		requete = requete + "id_auteur = "+Integer.toString(obj.getIdAuteur());
		requete = requete+" AND id_livre ="+Integer.toString(obj.getIdLivre());
	//	System.out.println(requete);
		try
		{
			ResultSet res1 =  this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			if (res1.first())
			{
				retour = true;
			}
			
		}
		catch (SQLException e)
		{
			// Il faudra mettre un popup
			System.out.println("ErreurSQLdans la recherche dans la table de liaison auteur_livre");
		}
		return retour;
	}
	
	@Override
	public Object getByCleLiaison(Cles cle, int id) {
		// Testee le 16/03/2018  OK
		String champCherche;
		List<Integer> resultat = new ArrayList<Integer>();
		int ii;
		String champCle = cle.toString();
		if(cle==Cles.id_auteur)
		{
			champCherche=Cles.id_livre.toString();
		}
		else
		{
			champCherche=Cles.id_auteur.toString();
		}
		String requete = "SELECT "+champCherche+" FROM livre_auteurs WHERE ";
		requete= requete + champCle+" ="+Integer.toString(id);
		try
		{
			ResultSet res= this.connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
			while (res.next())
			{
				ii=res.getInt(1);
				resultat.add(ii);
			}
			res.close();
		}
		catch (SQLException e)
		{    e.printStackTrace();
			System.out.println("Erreur SQL dans la récupération de liaisons:");
			System.out.println(requete);
		}
		
		return resultat;
	}

	@Override
	public Object getListeByCleLiaison(Cles cle, int id) {
		// teste le 16/3/2018  OK
		// récupérer la liste des entiers correspondants:
		List<Integer> listeres = (List<Integer>)this.getByCleLiaison(cle, id);
		if(listeres.size()==0)
		{
			return null;   // liste vide
		}
		else
		{
			if(cle==Cles.id_auteur)
			{
	//			System.out.println("auteurs");
				// on retourne la liste des livres de cet auteur
				DAO livreDAO = new LivreDAO(connex);
				List<Livre> livres = new ArrayList<Livre>();
				for(int i=1;i<listeres.size();i++)
				{
					livres.add((Livre)livreDAO.findId(listeres.get(i)));
				}
				
				return livres;
			}
			else
				
				
	//			System.out.println("livres");
			{
				// on retourne la liste des auteurs de ce livre
				DAO auteurDAO = new AuteurDAO(connex);
				List<Auteur> auteurs = new ArrayList<Auteur>();
				for(int i = 1; i<listeres.size();i++)
				{
					auteurs.add(((Auteur)auteurDAO.findId(listeres.get(i))));
				}
				return auteurs;
			}
		}
		
		
		
	}

	@Override
	public boolean deleteByCleLiaison(Cles cle, int id) {
		String requete;
		int res;
		int mes=0;
		// correction d'un bug le 30/03/2018
		if(cle == Cles.id_auteur)
		{
			// on efface tous les liaisons livre auteur de cet auteur
			requete = "DELETE * FROM livre_auteurs WHERE id_auteur = "+Integer.toString(id);
		}
		else
		{
			// le contraire, id on efface toutes les liaisons pour ce livre
			requete = "DELETE FROM livre_auteurs WHERE id_livre ="+Integer.toString(id);
		}
		boolean retour = false;
		System.out.println(requete);
		try
		{
			res = this.connex.createStatement(). executeUpdate(requete);
			if(res==1)
			{
				retour = true;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Erreur SQLdans la suppression dans la table de liaison livres auteurs");
			System.out.println(cle.toString()+" = "+id);
			mes=1;
		}
		
		return retour;
	}

}
