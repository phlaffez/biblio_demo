package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Cote4;

class Cote4Test {
	
	Cote4 c=new Cote4(1,"code","A",1,15.0f);

	@Test
	void testGetCode() {
		String code = "code";
		assertTrue("get code",code.equals(c.getCode()));
	}

	@Test
	void testSetCode() {
		String code = "Code2";
				c.setCode(code);;
		assertTrue("set code",code.equals(c.getCode()));
	}

	@Test
	void testGetNom() {
		String nom = "A";
		assertTrue("get nom",nom.equals(c.getNom()));
	}

	@Test
	void testSetNom() {
		String nom = "B";
		c.setNom(nom);
		assertTrue("get nom",nom.equals(c.getNom()));
	}

}
