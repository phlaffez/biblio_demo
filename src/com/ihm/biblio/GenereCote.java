package com.ihm.biblio;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import phl.outils.panneaux.outilsStandards.JButtonOutils;

public class GenereCote extends JFrame{
	// nom fenetre:
	private String nomFenetre;

	// titre:
	private JPanel panneauTitre;
	private String titre;
	private JLabel labelTitre= new JLabel();
	private JPanel jpanTitre = new JPanel();
	
	// Génération de la cote
	
	// Boutons
	
	private JPanel paneauBoutons;
	private JButtonOutils bOK;
	private JButtonOutils bAnnule;
	private JButtonOutils  AddCote1;
	 private JButtonOutils  AddCote2;
	private JButtonOutils  AddCote3;
	private JButtonOutils  AddCote4;
	
	// Listes de selection et de compteurs
	
	private JPanel paneauCotes;
	private JComboBox  listeCote1;
	private JComboBox  listeCote2;
	private JComboBox  listeCote3;
	private JComboBox  listeCote4;
	private JTextArea listeCompteur;     // On mettra ici tous les numeros déjà utilisés avec cette cote	
	private JTextField champCompteurSais;
	private JTextField coteGeneree;
	private JPanel panneauCote4;
		
	

}
