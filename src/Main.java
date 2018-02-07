import com.DAO.biblio.DAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Pays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Main");
		
		
		DAO paysDAO=new PaysDAO(Mysql_Connect.getInstance());
		Pays pays = new Pays(4,"Chine");
		// System.out.println(pays.toString());
		paysDAO.delete(pays);
		
		
		System.out.println("-----------------");
		
		
	}
	
}
