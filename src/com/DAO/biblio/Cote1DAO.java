package com.DAO.biblio;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.DAO.biblio.DAO;
import com.DAO.biblio.DAO_Noms;
import com.DAO.biblio.OptionRecherche;
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
						// peut créer la fiche
						req= "INSERT INTO cote1 (code, nom) VALUES (\'"+obj.getCode()+"\',\'"+obj.getNom()+"\')";
						try
						{
							res = this.connex.createStatement(). executeUpdate(req);
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
		
		
		return false;
	}

	@Override
	public boolean update(Cote1 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cote1 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cote1 findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cote1> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNom(String n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// TODO Auto-generated method stub
		return null;
	}

}

