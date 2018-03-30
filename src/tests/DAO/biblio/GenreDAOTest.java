package tests.DAO.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.DAO.biblio.GenreDAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Genre;

class GenreDAOTest {

	@Test
	void testLastId() {
		GenreDAO genredao = new GenreDAO(Mysql_Connect.getInstance());
		int res = genredao.lastId();
		assertTrue("LastId",res == 3);
	}

	@Test
	void testFindIdInt() {
		GenreDAO genredao = new GenreDAO(Mysql_Connect.getInstance());
		Genre genre = genredao.findId(1);
		System.out.println(genre.getNomGenre());
		String sf = "SF";
		assertTrue("FindId",genre.getNomGenre().equals(sf));
	}

	@Test
	void testSelectAll() {
		GenreDAO genredao = new GenreDAO(Mysql_Connect.getInstance());
		List<Genre> genres = new ArrayList<Genre>();
		genres = genredao.selectAll();
		assertTrue("selectall",genres.size()==3);
	}

}
