package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Langue;

class LangueTest {

	
	@Test
	void testGetId() {
		Langue langue = new Langue(5,"Français",2);
		assertTrue("getId",langue.getId()==5);
	}
	
	@Test
	void testSetId() {
		Langue langue = new Langue(5,"Français",2);
		langue.setId(6);
		assertTrue("setId",langue.getId()==6);
	}


	

	@Test
	void testGetNom() {
		Langue langue = new Langue(5,"Français",2);
		assertTrue("getNom",langue.getNom()=="Français");
	}
	
	
	@Test
	void testSetNom() {
		Langue langue = new Langue(5,"Français",2);
		langue.setNom("Anglais");
		assertTrue("setNom",langue.getNom()=="Anglais");
	}
	
	

	@Test
	void testGetNombre() {
		Langue langue = new Langue(5,"Français",2);
		assertTrue("getNombre",langue.getNombre()==2);
	}
	
	@Test
	void testSetNombre() {
		Langue langue = new Langue(5,"Français",2);
		langue.setNombre(20);
		assertTrue("setNombre",langue.getNombre()==20);
	}
	

}
