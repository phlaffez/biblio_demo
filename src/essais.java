import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Genre;
import com.metier.biblio.Langue;
import com.metier.biblio.ResumeLivre;

public class essais {

	public static void main(String[] args) {
		String s1 = "Informatique";
	
		Genre	r=new Genre();
		System.out.println(r.toString());
		System.out.println("\n");
		r = new Genre(5,s1);
		System.out.println(r.toString());
		
	}

}
