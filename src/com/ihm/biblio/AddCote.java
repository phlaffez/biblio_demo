package com.ihm.biblio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.DAO.biblio.BddTables;
import com.DAO.biblio.Cote1DAO;
import com.DAO.biblio.Cote2DAO;
import com.DAO.biblio.Cote3DAO;
import com.DAO.biblio.Cote4DAO;
import com.DAO.biblio.DaoFactoryMySQL;
import com.metier.biblio.Cote1;
import com.metier.biblio.Cote2;
import com.metier.biblio.Cote3;
import com.metier.biblio.Cote4;

import phl.outils.panneaux.outilsStandards.JButtonOutils;

public class AddCote extends JFrame{

	// cette classe graphique doit permettre d'ajouter des enregistrements dans les tables
	// de Cotes
	
	private BddTables neoCote;   // la table dans laquelle on ajoute quelque chose
	private BddTables coteRef;   // la table de niveau inférieur
	private int refCo;           // la référence à laquelle rattacher la nouvelle cote, dans la
	                             // table Cote(n-1) pour un ajout en Cote(n), n>=2

	private Color coulFond;          // couleur fond du panneau
	private Color coulTextPP;        // couleur texte principal
	private Color coulEntete;        // couleur du fond de l'en tête de la table
	private Color coulEnteteText;    // couleur du texte de l'en tête de la table
	private Color coulBoutonFond;     // couleur des boutons
	private Color coulBoutonText;    // couleur du texte des boutons
	
	private JPanel panneau = new JPanel();
	private int hauteur;
	private int largeur;
	
	// nom fenetre, titre
	private String nomFenetre;
	private JPanel panneauTitre;
	private JLabel labelTitre=new JLabel();
	
	// Boutons
	
	private JPanel panneauBoutons;
	private JButtonOutils bOK;
	private JButtonOutils bAnnule;
	
	
	// Champs de cotes:
	
	private GridBagLayout grille;
	private GridBagConstraints grilleCont;
	private JPanel panneauCentral = new JPanel();
	private JTextField c1 = new JTextField();
	private JTextField c2 = new JTextField();
	private JTextField c3 = new JTextField();
	private JTextField c4 = new JTextField();
	
	// champs et labels por la saisie (autre que la cote, donc nom et infos)
	private JLabel labelInfo = new JLabel("Infos:");
	private JTextField infoSais = new JTextField();
	private JLabel labelNom = new JLabel("Nom:");
	private JLabel LabelCode = new JLabel("Codes:");
	private JTextField nomSais = new JTextField();
	
	

	
	// objets dao
	
	Cote1DAO cote1dao = DaoFactoryMySQL.getCote1DAO();
	Cote2DAO cote2dao = DaoFactoryMySQL.getCote2DAO();
	Cote3DAO cote3dao = DaoFactoryMySQL.getCote3DAO();
	Cote4DAO cote4dao = DaoFactoryMySQL.getCote4DAO();
	
	// Objets Cote
	
	Cote1 cote1 = new Cote1();
	Cote2 cote2 = new Cote2();
	Cote3 cote3 = new Cote3();
	Cote4 cote4 = new Cote4();
	
	
	
	
	
	
	
	public AddCote(BddTables neoC, int refCo,Color coulFond, Color coulTextPP,Color coulEntete, Color coulEnteteText, Color coulBoutonFond, Color coulBoutonText, 
			int l, int h ) 
	
	// neoC indique la table dans laquelle on va créer le nouveau code
	// refCo indique laréférence dans la tablede niveau hiérarchique supérieure
	{
		this.neoCote = neoC;
		this.refCo = refCo;
		this.largeur = l;
		this.hauteur = h;

       switch (this.neoCote) {
       case COTE1:
    	   this.nomFenetre="Ajout d'une clé de cote COTE1";
    	   break;
       case COTE2:
    	   this.coteRef=BddTables.COTE1;
    	   this.nomFenetre="Ajout d'une clé de cote COTE2";
    	   break;
       case COTE3:
    	   this.coteRef=BddTables.COTE2;
    	   this.nomFenetre="Ajout d'une clé de cote COTE3";
    	   break;
       case COTE4:
    	   this.coteRef=BddTables.COTE3;
    	   this.nomFenetre="Ajout d'une clé de cote COTE4";
    	   break;
    	default: this.coteRef=BddTables.AUCUNE;   
   
       }
       
      
       

       this.coulFond=coulFond;
       this.coulTextPP=coulTextPP;
       this.coulEntete= coulEntete;
       this.coulEnteteText= coulEnteteText;
   	   this.coulBoutonFond= coulBoutonFond;
       this.coulBoutonText= coulBoutonText;
       
       init0();
       init1();
       init2();
       init3();
       init5();
       
	}
	
	private void init0()
	{
		// On doit initialiserles champs à ce niveau, à savoir:
		// Mettre en blanc le champ à saisir etlepasserà modifiable
		// Récupérer les valeurs pour les champs de codes de niveau hiérarchiques supérieurs
		// 
		
		this.c1.setEditable(false);
		this.c2.setEditable(false);
		this.c3.setEditable(false);
		this.c4.setEditable(false);
		
		switch (this.neoCote) {
	       case COTE1:
	    	   this.c1.setEditable(true);
	    	   // juste à initialiser les couleurs de champ
	    	   // La récupération des codes se fait bien
	    	   this.c1.setBackground(Color.WHITE);
	    	   this.c1.setForeground(Color.BLACK);
	       	   this.c2.setBackground(Color.DARK_GRAY);
	    	   this.c2.setForeground(Color.DARK_GRAY);
	       	   this.c3.setBackground(Color.DARK_GRAY);
	    	   this.c3.setForeground(Color.DARK_GRAY);
	       	   this.c4.setBackground(Color.DARK_GRAY);
	    	   this.c4.setForeground(Color.DARK_GRAY);
	    	   
	    	   
	    	   break;
	       case COTE2:
	    	   this.c2.setEditable(true);
	    	   // couleurs de champs et récupération du code de la cote de niveau 1
	    	   
	    	   cote1 = cote1dao.findId(refCo);
	    	   this.c1.setBackground(Color.WHITE);
	    	   this.c1.setForeground(Color.LIGHT_GRAY);
	    	   this.c1.setText(cote1.getCode());
	       	   this.c2.setBackground(Color.WHITE);
	    	   this.c2.setForeground(Color.BLACK);
	       	   this.c3.setBackground(Color.DARK_GRAY);
	    	   this.c3.setForeground(Color.DARK_GRAY);
	       	   this.c4.setBackground(Color.DARK_GRAY);
	    	   this.c4.setForeground(Color.DARK_GRAY);

	    	   break;
	       case COTE3:
	    	   this.c3.setEditable(true);
	    	   // cote2
	    	   cote2 = cote2dao.findId(refCo);
	    	   cote1 = cote1dao.findId(cote2.getCote1());
	    	   this.c1.setBackground(Color.WHITE);
	    	   this.c1.setForeground(Color.LIGHT_GRAY);
	    	   this.c1.setText(cote1.getCode());
	       	   this.c2.setBackground(Color.WHITE);
	    	   this.c2.setForeground(Color.LIGHT_GRAY);
	    	   this.c2.setText(cote2.getCode());
	       	   this.c3.setBackground(Color.WHITE);
	    	   this.c3.setForeground(Color.BLACK);
	       	   this.c4.setBackground(Color.DARK_GRAY);
	    	   this.c4.setForeground(Color.DARK_GRAY);

	    	   break;
	       case COTE4:
	    	   this.c4.setEditable(true);
	    	   cote3 = cote3dao.findId(refCo);
	    	   cote2 = cote2dao.findId(cote3.getCote2());
	    	   cote1 = cote1dao.findId(cote2.getCote1());
	    	   this.c1.setBackground(Color.WHITE);
	    	   this.c1.setForeground(Color.LIGHT_GRAY);
	    	   this.c1.setText(cote1.getCode());
	       	   this.c2.setBackground(Color.WHITE);
	    	   this.c2.setForeground(Color.LIGHT_GRAY);
	    	   this.c2.setText(cote2.getCode());
	       	   this.c3.setBackground(Color.WHITE);
	    	   this.c3.setForeground(Color.LIGHT_GRAY);
	    	   this.c3.setText(cote3.getCode());
	       	   this.c4.setBackground(Color.WHITE);
	    	   this.c4.setForeground(Color.BLACK);

	    	   break;
 
	   
	       }
		
		
		
		
		
		
		
	}
	
	private void init1()
	{
		// panneau d'en tête
		 this.setTitle(this.nomFenetre);
		   	this.setSize(this.largeur, this.hauteur);
			this.setBackground(this.coulFond);
			this.setForeground(coulTextPP);
			
		this.panneauTitre=new JPanel();
		this.labelTitre.setText("Création d'un code de cote pour la table "+this.neoCote.toString());
		this.panneauTitre.add(this.labelTitre);
		this.panneauTitre.setBackground(Color.ORANGE);
	}
	
	private void init2()
	{
		// panneau de boutons
		bOK = new JButtonOutils("Valider",100,50,Color.ORANGE);
			bAnnule = new  JButtonOutils("Annuler",100,50,Color.ORANGE);
			this.panneauBoutons= new JPanel();
			this.panneauBoutons.setBackground(Color.cyan);
			this.panneauBoutons.add(this.bAnnule);
			this.panneauBoutons.add(this.bOK);
			this.bOK.addActionListener(new bOKListener());
			this.bAnnule.addActionListener(new bAnnuleListener());
			
		
	}
	
	
	private void init3()
	{
		// panneau de champs de saisie
		Dimension dimo = new Dimension(50,25);
		Dimension dimo2 = new Dimension(100,25);
		Dimension dimo3 = new Dimension(200,25);
		
		this.panneauCentral.setBackground(Color.WHITE);
		grille = new  GridBagLayout();
		grilleCont = new  GridBagConstraints();
		this.panneauCentral.setLayout(this.grille);
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.LabelCode.setPreferredSize(dimo);
		this.panneauCentral.add(this.LabelCode,this.grilleCont);
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		c1.setPreferredSize(dimo);
		this.panneauCentral.add(this.c1, this.grilleCont);
		this.grilleCont.gridx=2;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		c2.setPreferredSize(dimo);
		this.panneauCentral.add(this.c2, this.grilleCont);
		this.grilleCont.gridx=3;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		c3.setPreferredSize(dimo);
		this.panneauCentral.add(this.c3, this.grilleCont);
		this.grilleCont.gridx=4;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		c4.setPreferredSize(dimo);
		this.panneauCentral.add(this.c4, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=1;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.panneauCentral.add(this.labelNom, this.grilleCont);
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=1;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=2;
		this.grilleCont.weightx=3;
		this.nomSais.setPreferredSize(dimo2);
		this.panneauCentral.add(this.nomSais, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=2;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=2.5;
		this.panneauCentral.add(this.labelInfo, this.grilleCont);
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=2;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=3;
		this.grilleCont.weightx=1;
		this.infoSais.setPreferredSize(dimo3);
		this.panneauCentral.add(this.infoSais, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		
		
		
		
	}
	
	private void init5()
	{
		// Mise en place finale
		this.setLocationRelativeTo(null);
	
		this.add(this.panneau);
		 this.panneau.setLayout(new BorderLayout());
		 this.panneau.add(this.panneauTitre,BorderLayout.NORTH);
		 this.panneau.add(this.panneauCentral,BorderLayout.CENTER);
		this.panneau.add(this.panneauBoutons, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	
	// Les deux classes internes pour les boutons
	
	class bOKListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("Bouton OK pressé");
			
			
			int id=0;
			int idref = 0;
			StringBuffer nom=new StringBuffer();
			StringBuffer infos=new StringBuffer();
			StringBuffer code=new StringBuffer();
			float compteur = 0.0f;
			boolean b1 = true;
	
			
			// verification longeur du champ nom
			if(nomSais.getText().length()>50)
			{
				nomSais.setBackground(Color.RED);
				b1=false;
			}
			else
			{
				nomSais.setBackground(Color.WHITE);
				nom = new StringBuffer(nomSais.getText());
			}
			
			// verification longeur du champ infos
			if(infoSais.getText().length()>250)
			{
				infoSais.setBackground(Color.RED);
				b1=false;
			}
			else
			{
				infoSais.setBackground(Color.WHITE);
				infos = new StringBuffer(infoSais.getText());
			}
			
			
			
			switch (neoCote) {
		       case COTE1:
		    	   // création d'une cote1
		    	   // verification de longueur:
		    	   if(c1.getText().length()>3)
		    	   {
		    		   // pas bon. On met en rouge le champ
		    		   c1.setBackground(Color.RED);
		    		   b1=false;
		    	   }
		    	   else
		    	   {
		    		   c1.setBackground(Color.WHITE); 
		    		   code= new StringBuffer(c1.getText());
		    	   }
		    	   
		    	   break;
		       case COTE2:
		    	   // création d'une cote1
		    	   // verification de longueur:
		    	   if(c2.getText().length()>3)
		    		  
		    	   {
		    		   // pas bon. On met en rouge le champ
		    		   c2.setBackground(Color.RED);
		    		   b1=false;
		    	   }
		    	   else
		    	   {
		    		   c2.setBackground(Color.WHITE); 
		    		   code= new StringBuffer(c2.getText());
		    	   }
		    	   break;
		       case COTE3:
		    	   // création d'une cote1
		    	   // verification de longueur:
		    	   if(c3.getText().length()>5)
		    		   
		    	   {
		    		   b1=false;
		    		   // pas bon. On met en rouge le champ
		    		   c3.setBackground(Color.RED);
		    	   }
		    	   else
		    	   {
		    		   c3.setBackground(Color.WHITE); 
		    		   code= new StringBuffer(c3.getText());
		    	   }
		    	   break;
		       case COTE4:
		    	   // création d'une cote1
		    	   // verification de longueur:
		    	   if(c4.getText().length()>5)
		    	   {
		    		   b1=false;
		    	   
		    		   // pas bon. On met en rouge le champ
		    		   c4.setBackground(Color.RED);
		    	   }
		    	   else
		    	   {
		    		   c4.setBackground(Color.WHITE);
		    		   code= new StringBuffer(c4.getText());
		    	   }
		    	   break;

		       }
			
			if(b1)
			{
														
				switch (neoCote) {
			       case COTE1:
			    	   cote1 = new Cote1(id,code.toString(),nom.toString(),infos.toString());
			    	   cote1dao.create(cote1);
			    	   dispose();
			    	   break;
			       case COTE2:
			    	   cote2 = new Cote2(id,refCo,  code.toString(), nom.toString(), infos.toString());
			    	   cote2dao.create(cote2);
			    	   dispose();
			    	   break;
			       case COTE3:
			    	   cote3 = new Cote3(id, refCo, code.toString(), nom.toString(), infos.toString());
			    	   cote3dao.create(cote3);
			    	   dispose();
			    	   break;
			       case COTE4:
			    	   cote4 = new Cote4(id, code.toString(), nom.toString(), refCo,  compteur,infos.toString());
			    	   System.out.println(cote4.toString());
			    	   cote4dao.create(cote4);
			    	   dispose();
			    	   break;
  
			   
			       }			
				

			}
			
			
			
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
	
}


