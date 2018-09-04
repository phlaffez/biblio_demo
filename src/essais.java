
import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.AuteurGenreDAO;
import com.DAO.biblio.BddTables;
import com.DAO.biblio.DAO;
import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.GenreDAO;
import com.DAO.biblio.LivreAuteurDAO;
import com.DAO.biblio.LivreDAO;
import com.DAO.biblio.LocalisationDAO;
import com.DAO.biblio.ResumeLivreDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.ihm.biblio.DetailAuteur;
import com.ihm.biblio.Fenetre_PP;
import com.ihm.biblio.Ordre;
import com.metier.biblio.Auteur;
import com.metier.biblio.AuteurGenre;
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
		

//		DetailAuteur da = new DetailAuteur(Color.CYAN, Color.black);
		
//		AuteurDAO auteurdao = DaoFactoryMySQL.getAuteurDAO();
		LocalisationDAO locdao = new LocalisationDAO(Mysql_Connect.getInstance());
		ArrayList<Localisation> lieux = (ArrayList<Localisation>) locdao.selectAll();
	System.out.println(lieux.toString());
			}
}
		
	



