package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Genre;

class GenreTest {
	Genre g;

	@Test
	void testSetId() { 
		g= new Genre();
		g.setId(3);
		assertTrue("setId",g.getId()==3);
	}

	@Test
	void testGetId() {
		g = new Genre(5,"test");
		assertTrue("getId",g.getId()==5);
	}

	@Test
	void testSetNomGenre() {
		g= new Genre();
		g.setNomGenre("test");
		assertTrue("setNomGenre",g.getNomGenre()=="test");
	}

	@Test
	void testGetNomGenre() {
		g = new Genre(5,"test2");
		assertTrue("getNomGenre",g.getNomGenre()=="test2");
	}

}
