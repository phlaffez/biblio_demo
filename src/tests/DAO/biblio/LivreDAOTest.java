package tests.DAO.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.DAO.biblio.LivreDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Livre;

class LivreDAOTest {

	@Test
	void testFindId() {
		LivreDAO livredao = new LivreDAO(Mysql_Connect.getInstance());
		Livre livre = livredao.findId(5);
		String s= livre.getNomLivre();
		assertTrue("FindId",s.equals("test"));
	}

	@Test
	void testGetByNom() {
		LivreDAO livredao = new LivreDAO(Mysql_Connect.getInstance());
		Livre livre;
		ArrayList<Livre> liste = new ArrayList<Livre>();
		liste = (ArrayList<Livre>)livredao.getByNom("test");
		boolean ok = false;
		if(liste!=null)
		{
		if (liste.size()==2)
		{
			if ((liste.get(0).getId()==5) && (liste.get(1).getId()==6))
			{
				ok = true;
			}
			
			if ((liste.get(0).getId()==6) && (liste.get(1).getId()==5))
			{
				ok = true;
			}
		}
		}
	
		
		assertTrue("GetByNom",ok);
	}
	
	@Test
	void testLastId() {
		LivreDAO livredao = new LivreDAO(Mysql_Connect.getInstance());
		assertTrue("LastId", livredao.lastId()==6);
	}
	
	

}
