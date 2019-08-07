package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Couleurs;

class CouleursTest {
	Couleurs couleur = new Couleurs(1,"VERT");

	@Test
	void testGetId() {
		assertTrue("getId",couleur.getId()==1);
	}

	@Test
	void testSetId() {
		couleur.setId(12);
		assertTrue("setId",couleur.getId()==12);
	}

	@Test
	void testGetNom() {
		assertTrue("getNom",couleur.getNom().equals("VERT"));
	}

	@Test
	void testSetNom() {
		couleur.setNom("BLEU");
		assertTrue("getNom",couleur.getNom().equals("BLEU"));
	}

}
