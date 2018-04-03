
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.AuteurGenreDAO;
import com.DAO.biblio.DAO;
import com.DAO.biblio.GenreDAO;
import com.DAO.biblio.LivreAuteurDAO;
import com.DAO.biblio.LivreDAO;
import com.DAO.biblio.ResumeLivreDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Genre;
import com.metier.biblio.Langue;
import com.metier.biblio.Livre;
import com.metier.biblio.LivreAuteur;
import com.metier.biblio.ResumeLivre;
import com.outils.biblio.Cles;

public class essais {

	public static void main(String[] args) {
		
ResumeLivre resume = new ResumeLivre(10,"ceci est un test de modification");

DAO resumedao = new ResumeLivreDAO(Mysql_Connect.getInstance());
resumedao.delete(resume);

System.out.println("Fini");
			}
}
		
	



