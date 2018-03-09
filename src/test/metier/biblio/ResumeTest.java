package test.metier.biblio;

import static org.junit.Assert.assertTrue;

// toString sera teste à la main

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.metier.biblio.LivreAuteur;
import com.metier.biblio.ResumeLivre;

class ResumeTest {

	ResumeLivre r;
	@Test
	void testSetId() {
		r=new ResumeLivre(10,"Les chaussettes de l'archiduchesse");
		r.setId(3);
		assertTrue("setId",r.getId()==3);
	}

	@Test
	void testGetId() {
		r=new ResumeLivre(10,"Les chaussettes de l'archiduchesse");
		assertTrue("getId",r.getId()==10);
	}

	@Test
	void testSetResume() {
		r=new ResumeLivre(10,"Les chaussettes de l'archiduchesse");
		String d="toto part en vadrouille";
		r.setResume(d);
		assertTrue("setResume",d.equals(r.getResume()));
	}

	@Test
	void testGetResume() {
		r=new ResumeLivre(10,"Les chaussettes de l'archiduchesse");
		String r2d2="Les chaussettes de l'archiduchesse";
		assertTrue("setResume",r2d2.equals(r.getResume()));
	}

}
