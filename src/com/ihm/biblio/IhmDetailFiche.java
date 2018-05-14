package com.ihm.biblio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
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
	
	// message d'erreur
	protected StringBuffer errMsg=new StringBuffer();  // message d'erreur créé par la validation
	
	
	protected abstract void initCreate();    // formulaire vide
	protected abstract void remplit();      // formulaire pre rempli
	protected abstract void initPan();
	protected abstract void editable(boolean ok);   // pour passer les champs en non editable ou editable selon ce qu'on fait
	
	protected abstract void initBoutons();
	protected abstract boolean valide();    // validation des saisies
	protected abstract Object  creeObjet(int id); // créee un objet avec les données du formulaire
	
	
	class quitterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			
		}
		
	}

}
