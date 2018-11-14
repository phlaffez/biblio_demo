package com.DAO.biblio;


import java.awt.Color;
// a tester avec les tables auteur, livres,genre, pays et langues
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import phl.outils.tables.ModelTablePhl;

public class DAOTableFactory {

	public DAOTableFactory() {
		// TODO Auto-generated constructor stub
	}


public static JTable getTable(Connection conn, BddTables table)
{
	ModelTablePhl model;
	JTable tab = new JTable();
		
	int nbcol=0;
	int nblig=0;
	String requete="";
	 String[] titre = new String[4];
	 Object[][] data;
	
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
	else if(table.equals(BddTables.AUTEURS)|table.equals(BddTables.AUTEURS2))
	{
		// Une table d'auteurs vide
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
			 requete = "SELECT id, nom_liv, date_pub, lieux FROM "+table;
	//		 System.out.println(requete);
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
		if(!table.equals(BddTables.AUTEURS2))
		{
			
		
		Statement state = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
//		System.out.println(requete);
		ResultSet result = state.executeQuery(requete);
		result.last();
		 nblig = result.getRow();      // nombre de lignes
		result.beforeFirst();               // pour parcourir, on revient au début
		 data = new Object[nblig][nbcol];
		
		int nbreLine = 0;
		while (result.next()) {
			for (int i = 0; i < nbcol; i++)
			{
				    data[nbreLine][i] = result.getObject(i +1).toString();
			}
			nbreLine++;
		}
		}
		else
		{
			nblig = 1;
			 data = new Object[nblig][nbcol];
			 for (int i =0;i<nbcol; i++)
			 {
				 data[0][i]="";
			 }
		}
		
		model = new ModelTablePhl(data,titre);
		tab = new JTable(model)	{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
				};
		tab.setRowHeight(20);
	}
	catch (Exception e)
	{
		System.out.println("DAO Tableactory : Erreur dans la récupération de la table "+table);
	}
	return tab;
}
	
}