import java.util.ArrayList;

import com.metier.biblio.Auteur;
import com.metier.biblio.Livre;;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Main");
	
		
		/*
		 *  Livre(int id, String nom_livre, int genre, int langue, 
		 *  String datePub, 
				boolean unResume, String classement, 
				ArrayList<Auteur> auteurs)
		 */
		String nom="Vingt mille lieux sous les mers";
		int g=2;
		int l = 1;
		String d="01/11/1886";
		boolean u=false;
		String r="Orbec";
		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
		Auteur unaut=new Auteur("Vernes","Jules");
		auteurs.add(unaut);
		unaut=new Auteur("Laffez","Philippe");
		auteurs.add(unaut);
		Livre livre = new Livre(1,nom,g,l,d,u,r,auteurs);
		System.out.println(livre.toString());
		
		System.out.println("Setters:");
		livre.setId(10);
		System.out.println(livre.getId());
		unaut=new Auteur("Celine","Louis Ferdinand");
		auteurs.add(unaut);
		livre.setAuteurs(auteurs);
		System.out.println(livre.getAuteurs());
		livre.setNomLivre("Vingt mille merdes sous les ponts");
		System.out.println(livre.getNomLivre());
		livre.setClassement("Kinshasa");
		System.out.println(livre.getClassement());
		livre.setDatePublication("01/03/2018");
		System.out.println(livre.getDatePublication());
		livre.setClassement("ici");
		System.out.println(livre.getClassement());
		livre.setGenre(99);
		System.out.println("Genre: "+livre.getGenre());
		livre.setLangue(99);
		System.out.println("Langue: "+livre.getLangue());
		
	}
	
}
