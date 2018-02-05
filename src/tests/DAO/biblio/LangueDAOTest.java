package tests.DAO.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.DAO.biblio.DAO;
import com.DAO.biblio.LangueDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Langue;

class LangueDAOTest {

	// pas facile de tester les méthodes qui ne renvient rien. Je les faits à la main
	@Test
	void testLastId() {
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		assertTrue("latId",langueDAO.lastId()==9);
	}

	
	/*
	@Test	void testUpdateLangue() {
		Langue langue = new Langue(0,"Anglais",0);
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		langueDAO.create(langue);
		int id = langueDAO.lastId();
		langue.setNom("Chinois");
		langueDAO.update(langue);
		langue = (Langue)langueDAO.findId(id);
		assertTrue("Update",(("Chinois"==langue.getNom())&&id==langue.getId()));
	}*/
	
	@Test
	void testFindId() {
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		Langue langue = (Langue)langueDAO.findId(1);
		assertTrue("FindId",langue.getId()==1);		
		Langue langue2 = (Langue)langueDAO.findId(10000);
		System.out.println(langue2);
		assertTrue("FindId non trouve",langue2==null);

	}

/*	
	@Test
	void testCreateLangue() {
		Langue langue = new Langue(0,"Perse",0);
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		langueDAO.create(langue);
		int id=langueDAO.lastId();
		Langue langue2 = (Langue) langueDAO.findId(id);
		assertTrue("CreateLangue",langue2.getNom()=="PERSE");
	}
	*/


	@Test
	void testGetByNom() {
				LangueDAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
				Langue langue=(Langue)langueDAO.getByNom("Allemand");

				assertTrue("GetbyNom",langue.getId()==2);
				Langue langue2=(Langue)langueDAO.getByNom("Tagalog");
				assertTrue("GetbyNom non trouve",langue2==null);
		
	}
	
/*
	@Test
	void testDeleteLangue() {
		LangueDAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		Langue langue = new Langue(0,"Breton",0);
		langueDAO.create(langue);
		int id=langueDAO.lastId();
		Langue langue2=langueDAO.findId(id);
		assertTrue("delete, controle creation",langue2!=null);
		langueDAO.delete(langue);
		assertTrue("delete",langueDAO.findId(id)==null);
	}

*/
	@Test
	void testSelectAll() {
		LangueDAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		List<Langue> langues = langueDAO.selectAll();
		assertTrue("Select All",langues.size()==5);
	
	}

} 