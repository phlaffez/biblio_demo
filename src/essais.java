
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.AuteurGenreDAO;
import com.DAO.biblio.DAO;
import com.DAO.biblio.LivreAuteurDAO;
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
		
		 int id=125;
		String nom_liv="Lecture";
		 int genre=12;
		 int langue=1;
		 String date_pub="12/01/1850";
		 boolean un_resume=false;
		 String classement="Ici ou là-bas";
		ArrayList<Auteur> auteurs=null;
		
		Livre livre = new Livre(id,nom_liv,genre,langue,date_pub,un_resume,classement, auteurs);
		System.out.println(livre.toString());
		
			}
}
		
	



