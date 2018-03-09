import com.metier.biblio.AuteurGenre;
import com.metier.biblio.Langue;

public class essais {

	public static void main(String[] args) {

		AuteurGenre 		ag=new AuteurGenre(10,3);
		String s1="idAuteur = 10 / idGenre = 3";
		System.out.println(s1);
		System.out.println(ag.toString());
	}

}
