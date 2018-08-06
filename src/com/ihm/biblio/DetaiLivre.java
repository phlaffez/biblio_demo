package com.ihm.biblio;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.LivreDAO;
import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;

public class DetaiLivre  extends IhmDetailFiche<Livre,LivreDAO>{
	
	/* les boutons et le panneau d'affichage sont standards et sont donc des
	 * variables protected de la classe mère.
	 * Les variables de cette classe sont les champs d'affichage des variables
	 * de l'obet Livre. Sauf pourle champ Auteurs car ilpeut y avoir plusieurs
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


	public DetaiLivre() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initCreate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remplit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initPan() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void editable(boolean ok) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initBoutons() {
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

}
