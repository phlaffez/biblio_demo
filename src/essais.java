import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Langue;
import com.metier.biblio.ResumeLivre;

public class essais {

	public static void main(String[] args) {
		String s1 = "Les chaussettes de l'archiduchesse\n";
		s1 = s1+"sont elles seches ?\n";
		s1=s1+"archiseches?";
		ResumeLivre	r=new ResumeLivre(10,s1);
		System.out.println(r.toString());
	}

}
