package com.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LocalisationTest {

	@Test
	void testSetId() {;
		Localisation loc = new Localisation(1,"KIN");
		loc.setId(5);
		assertTrue("setId",loc.getId()==5);
	}

	@Test
	void testGetId() {
		Localisation loc = new Localisation(1,"KIN");
		assertTrue("setId",loc.getId()==1);
	}

	@Test
	void testSetLieu() {
		Localisation loc = new Localisation(1,"KIN");
		loc.setLieu("PLESSIS");
		assertTrue("setId",loc.getLieu().equals("PLESSIS"));
	}

	@Test
	void testGetLieu() {
		Localisation loc = new Localisation(1,"KIN");
		assertTrue("setId",loc.getLieu().equals("KIN"));
	}

	@Test
	void testToString() {
		Localisation loc = new Localisation(1,"KIN");
		String l ="1:KIN";
		assertTrue("setId",loc.toString().equals(l));
	}

}
