package com.ihm.biblio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Classe abstrait définissant les méthodes à créer pour les fiches de détails.
 * Pourra sans doute être déplacée dans OutilsPersos si elle se
 * révèle pratique à l'usage
 */
import phl.outils.panneaux.outilsStandards.JButtonOutils;

public abstract class IhmDetailFiche <T,TDAO> extends JFrame{
	
	protected JPanel pan;
	protected JButtonOutils boutonCreer;
	protected JButtonOutils boutonModifier;
	protected JButtonOutils boutonQuitter;
	protected JButtonOutils boutonRAZ;
	
	// Couleurs:
	
	protected Color coulFond;          // couleur fond du panneau
	protected Color coulTextPP;        // couleur texte principal
	
	// gestionaire de disposition:
	
	protected GridBagLayout grille;
	protected GridBagConstraints grilleCont;
	
	
	public abstract void initCreate();
	public abstract void initPan();
	
	public abstract void initBoutons();
	  public abstract class CreerListener implements ActionListener{};
	
	

}
