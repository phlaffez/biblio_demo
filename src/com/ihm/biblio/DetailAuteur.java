package com.ihm.biblio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.Pays;

import phl.outils.panneaux.outilsStandards.FenetreMessage;
import phl.outils.panneaux.outilsStandards.JButtonOutils;
import phl.outils.testsNumeriques.testNumeric;

public class DetailAuteur extends IhmDetailFiche<Auteur,AuteurDAO>{
	
	/* les boutons et le panneau d'affichage sont standards et sont donc des
	 * variables protected de la classe mère.
	 * Les variables de cette classe sont les champs d'affichage des variables
	 * de l'obet Auteur
	 */
	
	
	private Auteur obj;
	
	// Les 7 Labels
	private JLabel idLabel= new JLabel("Id:");
	private JLabel nomLabel = new JLabel("Nom:");
	private JLabel prenomLabel = new JLabel("Prenom");
	private JLabel paysLabel = new JLabel("Pays:");
	private JLabel naissLabel = new JLabel("Année de naissance:");
	private JLabel  decLabel = new JLabel("Année de décès");
	private JLabel infosLabel = new JLabel("INFORMATIONS");
	private Dimension dimLabel;
	private Dimension dimChamp;
	private JScrollPane jsc;


	
	// les champs de saisie / consultation - initialisés dans le constructeur
	
	Font f = new Font("Courier", Font.BOLD, 13);
	private JTextField idChamp;
	private JTextField nomChamp;
	private JTextField prenomChamp;
	private JTextField paysChamp;     // cas de la consultation
	private JComboBox listePays;     // cas de la création ou de la modification
	private JTextField naissChamp;
	private JTextField decChamp;
	private JTextArea infoChamp;

	
	private Ordre ordre;
	
	// Le gridBagLayout pour l'affichage des composants

	
	private int idPays;
	private PaysDAO paysdao = new PaysDAO(Mysql_Connect.getInstance());
	private Pays pays;
	
// messages:
	private String titre="Bibliothèque: Auteurs";
	private String titre2="Création / Modification d'une fiche auteur";
	
	
	
	public DetailAuteur(Color coulFond, Color coulTextPP) {
		
		init1(coulFond,coulTextPP);
		this.ordre=Ordre.CREATION;
		initCreate();
		initPan();
		init2();
	}


	

	
	private void init2() {
		this.setContentPane(this.pan);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}


	public  DetailAuteur(Auteur obj, Ordre ordre,Color coulFond, Color coulTextPP)
	{
	
		this.obj=obj;
		
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
				this.setTitle("Consultation d'une fiche auteur");
			}
			else
			{
				this.setTitle("Modification d'une fiche auteur");
			}
			remplit();
			initPan();
	        init2();
		}
		
	}
	

	@Override
	protected void initCreate() {
	
	// Affiche une fiche vide avec un objet vide
		this.setTitle("Création d'une fiche auteur");
		
		this.idChamp = new JTextField("0");
		this.nomChamp= new JTextField("");
		this.prenomChamp= new JTextField("");
		this.listePays = new JComboBox();
		ajouteTousPays();
		
		this.naissChamp =  new JTextField("0"); 
		this.decChamp =  new JTextField("0"); 
        this.infoChamp = new JTextArea("");
        editable(true);
		
	}

	@Override
	protected void initPan() {
		// Mise en place des différents éléments du panneau sur un GridBagLayout
		
		dimChamps();
		this.pan = new JPanel();
		this.pan.setSize(new Dimension(480,480));
		this.pan.setBackground(this.coulFond);
		this.grille = new GridBagLayout();
		this.pan.setLayout(grille);
		this.grilleCont = new GridBagConstraints();
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=0;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=2;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
		this.pan.add(this.idLabel, this.grilleCont);		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=0;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
		this.pan.add(this.idChamp, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=1;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
		this.pan.add(this.nomLabel, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=1;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
		this.pan.add(this.nomChamp, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		

		this.grilleCont.gridx=0;
		this.grilleCont.gridy=2;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
		this.pan.add(this.prenomLabel, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=2;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
		this.pan.add(this.prenomChamp, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=3;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
		this.pan.add(this.naissLabel, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=3;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
		this.pan.add(this.naissChamp, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=4;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
		this.pan.add(this.decLabel, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=4;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
		this.pan.add(this.decChamp, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		
		if (ordre!= Ordre.LECTURE)
		{
			// en création ou modification, on utilise le Combo
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=5;
		this.grilleCont.gridheight=2;
		this.grilleCont.gridwidth=1;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
		this.pan.add(this.paysLabel, this.grilleCont);
		
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=5;
		this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
		this.pan.add(this.listePays, this.grilleCont);
		}
		else
		{
			// En lecture seule on affiche uniquement le pays de l'auteur
			this.grilleCont.gridx=0;
			this.grilleCont.gridy=5;
			this.grilleCont.gridheight=1;
			this.grilleCont.gridwidth=1;
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_START;
			this.pan.add(this.paysLabel, this.grilleCont);
			
			this.grilleCont.anchor=GridBagConstraints.FIRST_LINE_END;
			this.paysChamp.setEditable(false);   // on ne doit pas modifier
			this.pan.add(this.paysChamp, this.grilleCont);
		}
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		
		// Le champ info est affiché sur toute la largeur, le titre sur une ligne
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=10;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=4;
		this.grilleCont.weightx=4;
		this.grilleCont.anchor=GridBagConstraints.CENTER;
		this.pan.add(this.infosLabel, this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=12;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=4;
		this.grilleCont.weighty=1;	
		this.grilleCont.weightx=2;
		this.grilleCont.fill=GridBagConstraints.BOTH;
		this.jsc = new JScrollPane(this.infoChamp);

		this.pan.add(this.jsc,this.grilleCont);
		this.grilleCont.gridwidth = GridBagConstraints.REMAINDER;
		initBoutons();
		
		this.grilleCont.gridx=0;
		this.grilleCont.gridy=13;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
		this.grilleCont.weightx=1;
		this.grilleCont.weighty=1;
		this.grilleCont.fill=GridBagConstraints.NONE;
//		this.grilleCont.anchor=GridBagConstraints.CENTER;
		this.pan.add(this.boutonCreer, this.grilleCont);
		
		this.grilleCont.gridx=1;
		this.grilleCont.gridy=13;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
//		this.grilleCont.anchor=GridBagConstraints.CENTER;
		this.pan.add(this.boutonModifier, this.grilleCont);
		
		this.grilleCont.gridx=2;
		this.grilleCont.gridy=13;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
//		this.grilleCont.anchor=GridBagConstraints.CENTER;
		this.pan.add(this.boutonRAZ, this.grilleCont);
		
		this.grilleCont.gridx=3;
		this.grilleCont.gridy=13;
		this.grilleCont.gridheight=1;
		this.grilleCont.gridwidth=1;
//		this.grilleCont.anchor=GridBagConstraints.CENTER;
//		this.grilleCont.fill = GridBagConstraints.HORIZONTAL;
		this.pan.add(this.boutonQuitter, this.grilleCont);

		
		
		
	}

	@Override
	protected void initBoutons() {
		// TODO Auto-generated method stub
		// cette méthode doit affecter les listener et placer les boutons dans
		// dans le bas de la grille
		
		 this.boutonCreer = new JButtonOutils("Créer fiche",180,50,Color.RED);
		 this.boutonModifier = new JButtonOutils("Valider modifications",180,50,Color.RED);
		this.boutonQuitter= new JButtonOutils("Quitter", 180, 50, Color.RED);
		this.boutonRAZ = new JButtonOutils("RAZ",180,50,Color.RED);
		
		this.boutonCreer.addActionListener(new CreerListener());
		this.boutonModifier.addActionListener(new ModifierListener());		
		this.boutonQuitter.addActionListener(new quitterListener());
		this.boutonRAZ.addActionListener(new RazListener());
		
	}
	
	private void ajouteTousPays()
	{
		ArrayList<Pays> lp = (ArrayList<Pays>)paysdao.selectAll();
		Collections.sort(lp);	
		
		// Insertion dans le Combo
		for(int i = 0;i<lp.size();i++)
		{
			this.listePays.addItem(lp.get(i).getNom());
		}
	
		
	}
	
private void init1(Color coulFond, Color coulTextPP)
{
	this.coulFond=coulFond;
	this.coulTextPP=coulTextPP;
	this.setBackground(this.coulFond);
//	this.setPreferredSize(new Dimension(800,600));
	this.setSize(new Dimension(800,600));
	 this.setResizable(false);
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

private void dimChamps()
{
	// dimensionnement des champs, police, etc.
	
	Dimension dimAnnee = new Dimension(50,25);
	Dimension dimChamp = new Dimension(250,25);
	Dimension dimInfo = new Dimension(750,300);

	
	this.nomChamp.setBackground(Color.WHITE);
	this.nomChamp.setVisible(true);	
	this.nomChamp.setFont(f);
	this.nomChamp.setPreferredSize(dimChamp);
	
	this.prenomChamp.setBackground(Color.WHITE);
	this.prenomChamp.setVisible(true);	
	this.prenomChamp.setFont(f);
	this.prenomChamp.setPreferredSize(dimChamp);
	
	
	this.naissChamp.setBackground(Color.WHITE);
	this.naissChamp.setVisible(true);	
	this.naissChamp.setFont(f);
	this.naissChamp.setPreferredSize(dimAnnee);
	
	this.decChamp.setBackground(Color.WHITE);
	this.decChamp.setVisible(true);	
	this.decChamp.setFont(f);
	this.decChamp.setPreferredSize(dimAnnee);
	
	this.infoChamp.setBackground(Color.WHITE);
	this.infoChamp.setVisible(true);	
	this.infoChamp.setFont(f);
	this.infoChamp.setPreferredSize(dimInfo);
}


@Override
protected void remplit() {
	this.idChamp = new JTextField(Integer.toString(this.obj.getId()));
	this.idChamp.setEditable(false);
	this.nomChamp= new JTextField(this.obj.getNom());
	this.prenomChamp= new JTextField(this.obj.getPrenom());
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
	this.naissChamp =  new JTextField(Integer.toString(this.obj.getAnnee_naiss()));
	this.decChamp = new JTextField(Integer.toString(this.obj.getAnnee_deces()));
    this.infoChamp = new JTextArea(obj.getInfo());
    if(this.ordre==Ordre.CREATION)
    {
    	editable(true);
    }
    else
    {
    	editable(false);
    }

	
}





@Override
protected void editable(boolean ok) {
	
	this.idChamp.setEditable(false);
	this.nomChamp.setEditable(ok);
	this.prenomChamp.setEditable(ok);
	this.paysChamp.setEditable(false);
	this.naissChamp.setEditable(ok);
	this.decChamp.setEditable(ok);
	this.infoChamp.setEditable(ok);
	
}


@Override
protected boolean valide() {
	/* vérifie que nom et prenoms sont bien des chaines de caratères valides sans chiffres
	 *  que l'année de naissance et celle de décès sont bien des nombres,
	 *   que l'année de naissance est bien avant celle de décès, 
	 *    que l'année de décès est bien avant la date d'aujourd'hui et que l'écart entre
	 *     naissance et décès n'exede pas 120 ans.
	  */
	
	Boolean ok = true;
	String[] interdit = {"0","1","2","3","4","5","6","7","8","9",
			"(",")",",[","|","{","}","+","*","/","_","\"","'","?","!",";"};
	
	String sn = this.nomChamp.getText();
	String sp = this.prenomChamp.getText();
	String an = this.naissChamp.getText();
	String ad = this.decChamp.getText();
	
	StringBuffer msg=new StringBuffer("");
	if(sn.isEmpty())
	{
		 msg.append("Le nom ne peux être vide.\n");
		 ok = false;
	}
	else
	{
	 // on vérifie qu'il n'y a pas de caractères interdits dans le nom, dans le prénom,
	// et que les années de naissances ou de décès correspondent à des entiers corrects,
	// c'est à dire des entiers compris entre -7000 et l'année en cours, et si
	// les champs sont vides on remplace par 9999
		
	// nom et prenom
		for (int i=0;i<interdit.length;i++)
		{
			if (sn.contains(interdit[i]))
			{
				ok = false;
				msg.append(" le caractère "+interdit[i]+" ne peut servir à écrire un nom\n");
				break;
			}
			
			if (sp.contains(interdit[i]))
			{
				ok = false;
				msg.append(" le caractère "+interdit[i]+" ne peut servir à écrire un nom\n");
				break;
			}
		}
		
 // tests années
		an = an.trim();           // on vire les espaces inutiles
		ad = ad.trim();
		
		if(an=="")              // si on n'a rien mis on remplace par 9999
		{
			an="9999";
		}
		if(ad=="")
		{
			ad = "9999";
		}
		
		this.naissChamp.setText(an);    // on met à jour les champs qui serviront 
		this.decChamp.setText(ad);      // pour la création de l'enregistrement		
		
		testNumeric tn = new testNumeric();
		boolean ok1 = tn.estEntier(an);
		if(!ok1)
		{
			ok = false;
			msg.append("Année de naissance invalide \n");
		}
		 ok1 = tn.estEntier(ad);
		if(!ok1)
		{
			ok = false;
			msg.append("Année de décès invalide \n");
		}
		
		
		
	}   // fin du else

	if(ok)
	{
		msg.append("Saisie validée");
		this.errMsg.append(msg);
	}
	return ok;
}

@Override
protected Object creeObjet(int id) {
	
	Auteur aut = new Auteur();
	PaysDAO pdao = DaoFactoryMySQL.getPaysDAO();
	Pays pays;
	aut.setId(id);
	aut.setNom(this.nomChamp.getText());
	aut.setPrenom(this.prenomChamp.getText());
	aut.setAnnee_naiss(Integer.parseInt(this.naissChamp.getText()));
	aut.setAnnee_deces(Integer.parseInt(this.decChamp.getText()));
	aut.setInfo(this.infoChamp.getText());
	pays = (Pays) pdao.getByNom(this.listePays.getSelectedItem().toString());
	System.out.println(pays.toString());
	aut.setId_pays(pays.getId());
	
	
	return aut;
}

// Listeners:

class CreerListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// validation du formulaire
		boolean ok = valide();
		
		if(ok)
		{
		// insertion d'une nouvelle valeur dans la base de données
		 // création de l'objet Auteur
		Auteur aut = (Auteur)creeObjet(0);
		// insertion:
		AuteurDAO autdao = DaoFactoryMySQL.getAuteurDAO();
		autdao.create(aut);
		//Afficher une boite de confirmation:
		int id = autdao.lastId();
		aut =  autdao.findId(id);
		
		//   affichage boite de message
		StringBuffer mess = new StringBuffer("La fiche auteur suivante a été créée:\n");
		mess.append(aut.toString());
		FenetreMessage fen = new FenetreMessage(titre,titre2,mess.toString(),
				300,300,Color.lightGray,Color.black);
		
		
		// RAZ du formulairepour saisie suivante
		initCreate();
		}
		else
		{
			StringBuffer mess = new StringBuffer("Les données du formulaire sont invalides:\n");
			mess.append(errMsg);
		
			FenetreMessage fen = new FenetreMessage(titre,titre2,mess.toString(),
					300,300,Color.lightGray,Color.black);
		}
		
		
	}
	}
	
	





class ModifierListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// mise à jour d'un enregistrement dans la base de données
		boolean ok = valide();
		if(ok)
		{
		// modification de l'auteur  dans la base de données
		 // création de l'objet Auteur
			int id = Integer.parseInt(idChamp.getText());
		Auteur aut = (Auteur)creeObjet(id);
		// insertion:
		AuteurDAO autdao = DaoFactoryMySQL.getAuteurDAO();
		autdao.update(aut);
		//Afficher une boite de confirmation:
		StringBuffer mess = new StringBuffer("La fiche auteur suivante a été modifiée:\n");
		mess.append(aut.toString());
		FenetreMessage fen = new FenetreMessage(titre,titre2,mess.toString(),
				300,300,Color.lightGray,Color.black);
		
		// TODO  affichage boite de message
		
		
		// RAZ du formulairepour saisie suivante
		initCreate();
		}
		else
		{
			// afficher une boite avec message d'erreur
			StringBuffer mess = new StringBuffer("Les données du formulaire sont invalides:\n");
			mess.append(errMsg);
		
			FenetreMessage fen = new FenetreMessage(titre,titre2,mess.toString(),
					300,300,Color.lightGray,Color.black);
		}
		
		
	}
	}
	
class  RazListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// remise à zero du formulaire
		if (ordre == Ordre.CREATION)
		{
			initCreate();
		
	}		
		else
		{
			remplit();
		}
	}
}


}








