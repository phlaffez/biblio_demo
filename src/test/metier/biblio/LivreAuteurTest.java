package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.AuteurGenre;
import com.metier.biblio.LivreAuteur;

class LivreAuteurTest {

	LivreAuteur la;
	@Test
	void testSetIdAuteur() {
		la=new LivreAuteur(10,3);
		la.setIdAuteur(25);
		assertTrue("setIdAuteur",la.getIdAuteur()==25);
	}

	@Test
	void testGetIdAuteur() {
		la=new LivreAuteur(10,3);
				assertTrue("getIdAuteur",la.getIdAuteur()==10);
	}

	@Test
	void testSetIdLivre() {
		la=new LivreAuteur(10,3);
		la.SetIdLivre(25);
		assertTrue("setIdLivre",la.getIdLivre()==25);
	}

	@Test
	void testGetIdLivre() {
		la=new LivreAuteur(10,3);
		assertTrue("getIdLivre",la.getIdLivre()==3);
	}

	@Test
	void testToString() {
		la=new LivreAuteur(10,3);
		String s1="idAuteur = 10 / idLivre = 3";
	}
}
