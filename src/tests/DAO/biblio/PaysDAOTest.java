package tests.DAO.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.DAO.biblio.DAO;
import com.DAO.biblio.LangueDAO;
import com.DAO.biblio.PaysDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Langue;
import com.metier.biblio.Pays;

public class PaysDAOTest {
	
	@Test
	void testLastId() {
		DAO paysDAO=new PaysDAO(Mysql_Connect.getInstance());
		assertTrue("latId",paysDAO.lastId()==3);
	}

	
	
	
	@Test
	void testFindId() {
		DAO paysDAO=new PaysDAO(Mysql_Connect.getInstance());
		Pays pays = (Pays)paysDAO.findId(1);
		assertTrue("FindId",pays.getId()==1);		
		Pays pays2 = (Pays)paysDAO.findId(10000);
		assertTrue("FindId non trouve",pays2==null);

	}




	@Test
	void testGetByNom() {
				PaysDAO paysDAO=new PaysDAO(Mysql_Connect.getInstance());
				Pays pays=(Pays)paysDAO.getByNom("USA");

				assertTrue("GetbyNom",pays.getId()==2);
				Pays pays2=(Pays)paysDAO.getByNom("Inde");
				assertTrue("GetbyNom non trouve",pays2==null);
		
	}
	



	@Test
	void testSelectAll() {
		PaysDAO paysDAO=new PaysDAO(Mysql_Connect.getInstance());
		List<Pays> pays = paysDAO.selectAll();
		assertTrue("Select All",pays.size()==3);
	
	}

}
