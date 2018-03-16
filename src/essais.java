
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
		AuteurDAO auteurdao = new AuteurDAO(connex);
		
		ArrayList<Auteur> liste = (ArrayList<Auteur>)auteurdao.getByNomPrenom("Strougatski","Boris");
		
		if (liste.size()!=0)
		{
			for (int i=0;i<liste.size();i++)
			{
				System.out.println(liste.get(i).getId()+" "+liste.get(i).getNom()+" "+liste.get(i).getPrenom());
			}
		}
	
	
		


	
		}
		
	}


