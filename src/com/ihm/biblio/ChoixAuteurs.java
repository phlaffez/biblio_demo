package com.ihm.biblio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.metier.biblio.Auteur;

import phl.outils.panneaux.outilsStandards.JButtonOutils;

import java.util.ArrayList;

public class ChoixAuteurs extends JFrame{
	
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
	
	// bas
	
	private JPanel panBas = new JPanel();
	private JButtonOutils boutonRAZ;
	private JButtonOutils boutonAnnuler;
	private JButtonOutils boutonValider;
	

	public ChoixAuteurs(String titre, Color cf, Color cf2, Color cb) 
	
	{
		this.titre = titre;
		this.cf=cf;
		this.cf2 = cf2;
		this.cb = cb;
		init1();
		init3();
	}
	
	public ChoixAuteurs(String titre, Color cf, Color cf2, Color cb, ArrayList <Auteur> la)
	{
		this.titre = titre;
		this.la = la;
		this.cf=cf;
		this.cf2 = cf2;
		this.cb = cb;
		init1();
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
		 
		 this.pan.setBackground(cf);
		 this.pan.setLayout(new BorderLayout());
		 this.pan.add(this.panHaut, BorderLayout.NORTH);
		 
		 
	}
	
	
	private void placementBoutons()
	{
		this.boutonRAZ = new JButtonOutils("RAZ",180,50,cb);
		this.boutonAnnuler = new  JButtonOutils("Annuler",180,50,cb);
		this.boutonValider = new JButtonOutils("Valider",180,50,cb);
		
		this.panBas.add(this.boutonRAZ);
		this.panBas.add(this.boutonAnnuler);
		this.panBas.add(this.boutonValider);
		
		this.pan.add(this.panBas, BorderLayout.SOUTH);
	}
	
	
	private void init3()
	{
		this.setContentPane(this.pan);   
		this.setVisible(true);
			}
	

}
