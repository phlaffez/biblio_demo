package com.ihm.biblio;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/*
 * Classe abstrait définissant les méthodes à créer pour les fiches de détails.
 * Pourra sans doute être déplacée dans OutilsPersos si elle se
 * révèle pratique à l'usage
 */
import phl.outils.panneaux.outilsStandards.JButtonOutils;

public abstract class IhmDetailFiche <T,TDAO>{
	
	protected JPanel pan;
	protected JButtonOutils boutonCreer;
	protected JButtonOutils boutonModifier;
	protected JButtonOutils boutonQuitter;
	protected JButtonOutils boutonRAZ;
	
	
	public abstract void ihMDetaiFiche();
	public abstract void iHMDetaiFiche(T obj,Ordre ordre);
	public abstract void initCreate();
	public abstract void initPan();
	
	public abstract void initBoutons();
	  public abstract class CreerListener implements ActionListener{};
	
	

}
