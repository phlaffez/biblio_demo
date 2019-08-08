package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Couleurs;
import com.metier.biblio.Monnaies;

class MonnaiesTest {
	
	Monnaies obj = new Monnaies(1,"Franc","Fr");

	@Test
	void testSetId() {
		assertTrue("getId",obj.getId()==1);
	}

	@Test
	void testGetId() {
		obj.setId(12);
		assertTrue("setId",obj.getId()==12);
	}

	@Test
	void testGetNom() {
		assertTrue("getNom",obj.getNom().equals("Franc"));
	}

	@Test
	void testSetNom() {
		obj.setNom("BLEU");
		assertTrue("getNom",obj.getNom().equals("BLEU"));
	}

	@Test
	void testGetAbrev() {
		assertTrue("getNom",obj.getAbrev().equals("Fr"));
	}

	@Test
	void testSetAbrev() {
		obj.setAbrev("BLEU");
		assertTrue("getNom",obj.getAbrev().equals("BLEU"));
	}

}
