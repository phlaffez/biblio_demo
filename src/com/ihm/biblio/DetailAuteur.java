package com.ihm.biblio;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.Pays;

public class DetailAuteur extends IhmDetailFiche<Auteur,AuteurDAO>{
	
	/* les boutons et le panneau d'affichage sont standards et sont donc des
	 * variables protected de la classe mère.
	 * Les variables de cette classe sont les champs d'affichage des variables
	 * de l'obet Auteur
	 */
	
	// Les 7 Labels
	private JLabel idLabel= new JLabel("Id:");
	private JLabel nomLabel = new JLabel("Nom:");
	private JLabel prenomLabel = new JLabel("Prenom");
	private JLabel paysLabel = new JLabel("Pays:");
	private JLabel naissLabel = new JLabel("Année de naissance:");
	private JLabel  decLabel = new JLabel("Année de décès");
	private JLabel infosLabel = new JLabel("Informations");
	
	// les champs de saisie / consultation - initialisés dans le constructeur
	
	private JTextField idChamp;
	private JTextField nomChamp;
	private JTextField prenomChamp;
	public JTextField paysChamp;     // cas de la consultation
	private JComboBox listePays;     // cas de la création ou de la modification
	private JTextField naissChamp;
	private JTextField decChamp;
	private JTextArea infoChamp;
	
	private int idPays;
	private PaysDAO paysdao = new PaysDAO(Mysql_Connect.getInstance());;
	private Pays pays;
	
	
	
	
	@Override
	public void ihMDetaiFiche() {
		initCreate();
		
	}

	@Override
	public void iHMDetaiFiche(Auteur obj, Ordre ordre) {
		if(ordre == Ordre.CREATION)
			{
			initCreate();
			}
		else
		{
			this.idChamp = new JTextField(Integer.toString(obj.getId()));
			this.idChamp.setEditable(false);
			this.nomChamp= new JTextField(obj.getNom());
			this.prenomChamp= new JTextField(obj.getPrenom());
			this.idPays = obj.getId_pays();
			// récupération du nom du pays:
			pays = paysdao.findId(idPays);
			if(ordre== Ordre.LECTURE)                // On n'affiche que le pays de l'auteur
			{
			this.paysChamp = new JTextField(pays.getNom());
			}
			else
			{
			    //	On affiche tous les pays dans un ComboBox, avec en première
				// ligne le pays déjà inseré
				this.listePays = new JComboBox();
				this.listePays.addItem(pays.getNom());
				ajouteTousPays();

				
			}
			this.naissChamp =  new JTextField(Integer.toString(obj.getAnnee_naiss()));
			this.decChamp = new JTextField(Integer.toString(obj.getAnnee_deces()));
	        this.infoChamp = new JTextArea(obj.getInfo());
		}
		
	}

	@Override
	public void initCreate() {
	// Affiche une fiche vide avec un objet vide
		
		this.idChamp = new JTextField("0");
		this.idChamp.setEditable(false);
		this.nomChamp= new JTextField("");
		this.prenomChamp= new JTextField("");
		this.listePays = new JComboBox();
		ajouteTousPays();
		
		this.naissChamp =  new JTextField("0"); 
		this.decChamp =  new JTextField("0"); 
        this.infoChamp = new JTextArea("");
		
	}

	@Override
	public void initPan() {
		// Mise en place des différents éléments du panneau
		
	}

	@Override
	public void initBoutons() {
		// TODO Auto-generated method stub
		
	}
	
	private void ajouteTousPays()
	{
		ArrayList<Pays> lp = (ArrayList<Pays>)paysdao.selectAll();
		// trier la liste mais comment ? Il faut faire avec un comparator,
		// et donc créer une méthode de tri. A voir donc
		
		// Insertion dans le Combo
		for(int i = 0;i<lp.size();i++)
		{
			this.listePays.addItem(lp.get(i).getNom());
		}
	
		
	}

}
