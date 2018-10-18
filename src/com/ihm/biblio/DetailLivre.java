package com.ihm.biblio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.GenreDAO;
import com.DAO.biblio.LangueDAO;
import com.DAO.biblio.LivreAuteurDAO;
import com.DAO.biblio.LivreDAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.ihm.biblio.DetailAuteur.CreerListener;
import com.ihm.biblio.DetailAuteur.ModifierListener;
import com.ihm.biblio.DetailAuteur.RazListener;
import com.ihm.biblio.IhmDetailFiche.quitterListener;
import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;
import com.metier.biblio.Pays;

import phl.outils.panneaux.outilsStandards.JButtonOutils;
import phl.outils.tables.ModelTablePhl;

public class DetailLivre  extends IhmDetailFiche<Livre,LivreDAO>{
	
	/* les boutons et le panneau d'affichage sont standards et sont donc des
	 * variables protected de la classe mère.
	 * Les variables de cette classe sont les champs d'affichage des variables
	 * de l'obet Livre. Sauf pourle champ Auteurs car il peut y avoir plusieurs
	 * auteurs, et il faut donc le prévoir à la création de la fiche livre
	 */
	
private Livre obj;
	
	// Les 8 Labels
	private JLabel idLabel= new JLabel("Id:");
	private JLabel titreLabel = new JLabel("Titre:");
	private JLabel genreLabel = new JLabel("Genre");
	private JLabel langueLabel = new JLabel("Langue:");
	private JLabel classementLabel = new JLabel("Lieu de rangement:");
	private JLabel  resLabel = new JLabel("Resumé");
	private JLabel auteursLabel = new JLabel("Auteurs");
	private JLabel datePubLabel = new JLabel("Date de publication");
	private JLabel dateAcqLabel = new JLabel("Date d'acquisition");
	private Dimension dimLabel;
	private Dimension dimChamp;
	private JScrollPane jsc;
	
	// les champs de saisie / consultation - initialisés dans le constructeur
	
		Font f = new Font("Courier", Font.BOLD, 13);
		private JTextField idChamp = new JTextField();
		private JTextField titreChamp = new JTextField();
		private JTextField genreChamp = new JTextField();
		private JComboBox listeGenres = new JComboBox();     // cas de la création ou de la modification
		private JTextField langueChamp= new JTextField();
		private JComboBox listeLangues = new JComboBox();     // cas de la création ou de la modification
		private JTextField rangementChamp= new JTextField();  // Il faudra ajouter une table pour les localisations
		private JTextField acqChamp= new JTextField();
		private JTextField publiChamp= new JTextField();
		private JTextArea resumeChamp= new JTextArea();
		private JTextArea auteursChamp= new JTextArea();

		
		private Ordre ordre;
	
		private ArrayList<Auteur> lesAuteurs; 
		private int idGenre;
		private int idLangue;
		private GenreDAO genredao = new GenreDAO(Mysql_Connect.getInstance());
		private LangueDAO languedao = new LangueDAO(Mysql_Connect.getInstance());
		private LivreAuteurDAO livreAuteurdao = new LivreAuteurDAO(Mysql_Connect.getInstance());

		
	// messages:enre
		private String titre="Bibliothèque: Livres";
		private String titre2="Création / Modification d'une fiche livre";


		
		
		public DetailLivre(Color coulFond, Color coulTextPP, ModelTablePhl mtable,
				int numLig) {
			
			this.mtable=mtable;
			this.numLig=numLig;
			init1(coulFond,coulTextPP);
			this.ordre=Ordre.CREATION;
			initCreate();
			initPan();
			init2();
		}

		
		public  DetailLivre(Livre obj, Ordre ordre,Color coulFond, Color coulTextPP, ModelTablePhl mtable,
				int numLig)
		{
		
			this.obj=obj;
			this.mtable=mtable;
			this.numLig=numLig;
			init1(coulFond,coulTextPP);
			this.ordre=ordre;
			
			if(ordre == Ordre.CREATION)
				{
				initCreate();
				initPan();
				init2();
				}
			else
			{
				if(ordre == Ordre.LECTURE)
				{
			
					this.setTitle("Consultation d'une fiche livre");
				}
				else
				{
			this.setTitle("Modification d'une fiche livre");
		
					
				}
				remplit();
				initPan();
		        init2();
			}
			
		}	
		
		
		
		
		
		
		
		private void init2() {
			this.setContentPane(this.pan);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
				
			}
		
		
		@Override
		protected void initCreate() {
			// TODO Auto-generated method stub
			// Affiche une fiche vide avec un objet vide
			this.setTitle("Création d'une fiche livre");
			this.idChamp.setText("0");
			this.titreChamp.setText("");
			
			//*******************************FINIR PLACEMENT DES CHAMPS
			
			
		}
		
		@Override
		protected void initPan() {
			// TODO Auto-generated method stub
			// Mise en place des différents éléments du panneau sur un GridBagLayout
			
			dimChamps();
			this.pan = new JPanel();
			this.pan.setSize(new Dimension(480,480));
			this.pan.setBackground(this.coulFond);
			this.grille = new GridBagLayout();
			this.pan.setLayout(grille);
			this.grilleCont = new GridBagConstraints();
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=0;
			this.grilleCont.gridheight=1;
			this.grilleCont.gridwidth=1;
			this.grilleCont.weightx=2;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.idLabel, this.grilleCont);
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=0;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.idChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=1;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.titreLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=1;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.titreChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=2;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.auteursLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=2;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.auteursChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=3;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.langueLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=3;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.langueChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			
	
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=4;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.genreLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=4;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.genreChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			
	
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=5;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.datePubLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=5;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.publiChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			
				
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=6;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.dateAcqLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=6;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.acqChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			
			
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=7;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.classementLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=7;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.rangementChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
			
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=8;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.resLabel, this.grilleCont);
			
			this.grilleCont.gridx=1;
			this.grilleCont.gridy=8;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.pan.add(this.resumeChamp, this.grilleCont);
			this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;						
					
			
	
			
			
		}
		
		@Override
		protected void initBoutons() {
			// TODO Auto-generated method stub
			// cette méthode doit affecter les listener et placer les boutons dans
			// dans le bas de la grille
			 this.boutonCreer = new JButtonOutils("Créer fiche",180,50,Color.RED);
			 this.boutonModifier = new JButtonOutils("Valider modifications",180,50,Color.RED);
			this.boutonQuitter= new JButtonOutils("Quitter", 180, 50, Color.RED);
			this.boutonRAZ = new JButtonOutils("RAZ",180,50,Color.RED);
			
			this.boutonCreer.addActionListener(new CreerListener());
			this.boutonModifier.addActionListener(new ModifierListener());		
			this.boutonQuitter.addActionListener(new quitterListener());
			this.boutonRAZ.addActionListener(new RazListener());
			
			if (this.ordre==Ordre.CREATION)
			{
				this.boutonModifier.setEnabled(false);
				this.boutonModifier.setVisible(false);
			}
			
			if(this.ordre==Ordre.LECTURE)
			{
				this.boutonModifier.setEnabled(false);
				this.boutonModifier.setVisible(false);
				this.boutonRAZ.setEnabled(false);
				this.boutonRAZ.setVisible(false);
				this.boutonCreer.setEnabled(false);
				this.boutonCreer.setVisible(false);
			}
			
		}
		
	private void init1(Color coulFond, Color coulTextPP) {
			// TODO Auto-generated method stub
		this.coulFond=coulFond;
		this.coulTextPP=coulTextPP;
		this.setBackground(this.coulFond);
//		this.setPreferredSize(new Dimension(800,600));
		this.setSize(new Dimension(800,600));
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}

	@Override
	protected void dimChamps() {
		// TODO Auto-generated method stub
		// dimensionnement des champs, police, etc.
		
	}
	



	@Override
	protected void remplit() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	protected void editable(boolean ok) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected boolean valide() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Object creeObjet(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	
	// Listeners:

	class CreerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class ModifierListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	class  RazListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// remise à zero du formulaire
			if (ordre == Ordre.CREATION)
			{   
				initCreate();
			
		}		
			else
			{
				remplit();
			}
			
		}
	}
	
	
	}




