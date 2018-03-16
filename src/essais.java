
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
		
		Connection connex = Mysql_Connect.getInstance();
		LivreAuteurDAO agdao = new LivreAuteurDAO(connex);
		ArrayList<Livre> l1 = (ArrayList<Livre>) agdao.getListeByCleLiaison(Cles.id_auteur, 6);
System.out.println(l1.size());
		if (l1.size()!=0)
{
	for (int i=0;i<l1.size();i++)
	{
		System.out.println(l1.get(i).getNomLivre());
	}
	System.out.println("--------------------------");
	ArrayList<Auteur> l2 = (ArrayList<Auteur>) agdao.getListeByCleLiaison(Cles.id_livre, 4);
	if (l2.size()!=0)
	{
		for (int i=0;i<l2.size();i++)
		{
			System.out.println(l2.get(i).getNom()+" "+l2.get(i).getPrenom());
		}
		System.out.println("--------------------------");
		
	
}

	}
		
		
			}
}
		
	



