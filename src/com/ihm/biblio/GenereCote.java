package com.ihm.biblio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import phl.outils.panneaux.outilsStandards.JButtonOutils;

public class GenereCote extends JFrame{
	
	// divers
	private JPanel panneau = new JPanel();
	private int hauteur;
	private int largeur;
	private Color colfond;
	private Color colTexte;
	private Color colEtiq;
	
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
		init3();
	}

	private void init1()
	{
		this.setSize(this.largeur, this.hauteur);
		
		// Panneau de titre:
		this.labelTitre2.setText("Sélectionnez successivement les éléments constituants la cote");
		this.labelTitre2.setForeground(this.colTexte);
		this.labelTitre2.setBackground(this.colEtiq);
		this.panneauTitre.setBackground(this.colEtiq);
		this.panneauTitre.add(this.labelTitre2);
//		this.panneauTitre.setPreferredSize(new Dimension(this.largeur,this.hauteur/10));
		
	}
	
	
	
	private void init2()
	{
		
		int largelem = this.largeur/6;
		int hautelem = 25;
		// panneau pour le choix des cotes
		this.grille = new GridBagLayout();
		this.grilleCont = new GridBagConstraints();
		
		this.paneauCotes.setLayout(this.grille);
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.labelTitre.setText("Saisie de la cote du livre: "+this.titreLivre);
		this.paneauCotes.add(this.labelTitre, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=1;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
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
		this.paneauCotes.add(this.AddCote1, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.AddCote2= new JButtonOutils("Nouveau",largelem,hautelem,Color.orange);
		this.paneauCotes.add(this.AddCote2, this.grilleCont);
		
		this.grilleCont.gridx=2;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.AddCote3= new JButtonOutils("Nouveau",largelem,hautelem,Color.orange);
		this.paneauCotes.add(this.AddCote3, this.grilleCont);
		
		this.grilleCont.gridx=3;
		this.grilleCont.gridy=3;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.AddCote4= new JButtonOutils("Nouveau",largelem,hautelem,Color.orange);
		this.paneauCotes.add(this.AddCote4, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=4;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=2;
		this.grilleCont.weightx=1;
		this.coteGeneree.setPreferredSize(new Dimension(largelem*2, hautelem));
		this.paneauCotes.add(this.coteGeneree, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
	}
	
	
	private void init3()
	{
		
		this.setLocationRelativeTo(null);
	
		this.add(this.panneau);
		this.panneau.setLayout(new BorderLayout());
		this.panneau.add(this.panneauTitre,BorderLayout.NORTH);
		this.panneau.add(this.paneauCotes,BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
}
