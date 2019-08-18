package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Album;
import com.metier.biblio.Localisation;

class AlbumTest {

	Album obj = new Album(1,2,"Test",3);
	
	@Test
	void testGetId() {
		assertTrue("getId",obj.getId()==1);
	}
	@Test
	void testSetId() {
		obj.setId(12);
		assertTrue("setId",obj.getId()==12);
	}

	
	@Test
	void testGetNom() {
		assertTrue("getNom",obj.getNom().equals("Test"));
	}

	@Test
	void testSetNom() {
		obj.setNom("BLEU");
		assertTrue("getNom",obj.getNom().equals("BLEU"));
	}
	
	@Test
	void testGetGenre() {
		assertTrue("getGenre",obj.getGenre()==2);
	}

	@Test
	void testSetGenreGenre() {
		obj.setGenre(10);
		assertTrue("setGenre",obj.getGenre()==10);
	}

	
	
	@Test
	void testGetLieu() {
		assertTrue("getLieu",obj.getLieu()==3);
	}
	

	@Test
	void testSetLieuInt() {
		obj.setLieu(33);
		assertTrue("getLieu",obj.getLieu()==33);
	}

	@Test
	void testSetLieuLocalisation() {
		Localisation loc = new Localisation(66,"Sur la lune");
		obj.setLieu(loc);
		assertTrue("getLieu",obj.getLieu()==66);
	}

	

}
