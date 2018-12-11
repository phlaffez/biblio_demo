package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Cote1;

class Cote1Test {

	Cote1 c1;
	@Test
	void testCote1IntStringString() {
		// constructeur complet
		c1 = new Cote1(12,"code","nom");
		String s = "12 / CODE / NOM";
		assertTrue("Const complet",s.equals(c1.toString()));
	}

	@Test
	void testCote1StringString() {
		c1 = new Cote1("code","nom");
		String s = "0 / CODE / NOM";
		assertTrue("Const partiel",s.equals(c1.toString()));
		System.out.println(s);
		System.out.println(c1.toString());
	}

	@Test
	void testGetIdCote1() {
		c1 = new Cote1(12,"code","nom");
		assertTrue("getId",c1.getIdCote1()==12);
	}

	@Test
	void testSetIdCote1() {
		c1 = new Cote1(12,"code","nom");
		c1.setIdCote1(15);
		assertTrue("setId",c1.getIdCote1()==15);
	}

	@Test
	void testGetCode() {
		c1 = new Cote1(12,"code","nom");
		String s = "CODE";
		assertTrue("getCode",s.equals(c1.getCode()));
	}

	@Test
	void testSetCode() {
		c1 = new Cote1(12,"code","nom");
		c1.setCode("code2");
		String s = "CODE2";
		assertTrue("setCode",s.equals(c1.getCode()));
	}

	@Test
	void testGetNom() {
		c1 = new Cote1(12,"code","nom");
		String s = "NOM";
		assertTrue("getNom",s.equals(c1.getNom()));
	}

	@Test
	void testSetNom() {
		c1 = new Cote1(12,"code","nom");
		String s = "NOM2";
		c1.setNom("nom2");
		assertTrue("getNom",s.equals(c1.getNom()));
	}

}
