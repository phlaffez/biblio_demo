
import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.AuteurGenreDAO;
import com.DAO.biblio.BddTables;
import com.DAO.biblio.Cote1DAO;
import com.DAO.biblio.Cote2DAO;
import com.DAO.biblio.Cote3DAO;
import com.DAO.biblio.Cote4DAO;
import com.DAO.biblio.DAO;
import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.GenreDAO;
import com.DAO.biblio.LivreAuteurDAO;
import com.DAO.biblio.LivreDAO;
import com.DAO.biblio.LocalisationDAO;
import com.DAO.biblio.OptionRecherche;
import com.DAO.biblio.ResumeLivreDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.ihm.biblio.ChoixAuteurs;
import com.ihm.biblio.DetailAuteur;
import com.ihm.biblio.Fenetre_PP;
import com.ihm.biblio.GenereCote;
import com.ihm.biblio.Ordre;
import com.metier.biblio.Auteur;
import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Cote1;
import com.metier.biblio.Cote2;
import com.metier.biblio.Cote3;
import com.metier.biblio.Cote4;
import com.metier.biblio.Genre;
import com.metier.biblio.Langue;
import com.metier.biblio.Livre;
import com.metier.biblio.LivreAuteur;
import com.metier.biblio.Localisation;
import com.metier.biblio.ResumeLivre;
import com.outils.biblio.Cles;

import phl.outils.panneaux.outilsStandards.FenetreMessage;

public class essais {

	public static void main(String[] args) {
		

		String titre = "test";
		Color cf2 = Color.cyan;
		Color cf = Color.LIGHT_GRAY;
		Color cb = Color.RED;
		Color ct = Color.BLACK;

		
		String tl="Les misérables";
		String auteur = "Hugo";
		int larg=800;
		int haut = 600;
		
//		GenereCote gc=new GenereCote(tl,auteur,larg,haut,cf,ct,cf2);
		
		

		 
	
		Cote3DAO c3dao = DaoFactoryMySQL.getCote3DAO();
		
		List<Cote3> c3 = (List<Cote3>)c3dao.getByCote2(14);
	
		System.out.println(c3);


		
	//	Cote2 cote2 = new Cote2(12,3,"FIC","Fiction","Revue fiction: revue littéraire de science fiction");
//		System.out.println(cote2.toString());
//		Cote2DAO cote2dao = DaoFactoryMySQL.getCote2DAO();
//		List<Cote2> cote2 = (List<Cote2>)cote2dao.getByCote1(1);

//		List<Cote1> cote1 = (List<Cote1>) cote1dao.getByCodeLike("PE",OptionRecherche.COMMENCE);
//      System.out.println(cote2.toString());
		
System.out.println("Fin essai");

		 

		
			}
}
		
	



