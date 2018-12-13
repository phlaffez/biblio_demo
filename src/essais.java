
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
		int idLivre=0;
		Cote4 obj;
		Cote4DAO cote3dao = new Cote4DAO(Mysql_Connect.getInstance());
		List<Cote4> cotes4 = new ArrayList<Cote4>();
		boolean res;
		obj = new Cote4();
		
		System.out.println(obj.toString());


		 

		
			}
}
		
	



