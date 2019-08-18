package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Catyvert;

class CatyverTest {
	
	Catyvert obj = new  Catyvert(5,"catalogue","genre","les infos");

	@Test
	void testGetId() {
		assertTrue("getId",obj.getId()==5);
	}

	@Test
	void testSetId() {
		obj.setId(12);
		assertTrue("setId",obj.getId()==12);
	}

	@Test
	void testGetCatalogue() {
		assertTrue("getCatalogue",obj.getCatalogue().equals("catalogue"));
	}

	@Test
	void testSetCatalogue() {
		obj.setCatalogue("caataze");
		assertTrue("setCatalogue",obj.getCatalogue().equals("caataze"));
	}

	@Test
	void testGetCategorie() {
		assertTrue("getCategorie",obj.getCategorie().equals("genre"));
	}

	@Test
	void testSetCategorie() {
		obj.setCategorie("caataze");
		assertTrue("setCategorie",obj.getCategorie().equals("caataze"));
	}

	
	@Test
	void getSetInfos() {
		assertTrue("getInfos",obj.getInfos().equals("les infos"));
	}
	@Test
	void testSetInfos() {
		obj.setInfos("caataze");
		assertTrue("setInfos",obj.getInfos().equals("caataze"));
	}

}
