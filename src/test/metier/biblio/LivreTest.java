package test.metier.biblio;

// je testerai setAuteur et getAuteur à la main
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;

class LivreTest {

	Livre l;
	@Test
	void testSetId() {
		l= new Livre();
		l.setId(1);
		assertTrue("setId",l.getId()==1);
	}

	@Test
	void testGetId() {
		l= new Livre();
		assertTrue("setId",l.getId()==0);
	}

	@Test
	void testSetNomLivre() {
		l= new Livre();
		String s="test";
		l.setNomLivre(s);
		assertTrue("setNomLivre",s.equals(l.getNomLivre()));
	}

	@Test
	void testGetNomLivre() {
		String nom_livre="Les tribulations d'un chinois en chine";
		String dateAcq = "12/2/1895";
		String datePub = "13/2/1895";
		int classement = 1;
		l = new Livre( 7, nom_livre, 2, 1,  datePub, dateAcq,false, classement, null);
		assertTrue("getNomLivre",nom_livre.equals(l.getNomLivre()));
		
	}

	@Test
	void testSetGenre() {
		l= new Livre();
		l.setGenre(2);
		assertTrue("setGenre",l.getGenre()==2);
	}

	@Test
	void testGetGenre() {
		l= new Livre();
		assertTrue("getGenre",l.getGenre()==0);
	}

	@Test
	void testSetLangue() {
		l= new Livre();
		l.setLangue(6);
		assertTrue("setLangue",l.getLangue()==6);
	}

	@Test
	void testGetLangue() {
		l= new Livre();
		assertTrue("getLangue",l.getLangue()==0);
	}

	@Test
	void testSetDatePublication() {
		l= new Livre();
		String s="12/01/2017";
		l.setDatePublication(s);
		assertTrue("setDatePub",s.equals(l.getDatePublication()));
	}

	@Test
	void testGetDatePublication() {
		String nom_livre="Les tribulations d'un chinois en chine";
		String datePub = "12/2/1895";
		String dateAcq = "13/2/1895";
		int classement = 1;
		l = new Livre( 7, nom_livre, 2, 1,  datePub, dateAcq,false, classement, null);
		assertTrue("getDatePub",datePub.equals(l.getDatePublication()));
		assertTrue("getDateAcq",dateAcq.equals(l.getDateAcquisition()));
	}

	@Test
	void testSetUnResume() {
		l= new Livre();
		l.setUnResume(true);
		assertTrue("setUnResume",l.getUnResume()==true);
	}

	@Test
	void testGetUnResume() {
		l= new Livre();
		assertTrue("getUnResume",l.getUnResume()==false);
	}

	@Test
	void testSetClassement() {
		l= new Livre();
		int s=1;
		l.setClassement(s);
		assertTrue("setClassement",s==l.getClassement());
	}

	@Test
	void testGetClassement() {
		String nom_livre="Les tribulations d'un chinois en chine";
		String datePub = "12/2/1895";
		String dateAcq = "13/2/1895";
		int classement = 1;
		l = new Livre( 7, nom_livre, 2, 1,  datePub, dateAcq,false, classement, null);
		assertTrue("getClassement",1==l.getClassement());
		
	}



}
