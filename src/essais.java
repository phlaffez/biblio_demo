
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.AuteurGenreDAO;
import com.DAO.biblio.DAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;
import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Genre;
import com.metier.biblio.Langue;
import com.metier.biblio.Livre;
import com.metier.biblio.ResumeLivre;
import com.outils.biblio.Cles;

public class essais {

	public static void main(String[] args) {
		
		Connection connex = Mysql_Connect.getInstance();
		AuteurGenreDAO agdao = new AuteurGenreDAO(connex);
boolean ok = agdao.deleteByCleLiaison(Cles.id_genre, 1);
System.out.println(ok);

	}
		
		
			}
		
	



