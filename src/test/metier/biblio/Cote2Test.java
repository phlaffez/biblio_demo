package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Cote2;

class Cote2Test {
	
	Cote2 c2;

	@Test
	void testCote2() {
		String s="0 / 0 /  /  / ";
		c2=new  Cote2();
		assertTrue("Const defaut",s.equals(c2.toString()));
	}

	@Test
	void testCote2IntIntStringString() {
		String s="1 / 2 / ee / bb / cc";
		c2=new  Cote2(1,2,"ee","bb","cc");
		assertTrue("Const complet",s.equals(c2.toString()));
	}

	@Test
	void testGetIdCote2() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		assertTrue("get id",c2.getIdCote2()==1);
	}

	@Test
	void testSetIdCote2() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		c2.setIdCote2(10);
		assertTrue("set id",c2.getIdCote2()==10);
	}

	@Test
	void testGetCote1() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		assertTrue("get cote1",c2.getCote1()==2);
	}

	@Test
	void testSetCote2() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		c2.setCote1(25);
		assertTrue("get cote1",c2.getCote1()==25);
	}

	@Test
	void testGetCode() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		String code="ee";
		assertTrue("get code",code.equals(c2.getCode()));
	}

	@Test
	void testSetCode() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		String code="eyh";
		c2.setCode(code);
		assertTrue("get code",code.equals(c2.getCode()));
	}

	@Test
	void testGetNom() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		String code="bb";
		assertTrue("get nom",code.equals(c2.getNom()));
	}

	@Test
	void testSetNom() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		String code="bbpp";
		c2.setNom(code);
		assertTrue("get nom",code.equals(c2.getNom()));
	}
	
	
	
	@Test
	void Infos() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		String code="cc";
		assertTrue("get infos",code.equals(c2.getInfos()));
	}

	@Test
	void testSetInfos() {
		c2=new  Cote2(1,2,"ee","bb","cc");
		String code="bbpp";
		c2.setInfos(code);
		assertTrue("set infos",code.equals(c2.getInfos()));
	}
	
	
	

}
