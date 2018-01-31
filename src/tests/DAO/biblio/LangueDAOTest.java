package tests.DAO.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.DAO.biblio.DAO;
import com.DAO.biblio.LangueDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Langue;

class LangueDAOTest {

	@Test
	void testLastId() {
		Langue langue = new Langue(0,"Anglais",0);
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		int prec = langueDAO.lastId();
		langueDAO.create(langue);
		assertTrue("latId",langueDAO.lastId()==prec+1);
	}

	
	@Test
	void testUpdateLangue() {
		Langue langue = new Langue(0,"Anglais",0);
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		langueDAO.create(langue);
		int id = langueDAO.lastId();
		langue.setNom("Chinois");
		langueDAO.update(langue);
		langue = (Langue)langueDAO.findId(id);
		assertTrue("Update",(("Chinois"==langue.getNom())&&id==langue.getId()));
	}
	
	@Test
	void testFindIdInt() {
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		Langue langue = (Langue)langueDAO.findId(0);
		assertTrue("FindId",langue.getNom()=="Latin");
		Langue langue2 = (Langue)langueDAO.findId(10000);
		assertTrue("FindId non trouve",langue2==null);
	}

	
	@Test
	void testCreateLangue() {
		Langue langue = new Langue(0,"Perse",0);
		DAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
		langueDAO.create(langue);
		int id=langueDAO.lastId();
		Langue langue2 = (Langue) langueDAO.findId(id);
		assertTrue("CreateLangue",langue2.getNom()=="Perse");
	}


	@Test
	void testGetByNom() {
				LangueDAO langueDAO=new LangueDAO(Mysql_Connect.getInstance());
				Langue langue=(Langue)langueDAO.getByNom("Allemand");
				assertTrue("GetbyNom",langue.getId()==2);
				Langue langue2=(Langue)langueDAO.getByNom("Tagalog");
				assertTrue("GetbyNom non trouve",langue2==null);
		
	}
	

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


	@Test
	void testSelectAll() {
		fail("Not yet implemented");
	}

}
