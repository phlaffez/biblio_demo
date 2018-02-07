package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Langue;
import com.metier.biblio.Pays;

public class PaysTest {

	@Test
	void testGetId() {
		Pays pays = new Pays(5,"France");
		assertTrue("getId",pays.getId()==5);
	}
	
	@Test
	void testSetId() {
		Pays pays = new Pays(5,"France");
		pays.setId(6);
		assertTrue("setId",pays.getId()==6);
	}
	
	@Test
	void testGetNom() {
		Pays pays = new Pays(5,"France");
		assertTrue("getNom",pays.getNom()=="France");
	}
	
	@Test
	void testSetNom() {
		Pays pays = new Pays(5,"France");
		pays.setNom("USA");
		assertTrue("setNom",pays.getNom()=="USA");
	}
}
