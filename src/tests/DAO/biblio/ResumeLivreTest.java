package tests.DAO.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.DAO.biblio.ResumeLivreDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.ResumeLivre;

class ResumeLivreTest {

	@Test
	void testLastId() {
		ResumeLivreDAO resumedao = new ResumeLivreDAO(Mysql_Connect.getInstance());
		int i = resumedao.lastId();
		assertTrue("LastId",i == 4);
	}

	@Test
	void testFindIdInt() {
		ResumeLivre resume;
		ResumeLivreDAO resumedao = new ResumeLivreDAO(Mysql_Connect.getInstance());
		resume = resumedao.findId(3);
		assertTrue("Resume existant",resume.getResume().equals("essai"));
		resume = resumedao.findId(12);
		assertTrue("Resume inexistant", resume == null);
	}

	@Test
	void testSelectAll() {
		ResumeLivreDAO resumedao = new ResumeLivreDAO(Mysql_Connect.getInstance());
		ArrayList<ResumeLivre> liste = (ArrayList<ResumeLivre>)resumedao.selectAll();
		boolean ok = true;
		if (liste.size()!=2) ok = false;
		if(ok)
		{
			boolean okk1 = ((liste.get(0).getId()==3)
					          & (liste.get(1).getId()==4));
			boolean okk2 = ((liste.get(1).getId()==3)
			          & (liste.get(0).getId()==4));
			if(okk1 ==okk2) ok = false;
		}
		assertTrue("SelectAll",ok);
	}

}
