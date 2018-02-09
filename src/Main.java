import com.DAO.biblio.AuteurLangueDAO;
import com.DAO.biblio.DAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.AuteurLangue;
import com.metier.biblio.Pays;
import com.outils.biblio.Cles;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Main");
		AuteurLangueDAO ald=new AuteurLangueDAO (Mysql_Connect.getInstance());
		Object les_a = ald.getByCleLiaison(Cles.id_langue, 1);
		System.out.println(les_a.getClass());
		System.out.println(les_a.toString());
	}
	
}
