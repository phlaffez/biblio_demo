
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Auteur;
import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Genre;
import com.metier.biblio.Langue;
import com.metier.biblio.Livre;
import com.metier.biblio.ResumeLivre;

public class essais {

	public static void main(String[] args) {
		String nom_livre="Les tribulations d'un chinois en chine";
		String datePub = "12/2/1895";
		String classement = "Orbec carton 18";
		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
		Auteur a = new Auteur("VERNE","Jules");
		auteurs.add(a);
		 a = new Auteur("GRAVIS","Anton");
		auteurs.add(a);
	Livre	l = new Livre( 7, nom_livre, 2, 1,  datePub, false, classement, auteurs);
	System.out.println(l.toString());
		
	}

}
