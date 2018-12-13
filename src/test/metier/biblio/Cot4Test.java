package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Cote3;
import com.metier.biblio.Cote4;

class Cot4Test {
	
	Cote4 c3;

	@Test
	void testCote4() {
		String s="0 / 0 / 0.0";
		c3=new  Cote4();
		assertTrue("Const defaut",s.equals(c3.toString()));
	}

	@Test
	void testCote4IntIntFloat() {
		String s="1 / 3 / 1.5";
		c3=new  Cote4(1,3,1.5f);
		assertTrue("Const defaut",s.equals(c3.toString()));
	}

	@Test
	void testGetIdCote4() {
		String s="1 / 3 / 1.5";
		c3=new  Cote4(1,3,1.5f);
		assertTrue("get id",c3.getIdCote4()==1);
	}

	@Test
	void testSetIdCote4() {
		String s="1 / 3 / 1.5";
		c3=new  Cote4(1,3,1.5f);
		c3.setIdCote4(25);
		assertTrue("get id",c3.getIdCote4()==25);
	}

	@Test
	void testGetCote3() {
		String s="1 / 3 / 1.5";
		c3=new  Cote4(1,3,1.5f);
		assertTrue("get id",c3.getCote3()==3);
	}

	@Test
	void testSetCote3() {
		String s="1 / 3 / 1.5";
		c3=new  Cote4(1,3,1.5f);
		c3.setCote3(6);
		assertTrue("get id",c3.getCote3()==6);
	}

	@Test
	void testGetCompteur() {
		String s="1 / 3 / 1.5";
		c3=new  Cote4(1,3,1.5f);
		assertTrue("get id",c3.getCompteur()==1.5f);
	}

	@Test
	void testSetCompteur() {
		String s="1 / 3 / 1.5";
		c3=new  Cote4(1,3,1.5f);
		c3.setCompteur(25.6f);
		assertTrue("get id",c3.getCompteur()==25.6f);
	}

}
