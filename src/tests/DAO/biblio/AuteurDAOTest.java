package tests.DAO.biblio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.DAO.biblio.AuteurDAO;
import com.DAO.biblio.DAO;
import com.dbacces.biblio.Mysql_Connect;
import com.metier.biblio.Auteur;

class AuteurDAOTest {

	@Test
	void testFindIdInt() {
		DAO auteurdao = new AuteurDAO(Mysql_Connect.getInstance());
		Auteur auteur;
		auteur = (Auteur) auteurdao.findId(5);
		assertTrue("FindId",auteur.getPrenom().equals("Boris"));
		
		
		
	}

	@Test
	void testSelectAll() {
		DAO auteurdao = new AuteurDAO(Mysql_Connect.getInstance());
		ArrayList<Auteur> liste;
		liste = (ArrayList<Auteur>) auteurdao.selectAll();
		assertTrue("SelectAll",liste.size()==6);
	}

	@Test
	void testGetByNom() {
		AuteurDAO auteurdao = new AuteurDAO(Mysql_Connect.getInstance());
		ArrayList<Auteur> liste;
		// un auteur unique
		String nom="Hugo";
		liste = (ArrayList<Auteur>) auteurdao.getByNom(nom);
		boolean ok1 = ((liste.size()==1) & (liste.get(0).getNom().equals("Hugo")));
	   
		// personne dans la liste
		
		 nom="Hugolin";
		liste = (ArrayList<Auteur>) auteurdao.getByNom(nom);
		boolean ok2 = false;
		if(liste != null) ok2 = true;
		
		// deux noms dans la liste
		 nom="Strougatski";
		liste = (ArrayList<Auteur>) auteurdao.getByNom(nom);
		boolean ok3 = true;
		if (liste ==null)
		{
			ok3 = false;
		}
		else
		{
			if(liste.size()!=2)
			{
				ok3 = false;
			}
			else
			{
			  boolean okk1 = ((liste.get(0).getId()==4) & (liste.get(1).getId()==5));
			  boolean okk2 = ((liste.get(0).getId()==5) & (liste.get(1).getId()==4));
			  if(okk1 == okk2) ok3=false;
			}
		}
		

		assertTrue("GetByNom",ok1 & ok2 & ok3);
	}
	
	
	
	

	@Test
	void testGetByNomPrenom() {
		String nom="Hugo";
		String prenom = "Victor";
		
		AuteurDAO auteurdao = new AuteurDAO(Mysql_Connect.getInstance());
		ArrayList<Auteur> liste;
		liste = (ArrayList<Auteur>) auteurdao.getByNomPrenom(nom, prenom);
		boolean ok1 = (liste.get(0).getId()==1);
		prenom = "jules";
		liste = (ArrayList<Auteur>) auteurdao.getByNomPrenom(nom, prenom);
		boolean ok2=false;
		if(liste!=null) ok2=true;
		System.out.print(ok1);System.out.print(" ");
		System.out.print(ok2);System.out.print(" ");
		assertTrue("GetByNomPrenom",ok1 & ok2);
	}

}
