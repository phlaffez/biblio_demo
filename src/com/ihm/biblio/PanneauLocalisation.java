package com.ihm.biblio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.GenreDAO;
import com.DAO.biblio.LocalisationDAO;
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
	
	class BoutonAjoutListener implements ActionListener
	{  
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nom = champSaisie.getText().toUpperCase();
		if(!nom.isEmpty())
		{
		LocalisationDAO dao = factory.getLocalisationDAO();
		lieu = (Localisation)dao.getByNom(nom);
		if(lieu==null)
		{
			lieu = new Localisation(0,nom);
			boolean ok = dao.create(lieu);
			panTable.remove(jsp);
			initTable();

		}
		else
		{
			System.out.println("Localisation existante dans la base de données. Faire un popup ici");
		}
		}	
	}
	}
	}
