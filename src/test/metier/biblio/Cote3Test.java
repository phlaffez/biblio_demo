package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Cote2;
import com.metier.biblio.Cote3;

class Cote3Test {
	
	Cote3 c3;

	@Test
	void testCote3() {
		String s="0 / 0 /  / ";
		c3=new  Cote3();
		assertTrue("Const defaut",s.equals(c3.toString()));
	}

	@Test
	void testCote3IntIntStringString() {
		String s="1 / 2 / ee / bb";
		c3=new  Cote3(1,2,"ee","bb");
		assertTrue("Const complet",s.equals(c3.toString()));
	}

	@Test
	void testGetIdCote3() {
		c3=new  Cote3(1,2,"ee","bb");
		assertTrue("get id",c3.getIdCote3()==1);
	}

	@Test
	void testSetIdCote3() {
		c3=new  Cote3(1,2,"ee","bb");
		c3.setIdCote3(10);
		assertTrue("set id",c3.getIdCote3()==10);
	}

	@Test
	void testGetCote2() {
		c3=new  Cote3(1,2,"ee","bb");
		assertTrue("get cote2",c3.getCote2()==2);
	}

	@Test
	void testSetCote2() {
		c3=new  Cote3(1,2,"ee","bb");
		c3.setCote2(25);
		assertTrue("get cote2",c3.getCote2()==25);
	}
	
	@Test
	void testGetCode() {
		c3=new  Cote3(1,2,"ee","bb");
		String code="ee";
		assertTrue("get code",code.equals(c3.getCode()));
	}

	@Test
	void testSetCode() {
		c3=new  Cote3(1,2,"ee","bb");
		String code="eyh";
		c3.setCode(code);
		assertTrue("get code",code.equals(c3.getCode()));
	}

	@Test
	void testGetNom() {
		c3=new  Cote3(1,2,"ee","bb");
		String code="bb";
		assertTrue("get nom",code.equals(c3.getNom()));
	}

	@Test
	void testSetNom() {
		c3=new  Cote3(1,2,"ee","bb");
		String code="bbpp";
		c3.setNom(code);
		assertTrue("get nom",code.equals(c3.getNom()));
	}

}
