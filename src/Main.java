import com.metier.biblio.ResumeLivre;;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Main");
		   String leres="Les chaussettes de l'archiduchesse\n sont elles seches";
		ResumeLivre resume = new ResumeLivre(4,leres);
	
	
		System.out.println(resume.toString());
		System.out.println("***********************************************");
		System.out.println(resume.getId());
		System.out.println(resume.getResume());
		
	}
	
}
