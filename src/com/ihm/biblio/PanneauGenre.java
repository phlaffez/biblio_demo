package com.ihm.biblio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phl.outils.panneaux.outilsStandards.PanneauOutilsStandard;
import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.GenreDAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.ihm.biblio.PanneauPays.BoutonAjoutListener;
import com.metier.biblio.Genre;
import com.metier.biblio.Pays;


public class PanneauGenre  extends PanneauOutilsStandard 
{

	private Genre genre = new Genre();
	private DaoFactoryMySQL factory = new DaoFactoryMySQL();
	private JScrollPane jsp;
	
	public PanneauGenre(String titrePan,Color colFond,
            
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
		
		table = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.GENRES);
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
		GenreDAO dao = factory.getGenreDAO();
		genre = (Genre)dao.getByNom(nom);
		if(genre==null)
		{
			genre = new Genre(0,nom);
			boolean ok = dao.create(genre);
			panTable.remove(jsp);
			initTable();

		}
		else
		{
			System.out.println("Genre existant dans la base de données. Faire un popup ici");
		}
		}
		
		

	}
		
	


	
	}
	
	
	
	
	

}
