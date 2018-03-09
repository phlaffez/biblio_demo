package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.AuteurLangue;

class AuteurLangueTest {
	
	AuteurLangue al;

	@Test
	void testSetIdAuteur() {
		al = new AuteurLangue(10,2);
		al.setIdAuteur(20);
		assertTrue("setIdAuteur",al.getIdAuteur()==20);
	}

	@Test
	void testGetIdAuteur() {
		al = new AuteurLangue(10,2);
		assertTrue("getIdAuteur",al.getIdAuteur()==10);
	}

	@Test
	void testSetIdLangue() {
		al = new AuteurLangue(10,2);
		al.setIdLangue(3);
		assertTrue("setIdLangue",al.getIdLangue()==3);
	}

	@Test
	void testGetIdLangue() {
		al = new AuteurLangue(10,2);
		assertTrue("getIdLangue",al.getIdLangue()==2);
	}

	@Test
	void testToString() {
		al = new AuteurLangue(10,2);
		String s1="idAuteur = 10 / idLangue = 2";
		assertTrue("toString",s1.equals(al.toString()));

}
}