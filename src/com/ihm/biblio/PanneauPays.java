package com.ihm.biblio;

import java.awt.Color;

import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.dbacces.biblio.Mysql_Connect;

import phl.outils.panneaux.outilsStandards.PanneauOutilsStandard;

public class PanneauPays extends PanneauOutilsStandard{

	public PanneauPays(String titrePan,Color colFond,
            
           Color coulTexPP,
           Color panTab,
           Color coulPanText,
           Color colEnt,
            Color colEntTex,
            Color cboufon,
            Color cboutex) 
	{
		 super(titrePan,colFond,coulTexPP,panTab,coulPanText,colEnt,
                  colEntTex,cboufon,cboutex); 
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
		this.panTable.add(new JScrollPane(DAOTableFactory.getTable(
				Mysql_Connect.getInstance(), BddTables.PAYS)));
		
	}

}
