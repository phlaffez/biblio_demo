package com.ihm.biblio;

import java.awt.Color;

import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;

import phl.outils.panneaux.outilsStandards.PanneauTableStandard;

public class PanneauLivres  extends PanneauTableStandard{
	
	private Livre livre = new Livre();
	private DaoFactoryMySQL factory = new DaoFactoryMySQL();
	private JScrollPane jsp;

	public PanneauLivres(String titrePan, Color colFond, Color coulTexPP, Color coulPanTab, Color coulPanText,
			Color colEnt, Color colEntTex, Color cboufon, Color cboutex) {
		super(titrePan, colFond, coulTexPP, coulPanTab, coulPanText, colEnt, colEntTex, cboufon, cboutex);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initBoutons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initTable() {
	
		table = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.LIVRES);
		jsp= new JScrollPane(table);
		 table.setBackground(this.coulPanTab);
this.panTable.add(jsp);	
this.setVisible(false);
this.setVisible(true);
	}

}
