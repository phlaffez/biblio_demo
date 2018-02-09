package com.DAO.biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.AuteurLangue;
import com.metier.biblio.Pays;
import com.outils.biblio.Cles;


// testée à la main le 09/02/2018 . Fonctionne
public class AuteurLangueDAO extends DAO<AuteurLangue> implements DAO_Liaison<AuteurLangue>
{

	public AuteurLangueDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}



	@Override
	public boolean create(AuteurLangue obj) {
		boolean retour = false;
		int res=0;
		int mes=0;
		if(!this.isPresent(obj))
		{
		String requete = "INSERT INTO auteur_langue (id_auteur, id_langue)";
		requete = requete+" VALUES ("+Integer.toString(obj.getIdAuteur())+",";
		requete = requete + Integer.toString(obj.getIdLangue())+")";
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
			System.out.println("Erreur SQL dans la création de la liaison Auteur Langue");
			System.out.println("Requete:  "+requete);
			mes=2;
		}
		}
		else
		{
			System.out.println("La liaison dans la table auteur_langue existe déjà");
			System.out.println("Auteur id = "+obj.getIdAuteur()+"  Langue id=  "+obj.getIdLangue());
			mes=1;
		}
		return retour;
	}
	
	

	@Override
	public boolean update(AuteurLangue obj) {
		// TODO Auto-generated method stub
		// On ne fait pas d'update dans cette table: soit la liaison Auteur Langur
		// existe, soit elle n'existe pas. Les champs de la table correspondent
		// à des identifiant des enregistrements lés, et sont donc invarriant
		return false;
	}

	
	
	@Override
	public boolean delete(AuteurLangue obj)
	{
		int res=0;
		int mes=0;
		boolean retour = false;
		if(this.isPresent(obj))
		{
			String requete = "DELETE FROM auteur_langue WHERE id_auteur = ";
			requete = requete+Integer.toString(obj.getIdAuteur())+" AND id_langue = ";
			requete = requete+Integer.toString(obj.getIdLangue());
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
			System.out.println("Auteur id= "+obj.getIdAuteur()+"  Langue id= "+obj.getIdLangue());
			
		}
		
		// TODO Auto-generated method stub
		return retour;
	}

	@Override
	public AuteurLangue findId(int id) {
		// TODO Auto-generated method stub
		// Pas d'iddans cette table.N'et donc pas à écrire
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		// Pas d'id dans cette table.N'et donc pas à écrire
		return 0;
	}

	@Override
	public List<AuteurLangue> selectAll() {
		// TODO Auto-generated method stub
		// Aucune implémentation à écrire.Cette méthode ne servirait pas
		return null;
	}
	
	



	@Override
	public boolean isPresent(AuteurLangue obj) {
		boolean retour = false;
		String requete = "SELECT * FROM auteur_langue WHERE ";
		requete = requete + "id_auteur = "+Integer.toString(obj.getIdAuteur());
		requete = requete+" AND id_langue ="+Integer.toString(obj.getIdLangue());
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
			System.out.println("ErreurSQLdans la recherche dans la table de liaison auteur_langue");
		}
		return retour;
	}
	
	@Override
	public Object getByCleLiaison(Cles cle, int id) {
		// TODO Auto-generated method stub
		String champCherche;
		List<Integer> resultat = new ArrayList<Integer>();
		int ii;
		String champCle = cle.toString();
		if(cle==Cles.id_auteur)
		{
			champCherche=Cles.id_langue.toString();
		}
		else
		{
			champCherche=Cles.id_auteur.toString();
		}
		String requete = "SELECT "+champCherche+" FROM auteur_langue WHERE ";
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

}
