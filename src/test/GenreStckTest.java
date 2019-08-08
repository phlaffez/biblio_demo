package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Monnaies;

import test.metier.biblio.GenreStock;

class GenreStckTest {

GenreStock obj = new GenreStock(1,"Franc","infos");
	
	@Test
	void testGetId() {
		assertTrue("getId",obj.getId()==1);
	}

	@Test
	void tesSetId() {
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
	void testGetRemarques() {
		assertTrue("getremarques",obj.getRemarques().equals("infos"));
	}

	@Test
	void testSetremarques() {
		obj.setremarques("BLEU");
		assertTrue("getinfos",obj.getRemarques().equals("BLEU"));
	}

}
