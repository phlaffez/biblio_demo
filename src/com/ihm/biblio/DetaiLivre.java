package com.ihm.biblio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.GenreDAO;
import com.DAO.biblio.LangueDAO;
import com.DAO.biblio.LivreDAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;
import com.metier.biblio.Pays;

import phl.outils.tables.ModelTablePhl;

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
	
	// les champs de saisie / consultation - initialisés dans le constructeur
	
		Font f = new Font("Courier", Font.BOLD, 13);
		private JTextField idChamp = new JTextField();
		private JTextField titreChamp = new JTextField();
		private JTextField genreChamp = new JTextField();
		private JComboBox listeGenres = new JComboBox();     // cas de la création ou de la modification
		private JTextField langueChamp= new JTextField();
		private JComboBox listeLangues = new JComboBox();     // cas de la création ou de la modification
		private JTextField classemmentChamp= new JTextField();  // Il faudra ajouter une table pour les localisations
		private JTextField acqChamp= new JTextField();
		private JTextField publiChamp= new JTextField();
		private JTextArea resumeChamp= new JTextArea();
		private JTextArea auteursChamp= new JTextArea();
		


		
		private Ordre ordre;
	
		private int idGenre;
		private int idLangue;
		private GenreDAO genredao = new GenreDAO(Mysql_Connect.getInstance());
		private LangueDAO languedao = new LangueDAO(Mysql_Connect.getInstance());

		
	// messages:
		private String titre="Bibliothèque: Livres";
		private String titre2="Création / Modification d'une fiche livre";


		public DetaiLivre(Color coulFond, Color coulTextPP, ModelTablePhl mtable,
				int numLig) {
			
			this.mtable=mtable;
			this.numLig=numLig;
			init1(coulFond,coulTextPP);
			this.ordre=Ordre.CREATION;
			initCreate();
			initPan();
			init2();
		}

		
		public  DetaiLivre(Livre obj, Ordre ordre,Color coulFond, Color coulTextPP, ModelTablePhl mtable,
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
		
		
		
		
		
		
		
		
		
		
		
		
	private void init1(Color coulFond, Color coulTextPP) {
			// TODO Auto-generated method stub
			
		}

	private void init2() {
			// TODO Auto-generated method stub
			
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


	@Override
	protected void dimChamps() {
		// TODO Auto-generated method stub
		
	}

}
