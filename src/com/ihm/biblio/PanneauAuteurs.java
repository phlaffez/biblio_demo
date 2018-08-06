package com.ihm.biblio;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.Genre;

import phl.outils.panneaux.outilsStandards.PanneauTableStandard;
import phl.outils.tables.ModelTablePhl;

public class PanneauAuteurs extends PanneauTableStandard{
	
	private Auteur auteur = new Auteur();
	private DaoFactoryMySQL factory = new DaoFactoryMySQL();
	private JScrollPane jsp;
	private ModelTablePhl model;


	
	
	public PanneauAuteurs(String titrePan, Color colFond, Color coulTexPP, Color coulPanTab, Color coulPanText,
			Color colEnt, Color colEntTex, Color cboufon, Color cboutex) {
		super(titrePan, colFond, coulTexPP, coulPanTab, coulPanText, colEnt, colEntTex, cboufon, cboutex);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initBoutons() {
		this.boutonAjout.addActionListener(new CreerListener());
		
	}

	@Override
	protected void initMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initTable() {
		if(table!=null)
		    this.remove(table);
		table = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.AUTEURS);
		 model = (ModelTablePhl)table.getModel();
		 jsp= new JScrollPane(table);
		
		// Listener
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				 if (e.getClickCount() == 2) {
				 Point p = e.getPoint();
                 int y = table.rowAtPoint(p);
                 int idAuteur = Integer.parseInt(table.getValueAt(y, 0).toString());
                 Auteur auteur = factory.getAuteurDAO().findId(idAuteur);
                
                 DetailAuteur detailAuteur = new DetailAuteur(
                		 auteur,Ordre.MODIFICATION,Color.lightGray,Color.BLACK,model,y);
                 
				 }
			}
		});
		
		 table.setBackground(this.coulPanTab);
		 this.panTable.remove(jsp);
this.panTable.add(jsp);	
this.setVisible(false);
this.setVisible(true);
		
	}
	
	class CreerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// ceci doit être mis dans un thread ?
			Auteur aut = new Auteur();
			model = (ModelTablePhl)table.getModel();
			DetailAuteur da = new DetailAuteur(aut,Ordre.CREATION,Color.CYAN, Color.black,model,0);
		
		}
		
	}
	

	}

