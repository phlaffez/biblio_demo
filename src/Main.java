import com.DAO.biblio.DAO;
import com.DAO.biblio.LangueDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Langue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Main");
		
		
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		Langue langue = (Langue) langueDAO.findId(9);
		System.out.println(langue.toString());
		langue.setNom("PERSAN");
		System.out.println(langue.toString());
		langueDAO.update(langue);
		System.out.println("-----------------");
		
		
	}
	
}
