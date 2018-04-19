package com.DAO.biblio;


import java.awt.Color;
// a tester avec les tables auteur, livres,genre, pays et langues
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

public class DAOTableFactory {

	public DAOTableFactory() {
		// TODO Auto-generated constructor stub
	}


public static JTable getTable(Connection conn, BddTables table)
{
	JTable tab = new JTable();
	int nbcol=0;
	String requete="";
	 String[] titre = new String[4];
	
	// on a les tables secondaires, pour lesquelles on n'affiche que l'id et le nom:
	
	if((table.equals(BddTables.GENRES))|
			(table.equals(BddTables.LANGUES))|
			(table.equals(BddTables.PAYS)))
	{
		 requete = "SELECT * FROM "+table+" ORDER BY id";
		nbcol=2;
		titre = new String[2];
		titre[0]="Id";
		titre[1]="Nom";
		
	}
	else if(table.equals(BddTables.AUTEURS))
	{
		// Les tables qu'on peut afficher en liste sont les auteurs et les livres
		 requete = "SELECT id, nom_aut, prenom_aut, annee_naiss FROM "+table;
		nbcol=4;
		titre = new String[4];
		titre[0]="Id";
		titre[1]="Nom";
		titre[2]="Prénom";
		titre[3]="Né en";
			
		}
	
		else if(table.equals(BddTables.LIVRES))
		{
			 requete = "SELECT id, nom_liv, date_pub, classement FROM "+table;
			nbcol=4;
			titre = new String[4];
			titre[0]="Id";
			titre[1]="Titre";
			titre[2]="Publié";
			titre[3]="Rangement";
	}
		else
		{
			// normalement on ne doit pas rentrer là
			nbcol=1;
			System.out.println("Erreur: appel de DAOTableFactory pour une table non autorisée");
		}
	
	
	
	try
	{
		Statement state = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
	
		ResultSet result = state.executeQuery(requete);
		result.last();
		int nblig = result.getRow();      // nombre de lignes
		result.beforeFirst();               // pour parcourir, on revient au début
		Object[][] data = new Object[nblig][nbcol];
		
		int nbreLine = 0;
		while (result.next()) {
			for (int i = 0; i < nbcol; i++)
			{
				data[nbreLine][i] = result.getObject(i +1).toString();
			}
			nbreLine++;
		}
		tab = new JTable(data, titre);
		tab.setRowHeight(20);
	}
	catch (Exception e)
	{
		System.out.println("Erreur dans la récupération de la table "+table);
	}
	return tab;
}
	
}