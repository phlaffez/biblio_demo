package test.metier.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Auteur;

class AuteurTests {
	

	/** les tests de constructeurs ne peuvent être fait ainsi
	 * même si le truc automatique les prévoit (car renvoient void)
	@Test
	void testAuteur() {
		Auteur auteur=new Auteur();
		fail("Not yet implemented");
	}

	@Test
	void testAuteurStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testAuteurIntStringStringIntIntIntString() {
		fail("Not yet implemented");
	}
*/


	@Test
	void testGetId() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		assertTrue("getId",auteur.getId()==5);
	}
	
	@Test
	void testSetId() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		auteur.setId(6);
		assertTrue("setId",auteur.getId()==6);
	}

	

	@Test
	void testGetNom() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		assertTrue("getNom",auteur.getNom()=="Vernes");
	}
	
	@Test
	void testSetNom() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		auteur.setNom("VERNES");
		assertTrue("setNom",auteur.getNom()=="VERNES");
	}

	

	@Test
	void testGetPrenom() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		assertTrue("getPrenom",auteur.getPrenom()=="Jules");
	}
	
	@Test
	void testSetPrenom() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		auteur.setPrenom("Henri");
		assertTrue("setPrenom",auteur.getPrenom()=="Henri");
	}

	

	@Test
	void testGetId_pays() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		assertTrue("getIdpays",auteur.getId_pays()==2);
	}
	
	@Test
	void testSetId_pays() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		auteur.setId_pays(6);
		assertTrue("setIdpays",auteur.getId_pays()==6);
	}

	

	@Test
	void testGetAnnee_naiss() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		assertTrue("getAnnee_naiss",auteur.getAnnee_naiss()==1880);
	}
	
	@Test
	void testSetAnnee_naiss() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		auteur.setAnnee_naiss(1895);
		assertTrue("setAnnee_naiss",auteur.getAnnee_naiss()==1895);
	}
	
	@Test
	void testGetAnnee_deces() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		assertTrue("getAnnee_deces",auteur.getAnnee_deces()==1942);
	}
	
	@Test
	void testSetAnnee_deces() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		auteur.setAnnee_deces(1936);
		assertTrue("setAnnee_naiss",auteur.getAnnee_deces()==1936);
	}
	
	@Test
	void testGetInfo() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		assertTrue("getInfos",auteur.getInfo()=="grand auteur");
	}

	@Test
	void testSetInfo() {
		Auteur auteur = new Auteur(5,"Vernes","Jules",2,1880,1942,"grand auteur");
		auteur.setInfo("Très grand auteur");
		assertTrue("setInfos",auteur.getInfo()=="Très grand auteur");
	}

	

	/** tostring testé avec les constructeurs: ok
	@Test
	void testToString() {
		fail("Not yet implemented");
	}
	*/

}
