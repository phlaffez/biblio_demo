package com.ihm.biblio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.Cote1DAO;
import com.DAO.biblio.Cote2DAO;
import com.DAO.biblio.Cote3DAO;
import com.DAO.biblio.Cote4DAO;
import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.LivreDAO;
import com.DAO.biblio.OptionRecherche;
import com.metier.biblio.Auteur;
import com.metier.biblio.Cote1;
import com.metier.biblio.Cote2;
import com.metier.biblio.Cote3;
import com.metier.biblio.Cote4;
import com.metier.biblio.Livre;

import phl.outils.panneaux.outilsStandards.JButtonOutils;

public class GenereCote extends JFrame{
	
	// divers
	private JPanel panneau = new JPanel();
	private int hauteur;
	private int largeur;
	private Color colfond;
	private Color colTexte;
	private Color colEtiq;
	private BddTables aCoter;   // soit LIVRES soit REVUES (pour plus tard
	                            // et peut être aussi les articles
	
	// objets à coter:
	
	private Livre livre = null;
	
	// nom fenetre:
	private String nomFenetre;

	// titre:
	private JPanel panneauTitre = new JPanel();
	private String titre;
	private JLabel labelTitre= new JLabel();
	private JLabel labelTitre2= new JLabel();
	private JLabel labelAuteur= new JLabel();
	private JPanel jpanTitre = new JPanel();
	private String titreFen="Creation de la cote";
	private String titreLivre;
	private String auteur;
	
	
	// panneau de selection
	
	private GridBagLayout grille;
	private GridBagConstraints grilleCont;
	private JPanel panneauCote = new JPanel();
	
	// Listes de selection et de compteurs
	
	private JPanel paneauCotes = new JPanel();
	private JComboBox  listeCote1 = new JComboBox();
	private JComboBox  listeCote2 = new JComboBox();
	private JComboBox  listeCote3 = new JComboBox();
	private JComboBox  listeCote4 = new JComboBox();
	private JTextArea listeCompteur = new JTextArea();     // On mettra ici tous les numeros déjà utilisés avec cette cote	
	private JScrollPane scroll;
	private JTextField champCompteurSais= new JTextField();
	private JTextField coteGeneree= new JTextField();
	
	
	
	// Boutons
	
		private JPanel panneauBoutons;
		private JButtonOutils bOK;
		private JButtonOutils bAnnule;
		private JButtonOutils  AddCote1;
		 private JButtonOutils  AddCote2;
		private JButtonOutils  AddCote3;
		private JButtonOutils  AddCote4;
		
	public GenereCote(String tl,String au, int l,int h, Color cf, Color ct, Color fet)
	{
		// Il s'agit d'un constructeur pour les tests.
		this.aCoter = BddTables.AUCUNE;
		this.titreLivre=tl;
		this.largeur = l;
		this.hauteur=h;
		this.colfond=cf;
		this.colTexte=ct;
		this.colEtiq = fet;
		this.auteur = au;
		
	
		
		this.setSize(this.largeur, this.hauteur);
		this.setBackground(this.colfond);
		this.setForeground(colTexte);
		this.setTitle(this.titreFen);
		
		init1();
		init2();
		initCompteur();
		init3();
		init4();
		init5();
	}
	
	
	public GenereCote(Livre livre, int l,int h, Color cf, Color ct, Color fet)
	{

		ArrayList<Auteur> listau;
		// Il s'agit d'un constructeur pour attribuer une cote à un livre
		this.aCoter=BddTables.LIVRES;
		this.livre=livre;
		this.titreLivre=livre.getNomLivre();
		this.largeur = l;
		this.hauteur=h;
		this.colfond=cf;
		this.colTexte=ct;
		this.colEtiq = fet;
		if(livre.getAuteurs()!=null)
		{
		 listau = (ArrayList<Auteur>) livre.getAuteurs();
		}
		else
		{
			listau = new ArrayList<Auteur> ();
		}
		
		if (listau.size()==1)
				{
		this.auteur = listau.get(0).getNom()+" "+listau.get(0).getPrenom();
				}
		if (listau.size()==0)
		{
this.auteur = "Inconnu";
if (listau.size()>=1)
{
this.auteur = listau.get(0).getNom()+" "+listau.get(1).getNom()+" ...";
}
		}
	
		
		this.setSize(this.largeur, this.hauteur);
		this.setBackground(this.colfond);
		this.setForeground(colTexte);
		this.setTitle(this.titreFen);
		
		init1();
		init2();
		initCompteur();
		init3();
		init4();
		init5();
	}
	
//	public GenereCote(Periodique  perio, int l,int h, Color cf, Color ct, Color fet)	
	// sera à faire quand j'ajouterai les périodiques dans la base de données
	// this.aCoter = BddTables.REVUES;
	

	private void init1()
	{
		// Mise en place du panneau de tête
		this.setSize(this.largeur, this.hauteur);
		
		// Panneau de titre:
		this.labelTitre2.setText("Sélectionnez successivement les éléments constituants la cote");
		this.labelTitre2.setForeground(this.colTexte);
		this.labelTitre2.setBackground(this.colEtiq);
		this.panneauTitre.setBackground(this.colEtiq);
		this.panneauTitre.add(this.labelTitre2);
		
	}
	
	
	
	private void init2()
	{
		// Mise en place de la grille de saisie
		int largelem = this.largeur/6;
		int hautelem = 25;
		// panneau pour le choix des cotes
		this.grille = new GridBagLayout();
		this.grilleCont = new GridBagConstraints();
		
		this.paneauCotes.setLayout(this.grille);
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=2;
		this.grilleCont.weightx=1;
		this.labelTitre.setText("Saisie de la cote du livre: "+this.titreLivre);
		this.paneauCotes.add(this.labelTitre, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=1;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=2;
		this.grilleCont.weightx=1;
		this.labelAuteur.setText("Auteur(s): "+this.auteur);
		this.paneauCotes.add(this.labelAuteur, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=2;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.listeCote1.setPreferredSize(new Dimension(largelem, hautelem));		
		this.paneauCotes.add(this.listeCote1, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=2;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.listeCote2.setPreferredSize(new Dimension(largelem, hautelem));
		this.paneauCotes.add(this.listeCote2, this.grilleCont);
		
		this.grilleCont.gridx=2;
		this.grilleCont.gridy=2;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.listeCote3.setPreferredSize(new Dimension(largelem, hautelem));
		this.paneauCotes.add(this.listeCote3, this.grilleCont);
		
		this.grilleCont.gridx=3;
		this.grilleCont.gridy=2;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.listeCote4.setPreferredSize(new Dimension(largelem, hautelem));
		this.paneauCotes.add(this.listeCote4, this.grilleCont);
		
		this.grilleCont.gridx=4;
		this.grilleCont.gridy=2;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.champCompteurSais.setPreferredSize(new Dimension(largelem, hautelem));
		this.paneauCotes.add(this.champCompteurSais, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.AddCote1= new JButtonOutils("Nouveau",largelem,hautelem,Color.orange);
		this.AddCote1.setName("Add1");
		this.AddCote1.addActionListener(new bAddCotesListener());
		this.paneauCotes.add(this.AddCote1, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.AddCote2= new JButtonOutils("Nouveau",largelem,hautelem,Color.orange);
		this.AddCote2.setName("Add2");
		this.AddCote2.addActionListener(new bAddCotesListener());
		this.paneauCotes.add(this.AddCote2, this.grilleCont);
		
		this.grilleCont.gridx=2;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.AddCote3= new JButtonOutils("Nouveau",largelem,hautelem,Color.orange);
		this.AddCote3.setName("Add3");
		this.AddCote3.addActionListener(new bAddCotesListener());
		this.paneauCotes.add(this.AddCote3, this.grilleCont);
		
		this.grilleCont.gridx=3;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.AddCote4= new JButtonOutils("Nouveau",largelem,hautelem,Color.orange);
		this.paneauCotes.add(this.AddCote4, this.grilleCont);
		this.AddCote4.setName("Add4");
		this.AddCote4.addActionListener(new bAddCotesListener());
		
		this.grilleCont.gridx=4;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=10;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.listeCompteur = new JTextArea();
		this.scroll = new JScrollPane(this.listeCompteur);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.scroll.setPreferredSize(new Dimension(120,150));
		this.paneauCotes.add(scroll, this.grilleCont);
		
		
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=4;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=2;
		this.grilleCont.weightx=1;
		this.coteGeneree.setPreferredSize(new Dimension(largelem*2, hautelem));
		this.paneauCotes.add(this.coteGeneree, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		initCote1();
		this.listeCote1.addItemListener(new listeCote1Listener());
		this.listeCote2.addItemListener(new listeCote2Listener());
		this.listeCote3.addItemListener(new listeCote3Listener());
		this.listeCote4.addItemListener(new listeCote4Listener());
		this.champCompteurSais.getDocument().addDocumentListener(new champCompteurListener() );
	
		
	}
	
	private void init3()
	{
		// mise en place du panneau latéral où on voit la liste du compteur
		
	}
	
	private void init4()
	{
		// mise en place du panneau de boutons
		
		this.bOK = new JButtonOutils("Valider",100,50,Color.orange);
		this.bAnnule = new JButtonOutils("Annuler",100,50,Color.orange);
		this.panneauBoutons = new JPanel();
		this.bOK.addActionListener(new bOKListener());
		this.bAnnule.addActionListener(new bAnnuleListener());
		this.panneauBoutons.add(this.bAnnule);
		this.panneauBoutons.add(this.bOK);
	}
	
	private void init5()
	{
		// Mise en place finale
		this.setLocationRelativeTo(null);
	
		this.add(this.panneau);
		this.panneau.setLayout(new BorderLayout());
		this.panneau.add(this.panneauTitre,BorderLayout.NORTH);
		this.panneau.add(this.paneauCotes,BorderLayout.CENTER);
		this.panneau.add(this.panneauBoutons, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private void initCote1()
	{
		// initialisation du Comboxbox listeCote1
		
		Cote1DAO c1dao = DaoFactoryMySQL.getCote1DAO();
		Cote1 c1;
		ArrayList<Cote1> c1s = (ArrayList<Cote1>)c1dao.selectAll();
		
		
		// On commence par le vider pour le recharger si on ajoute quelque chose
		// avec le bouton correspondant
		
		listeCote1.removeAllItems();
		this.listeCote1.setEditable(false);
		for (int i = 0;i<c1s.size();i++)
		{
			listeCote1.addItem(c1s.get(i));
		}
		c1 = (Cote1)listeCote1.getItemAt(0);
	generationCote();
		initCote2(c1.getIdCote1());
	}
	
	private void initCote2(int c1)
	{
		// initialisation du Comboxbox listeCote2 en fonction d'une Cote1 déjà choisie c1
		
		Cote2DAO c2dao = DaoFactoryMySQL.getCote2DAO();
		Cote2 c2;
		ArrayList<Cote2> c2s = (ArrayList<Cote2>)c2dao.getByCote1(c1);
		String s;
		
		
		// On commence par le vider pour le recharger si on ajoute quelque chose
		// avec le bouton correspondant
		listeCote2.removeAllItems();
		this.listeCote2.setEditable(false);
		if(!c2s.isEmpty()) 
		{
			
		for (int i = 0;i<c2s.size();i++)
		{
			listeCote2.addItem(c2s.get(i));
		}
		c2 = (Cote2)listeCote2.getItemAt(0);
		s = this.coteGeneree.getText()+"/"+c2.getCode();
		generationCote();
		initCote3(c2.getIdCote2());
		}
		else
		{
			initCote3(-1);
		}
	}
	
	
	private void initCote3(int c2)
	{
		// initialisation du Comboxbox listeCote3 en fonction d'une Cote2 déjà choisie c2
		
		Cote3DAO c3dao = DaoFactoryMySQL.getCote3DAO();
		Cote3 c3;
		
		ArrayList<Cote3> c3s = (ArrayList<Cote3>)c3dao.getByCote2(c2);
		String s;
		
		
		// On commence par le vider pour le recharger si on ajoute quelque chose
		// avec le bouton correspondant
		listeCote3.removeAllItems();
		this.listeCote3.setEditable(false);
		if(c2!=-1)
		{
		if(!c3s.isEmpty())
		{
		for (int i = 0;i<c3s.size();i++)
		{
			listeCote3.addItem(c3s.get(i));
		}
		c3 = (Cote3)listeCote3.getItemAt(0);
		generationCote();
		
		initCote4(c3.getIdCote3());
		}
		else
			initCote4(-1);
		}
		else
		{
			initCote4(-1);
		}
	}
	
	private void initCote4(int c3)
	{
		// initialisation du Comboxbox listeCote4 en fonction d'une Cote3 déjà choisie c3
		
		Cote4DAO c4dao = DaoFactoryMySQL.getCote4DAO();
		Cote4 c4;
		ArrayList<Cote4> c4s = (ArrayList<Cote4>)c4dao.getByCote3(c3);
		String s;
		
		
		// On commence par le vider pour le recharger si on ajoute quelque chose
		// avec le bouton correspondant
		listeCote4.removeAllItems();
		this.listeCote4.setEditable(false);
		if(c3!=-1)
		{
		if(!c4s.isEmpty())
		{
		for (int i = 0;i<c4s.size();i++)
		{
			listeCote4.addItem(c4s.get(i));
		}
		c4 = (Cote4)listeCote4.getItemAt(0);
		generationCote();
		}
		}
		
	}	
	
	private void generationCote()
	{
		Cote1 c1;
		Cote2 c2;
		Cote3 c3;
		Cote4 c4;
		String s;
		// genere la cote etla met dans le champ approprié 
		c1 = (Cote1)listeCote1.getItemAt(listeCote1.getSelectedIndex());
		s = c1.getCode();
		if(this.listeCote2.getComponentCount()!=0)
		{
			c2 = (Cote2)listeCote2.getItemAt(listeCote2.getSelectedIndex());
			if(c2!=null)
			{
			s=s+"/"+c2.getCode();
			if(this.listeCote3.getComponentCount()!=0)
			{
				c3 = (Cote3)listeCote3.getItemAt(listeCote3.getSelectedIndex());
				if(c3!=null) {
					
				s=s+"/"+c3.getCode();
				if(this.listeCote4.getComponentCount()!=0)
				{
					c4 = (Cote4)listeCote4.getItemAt(listeCote4.getSelectedIndex());
					if(c4!=null)
					{
					s=s+"/"+c4.getCode()+"/"+this.champCompteurSais.getText();
							
				}
				}
			}
			}
			}
		}
		this.coteGeneree.setText(s);
		
		
	}
	
	
	private void initCompteur()
	{
		int j=0;
		// vide le champs listeCompteur et la liste de float correspondant
		// recherche dans la base de données les valeurs correspondantes
		LivreDAO ldao = DaoFactoryMySQL.getLivreDAO();
		String cav = coteGeneree.getText();
	//	System.out.println(cav);
		ArrayList<Livre> livres = (ArrayList<Livre>)ldao.getByCoteLike(cav, OptionRecherche.COMMENCE);
		listeCompteur.setText("");
		if(livres.size()>0)
		{
			for (int i =0; i<livres.size();i++)
			{
				j=livres.get(i).getCote().lastIndexOf('/');
		//		System.out.println(j);
				listeCompteur.append(livres.get(i).getCote().substring(j+1));
				listeCompteur.append("\n");
			}
		}
		else
		{
			
		}
		
		
		// à cote1/cote2/cote3/cote4 correspondant, et les copie
		// dans le camps et dans la liste
		// met à jour les variables compt1 et compt2 et mret compt2 comme proposition
	}
	
	private boolean verifieCompteur()
	
	{
		// vérifie que ce que l'utilisateur a choisi est valide (un float pas encore utilisé)
		return false;
	}
	
	
	
	
	
	class listeCote1Listener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			
		Cote1	c1 = (Cote1)listeCote1.getItemAt(listeCote1.getSelectedIndex());
		generationCote();
		champCompteurSais.setText("");
		initCote2(c1.getIdCote1());
			
			
		}
		
	}
	
	class listeCote2Listener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			
		Cote2	c2 = (Cote2)listeCote2.getItemAt(listeCote2.getSelectedIndex());
		generationCote();
		champCompteurSais.setText("");
		if(c2!=null)
		{
		initCote3(c2.getIdCote2());
		}
		else
		{
			initCote3(-1);
		}
			
			
		}
	}
		
		
		class listeCote3Listener implements ItemListener
		{

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
			Cote3	c3 = (Cote3)listeCote3.getItemAt(listeCote3.getSelectedIndex());
			generationCote();
			champCompteurSais.setText("");
			if(c3!=null)
			{
			initCote4(c3.getIdCote3());
			}
			else
			{
				initCote4(-1);
			}
				
				
			}
			
		}
		
		class listeCote4Listener implements ItemListener
		{
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
		
			generationCote();
			initCompteur();
			champCompteurSais.setText("");
				
				
			}	
		}
		
		
		class champCompteurListener implements DocumentListener
		{

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				generationCote();
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				generationCote();
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				generationCote();
				
			}
			
		}


		class bAnnuleListener implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		//		System.out.println("Bouton annuler pressé");
				dispose();
				
			}
			
		}
		
		
		class bAddCotesListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddCote addcote;
				String str =((JButtonOutils)arg0.getSource()).getName();
				switch (str){
				case "Add1":
					addcote = new AddCote(BddTables.COTE1,0, colfond, colTexte,colfond, colTexte, Color.RED, colTexte,600,400); 
					break;
				case "Add2":
					Cote1 c1  = (Cote1) listeCote1.getSelectedItem();
					addcote = new AddCote(BddTables.COTE2,c1.getIdCote1(), colfond, colTexte,colfond, colTexte, Color.RED, colTexte,600,400);
					break;
			    case "Add3":
			    	Cote2 c2  = (Cote2) listeCote2.getSelectedItem();
					addcote = new AddCote(BddTables.COTE3,c2.getIdCote2(), colfond, colTexte,colfond, colTexte, Color.RED, colTexte,600,400);
					break;
			    case "Add4":
			    	Cote3 c3  = (Cote3) listeCote3.getSelectedItem();
					addcote = new AddCote(BddTables.COTE4,c3.getIdCote3(), colfond, colTexte,colfond, colTexte, Color.RED, colTexte,600,400);
					break;
					
				}
			
				
			}
			
		}
		
		
		
		
		class bOKListener implements ActionListener 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LivreDAO livredao = DaoFactoryMySQL.getLivreDAO();
				JFrame frame=new JFrame() ;
				// Il faut vérifier qu'on a quelque chose dans le champ numéro et que c'est un nombre
				
				String s= champCompteurSais.getText();
				if(s.length()==0)
				{
					// ici ce n'est pas bon -- > message
				}
				else
					
				{
					try
					{
						float f = Float.parseFloat(s);
						// on vérifie si la cote n'existe pas déjà dans la base de données
						String cote = coteGeneree.getText();
						ArrayList<Livre> liv= (ArrayList<Livre>) livredao.getByCoteLike(cote, OptionRecherche.EST);
						if(liv.isEmpty())
						{
		                  // La cote est libre
						  livre.setCote(cote);     // on met l'objet à jour
				//		  livredao.update(livre);  // on met à jour l'enregistrement dans la table livres.
			   // on met à jour le compteur de cote 4 si nécessaire:
						  
						  // recuperer l'objet cote4
						  Cote4 cote4 = (Cote4) listeCote4.getSelectedItem();
						  // verifier si le nouveau numéro est au dessus
						  // si oui mettre à jour l'objet cote4 puis la table
						  if(f>cote4.getCompteur())
						  {
							  cote4.setCompteur(f);
							  Cote4DAO c4dao = DaoFactoryMySQL.getCote4DAO();
						//	  c4dao.update(cote4);
						  }
						 
						  
		
						  // afficher un message de succès
						  JOptionPane.showMessageDialog(frame, "Cote insérée.");
						  dispose();
						 }
					}
					catch (NumberFormatException nfe) 
					{
						JOptionPane.showMessageDialog(frame, "Le dernier champ de la cote doit être un nombre entier","Erreur",JOptionPane.ERROR_MESSAGE);
						// message d'erreur car champ non numérique
					}
										
					
		
					
					
					
					
					// si elle existe, on informe
					// si elle est libre, on insère la cote dans la base de données et on germe la fenêtre
				}
				
				
				
			}
			
		}
		
		
		
		
		
}
