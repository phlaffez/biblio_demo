package com.ihm.biblio;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;

import phl.outils.panneaux.outilsStandards.PanneauTableStandard;
import phl.outils.tables.ModelTablePhl;

public class PanneauLivres  extends PanneauTableStandard{
	
	private Livre livre = new Livre();
	private DaoFactoryMySQL factory = new DaoFactoryMySQL();
	private JScrollPane jsp;
	private ModelTablePhl model;

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
		if(table!=null)
		    this.remove(table);
		table = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.LIVRES);
		 model = (ModelTablePhl)table.getModel();
		jsp= new JScrollPane(table);
		// Listener
				table.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						 if (e.getClickCount() == 2) {
						 Point p = e.getPoint();
		                 int y = table.rowAtPoint(p);
		                 int idLivre = Integer.parseInt(table.getValueAt(y, 0).toString());
		                 Livre livre = factory.getLivreDAO().findId(idLivre);
		                
		                 DetaiLivre detailLivre = new DetaiLivre(
		                		 livre,Ordre.MODIFICATION,Color.lightGray,Color.BLACK,model,y);
		                 
						 }
		
		
		
		 table.setBackground(this.coulPanTab);
this.panTable.add(jsp);	
this.setVisible(false);
this.setVisible(true);
	}

}
