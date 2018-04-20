package com.ihm.biblio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Pays;

import phl.outils.panneaux.outilsStandards.PanneauOutilsStandard;

public class PanneauPays extends PanneauOutilsStandard{
	
	private Pays pays = new Pays();
	

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
		this.boutonRetour.setVisible(false);
		
	}

	@Override
	protected void initMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initTable() {
				table = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.PAYS);

		this.panTable.add(new JScrollPane(table));
		
		
		class boutonAjoutListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
			
				
			}
		}
		
		
	}

}
