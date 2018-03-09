package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.AuteurGenre;

class AuteurGenreTest {

AuteurGenre ag;

	@Test
	void testGetIdAuteur() {
		ag=new AuteurGenre(10,3);
		assertTrue("getIdAuteur",ag.getIdAuteur()==10);
	}
	
	@Test
	void testSetIdAuteur() {
		ag=new AuteurGenre(10,3);
		ag.setIdAuteur(12);
		assertTrue("setIdAuteur",ag.getIdAuteur()==12);
	}

	
	@Test
	void testGetIdGenre() {
		ag=new AuteurGenre(10,3);
		assertTrue("setIdGenre",ag.GetIdGenre()==3);
	}
	@Test
	void testSetIdGenre() {
		ag=new AuteurGenre(10,3);
		ag.setIdGenre(1);
		assertTrue("getIdGenre",ag.GetIdGenre()==1);
	}

	

	@Test
	void testToString() {
		ag=new AuteurGenre(10,3);
		String s1="idAuteur = 10 / idGenre = 3";
		assertTrue("toString",s1.equals(ag.toString()));

	}

}
