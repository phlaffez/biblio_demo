package com.ihm.biblio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAOTableFactory;
import com.DAO.biblio.DaoFactoryMySQL;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;

import phl.outils.panneaux.outilsStandards.JButtonOutils;
import phl.outils.tables.ModelTablePhl;

import java.util.ArrayList;

public class ChoixAuteurs extends JFrame{
	
	// classe permettantle choix des auteurs d'un ouvrage
	
	private String titre;
private ArrayList <Auteur> la = new ArrayList<Auteur>();
private JPanel pan = new JPanel();
private Color cf;
private Color cf2;
private Color cb;
	
// utilisé pour l'affichage du titre de la fenêtre et de l'ouvrage:
	
	private JLabel etiqTitre = new JLabel("Choisissez le ou les auteurs de: ");
	private JLabel nomOuvrage = new JLabel();
	private JPanel panHaut = new JPanel();
	
	// Zone centrale
	
	private JPanel panCentre = new JPanel();
	private JPanel panCentreW = new JPanel();
	private JPanel panCentreE = new JPanel();
	
	// bas
	
	private JPanel panBas = new JPanel();
	private JButtonOutils boutonRAZ;
	private JButtonOutils boutonAnnuler;
	private JButtonOutils boutonValider;
	
	// tables, et accès à la base de données
	private Auteur auteur = new Auteur();         // recupère les infos dans le table de choix
	                                             // pour copie dans la table des choisis
	private DaoFactoryMySQL factory = new DaoFactoryMySQL();
	private JScrollPane jsp1;
	private ModelTablePhl model;
	private JTable table1;   // affichage de la table des auteurs
	
	private JScrollPane jsp2;
	private JTable table2;   // affichage des auteurs choisis
	
	// champ et bouton de recherche
	
	private JLabel labelRecherche = new JLabel("Preselection: ");
	private JTextField champRecherche = new JTextField();
	private JButtonOutils boutonRecherche;
	private JPanel panneauRecherche= new JPanel();
	private JRadioButton boutonContient= new JRadioButton();
	private JRadioButton boutonCommence = new JRadioButton();
	private ButtonGroup groupeOption = new ButtonGroup();
	private JLabel labelOption = new JLabel("Commence / Contient");
	
	
	
	

	public ChoixAuteurs(String titre, Color cf, Color cf2, Color cb) 
	
	{
		this.titre = titre;
		this.cf=cf;
		this.cf2 = cf2;
		this.cb = cb;
		init1();   //panneau haut
		init2();  // panneau central
		 placementBoutons();
		init3(); // panneau bas
	}
	
	public ChoixAuteurs(String titre, Color cf, Color cf2, Color cb, ArrayList <Auteur> la)
	{
		this.titre = titre;
		this.la = la;
		this.cf=cf;
		this.cf2 = cf2;
		this.cb = cb;
		init1();     
		init2();
		placementBoutons();
		init3();
	}
	
	private  void init1()  // initialise ce qui est commun et trivial dans cette interface
	{
	    this.setTitle("Selection du ou des auteurs");
		 this.setSize(1000,800);
		 this.setLocationRelativeTo(null);
		 
		 // Panneau haut
		 this.panHaut.setBackground(this.cf2);
		 this.panHaut.add(this.etiqTitre);
		 this.nomOuvrage.setText(this.titre);
		 this.panHaut.add(this.nomOuvrage);
		 
		 // panneau de recherche
		 
		 this.panneauRecherche.add(this.labelRecherche);
		 this.champRecherche.setPreferredSize(new Dimension(180,20));
		 this.panneauRecherche.add(this.champRecherche);
		 this.boutonCommence.setSelected(true);
		 this.groupeOption.add(this.boutonCommence);
		 this.groupeOption.add(this.boutonContient);
		 this.panneauRecherche.add(this.boutonCommence);
		 this.panneauRecherche.add(this.labelOption);
		 this.panneauRecherche.add(this.boutonContient);
		 this.boutonRecherche = new  JButtonOutils("Chercher",100,20,cb);
		 this.panneauRecherche.add(this.boutonRecherche);
		 
		 // Panneau central
		 
		 this.panCentre.setBackground(this.cf);
		 // table des auteurs de la base de données:
		 
		 if(table1!=null)
			 this.remove(table1);
			table1 = DAOTableFactory.getTable(Mysql_Connect.getInstance(), BddTables.AUTEURS);
			model = (ModelTablePhl)table1.getModel();
			jsp1= new JScrollPane(table1);
			this.panCentreW.setLayout(new BorderLayout());
			this.panCentreW.add(this.panneauRecherche,BorderLayout.NORTH);
			this.panCentreW.add(this.jsp1,BorderLayout.CENTER);
			
			if(table2!=null)
				this.remove(table2);
			table2 = new JTable();
			// model
			jsp2=new JScrollPane(table2);
			this.panCentreE.add(this.jsp2);
			
			
			this.panCentre.setLayout(new BorderLayout());
			this.panCentre.add(this.panCentreW, BorderLayout.WEST);
			this.panCentre.add(this.panCentreE,BorderLayout.EAST);
					 
				 
		 
	}
	
	
	private void placementBoutons()
	{
		this.panBas.setBackground(this.cf2);
		this.boutonRAZ = new JButtonOutils("RAZ",180,50,cb);
		this.boutonAnnuler = new  JButtonOutils("Annuler",180,50,cb);
		this.boutonValider = new JButtonOutils("Valider",180,50,cb);
		
		this.panBas.add(this.boutonRAZ);
		this.panBas.add(this.boutonAnnuler);
		this.panBas.add(this.boutonValider);		
	
	}
	
	// initialisation panneau central
	
	private void init2()
	{
		// Il faut initialiser la table d'affichage des auteurs à choisir
		// la table des auteurs choisis
	}
	
	
	private void init3()
	{
		 this.pan.setBackground(cf);
		 this.pan.setLayout(new BorderLayout());
		 this.pan.add(this.panHaut, BorderLayout.NORTH);
		 this.pan.add(this.panCentre, BorderLayout.CENTER);
			this.pan.add(this.panBas, BorderLayout.SOUTH);
		this.setContentPane(this.pan);   
		this.setVisible(true);
			}
	
	
	
	
	// Adapters
	
	// Double click pour ajouter un auteur dans la liste des auteurs du titre
	
	class AjouteAuteur extends MouseAdapter
	{
		
	}
	
	
	
	// Double click pour retirer un auteur de la liste des auteurs du titre
	
	class RetireAuteur extends MouseAdapter
	{
		
	}
	
	
	
	// Pour faire une recherche dans la table auteur (bouton spécialisé)
	
	class ChercherListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			//  récupération de ce qu'il faut chercher
			
			// Recherche dans la table de la BDD
			// affichage de la selection dans la Jtable de gauche
		}
		
		
		
	}
	

	
	class ValiderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		
		}
	
	}
	
	class RazListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		
		}
		
		class AnnulerListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			
			}
		
		}
	
	}
	

}
