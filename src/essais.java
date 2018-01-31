import com.metier.biblio.Langue;

public class essais {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Langue langue = new Langue();
		System.out.println("Constructeur par défaut:");
		System.out.println(langue.toString());
		
		Langue langue2 = new Langue(3,"Français",50);
		System.out.println("Constructeur complet:");
		System.out.println(langue2.toString());
		
	}

}
