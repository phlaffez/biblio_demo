package com.ihm.biblio;

import java.awt.Color;

import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.dbacces.biblio.Mysql_Connect;
import com.ihm.biblio.PanneauGenre.BoutonAjoutListener;
import com.metier.biblio.Genre;
import com.metier.biblio.Localisation;

import phl.outils.panneaux.outilsStandards.PanneauOutilsStandard;

public class PanneauLocalisation extends PanneauOutilsStandard
{

	private Localisation lieu = new Localisation();
	private DaoFactoryMySQL factory = new DaoFactoryMySQL();
	private JScrollPane jsp;
	
	public PanneauLocalisation(String titrePan,Color colFond,
            
           Color coulTexPP,
           Color panTab,
           Color coulPanText,
           Color colEnt,
            Color colEntTex,
            Color cboufon,
            Color cboutex)  {
		super(titrePan,colFond,coulTexPP,panTab,coulPanText,colEnt,
        colEntTex,cboufon,cboutex); 
	}

	@Override
	protected void initBoutons() {
		this.boutonRetour.setVisible(false);
		this.boutonAjout.addActionListener(new BoutonAjoutListener());
		
	}

	@Override
	protected void initMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initTable() {
		table = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.LOCALISATION);
		jsp= new JScrollPane(table);
		 table.setBackground(this.coulPanTab);
this.panTable.add(jsp);	
this.setVisible(false);
this.setVisible(true);
		
	}

}
