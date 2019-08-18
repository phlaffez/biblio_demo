


import java.util.ArrayList;

import com.DAO.biblio.CouleursDAO;
import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.MonnaiesDAO;
import com.DAO.biblio.OptionRecherche;
import com.metier.biblio.Album;
import com.metier.biblio.Catyvert;
import com.metier.biblio.Couleurs;
import com.metier.biblio.Monnaies;

import phl.outils.panneaux.outilsStandards.FenetreMessage;
import test.metier.biblio.GenreStock;

public class essais {

	public static void main(String[] args) {
		

/*
		Monnaies monnaie;
		
		ArrayList<Monnaies> monnaies = new ArrayList<Monnaies>();
		ArrayList<Couleurs> couleurs = new ArrayList<Couleurs>();
		DaoFactoryMySQL usine = new DaoFactoryMySQL();
		MonnaiesDAO monnaiedao = usine.getMonnaiesDAO();
		CouleursDAO couleurdao = usine.getCouleursDAO();
	monnaies = (ArrayList<Monnaies>)monnaiedao.selectAll();
	
	monnaie = (Monnaies)monnaiedao.getByNom("FRANC");
	
	monnaies = (ArrayList<Monnaies>) monnaiedao.getByNomLike("n", OptionRecherche.CONTIEND);
	couleurs = (ArrayList<Couleurs>) couleurdao.getByNomLike("V", OptionRecherche.CONTIEND);
		
for (int i = 0; i<couleurs.size();i++)
{
	System.out.println(couleurs.get(i).toString());
}
		

		
		GenreStock gs;
		
		gs = new GenreStock(2, "album","albums pour exposition");
		System.out.println(gs.toString());
		
		
Album album = new Album(2,"test",3);

System.out.println(album.toString());
		
	*/
		
		Catyvert cat = new Catyvert(5,"catalogue","genre","les infos");
		System.out.println(cat.toString());
		
System.out.println("Fin essai");

		 

		
			}
}
		
	



