package com.ihm.biblio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Pays;

import phl.outils.panneaux.outilsStandards.PanneauOutilsStandard;

public class PanneauPays extends PanneauOutilsStandard{
	
	private Pays pays = new Pays();
	private DaoFactoryMySQL factory = new DaoFactoryMySQL();
	private JScrollPane jsp;

	

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
		this.boutonAjout.addActionListener(new BoutonAjoutListener());
		
		
	}

	@Override
	protected void initMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initTable() {
				table = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.PAYS);
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
		String nomPays = champSaisie.getText().toUpperCase();
		if(!nomPays.isEmpty())
		{
		PaysDAO paysdao = factory.getPaysDAO();
		pays = (Pays)paysdao.getByNom(nomPays);
		if(pays==null)
		{
			pays = new Pays(0,nomPays);
			boolean ok = paysdao.create(pays);
			panTable.remove(jsp);
			initTable();

		}
		else
		{
			System.out.println("Pays existant dans la base de données. Faire un popup ici");
		}
		}

	}
		
	}
	
}
