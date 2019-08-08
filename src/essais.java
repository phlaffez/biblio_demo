


import java.util.ArrayList;

import com.DAO.biblio.DaoFactoryMySQL;
import com.DAO.biblio.MonnaiesDAO;
import com.metier.biblio.Couleurs;
import com.metier.biblio.Monnaies;

import phl.outils.panneaux.outilsStandards.FenetreMessage;

public class essais {

	public static void main(String[] args) {
		


		Monnaies monnaie;
		ArrayList<Monnaies> monnaies = new ArrayList<Monnaies>();
		MonnaiesDAO monnaiedao = new DaoFactoryMySQL().getMonnaiesDAO();
	monnaies = (ArrayList<Monnaies>)monnaiedao.selectAll();
	
	
for (int i = 0; i<monnaies.size();i++)
{
	System.out.println(monnaies.get(i).toString());
}
		
			

		
System.out.println("Fin essai");

		 

		
			}
}
		
	



