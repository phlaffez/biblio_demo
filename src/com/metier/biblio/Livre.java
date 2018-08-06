package com.metier.biblio;


// testée ok le 01/03/2018
// je dois retester les constructeurs, ça déconne en utilisation depuis LivreDAO
import java.util.ArrayList;
import java.util.List;

import com.metier.biblio.Auteur;


public class Livre {
	private int id;
	private String nom_liv;
	private int genre;
	private int langue;
	private String datePub;
	private String dateAcq;
	private boolean un_resume;
	private String classement;
	private List<Auteur> auteurs;

	// constructeurs
	
	public  Livre()
	{
		this.id=0;
		this.nom_liv="";
		this.genre=0;
		this.langue=0;
		this.datePub="";
		this.dateAcq="";
		this.un_resume=false;
		this.classement="";
		this.auteurs=null;
	}
		
		public  Livre(int id, String nom_livre, int genre, int langue, String datePub,
				String dateAcq,	boolean unResume, String classement, ArrayList<Auteur> auteurs)
		
		{
			
			this.id = id;
			this.nom_liv=nom_livre;
			this.genre=genre;
			this.langue=langue;
			this.datePub=datePub;
			this.dateAcq=dateAcq;
			this.un_resume = unResume;
			this.classement = classement;
			this.auteurs=auteurs;
			
	}
	
	// getters et setters;
		
		public void setId(int id)
		{
			this.id=id;
		}
		
		public int getId()
		{
			return this.id;
		}
		
		
		public void setNomLivre(String n)
		{
			this.nom_liv=n;
		}
		
		public String getNomLivre()
		{
			return this.nom_liv;
		}
		
		public void setGenre(int g)
		{
			this.genre=g;
		}
		
		public int getGenre()
		{
			return this.genre;
		}
		
		public void setLangue(int l)
		{
			this.langue=l;
		}
		
		public int getLangue()
		{
			return this.langue;
		}
		
		public void setDatePublication(String dp)
		{
			this.datePub=dp;
		}
		public String getDatePublication()
		{
			return this.datePub;
		}
		
		public String getDateAcquisition()
		{
			return this.dateAcq;
		}
		
		public void setDateAcquisition(String dp)
		{
			this.dateAcq=dp;
		}
		
		
		
		public void setUnResume(boolean ur)
		{
			this.un_resume=ur;
		}
		
		public boolean getUnResume()
		{
			return this.un_resume;
		}
		
		public void setClassement(String c)
		{
			this.classement=c;
		}
		
		public String getClassement()
		{
			System.out.println(this.classement);
			return this.classement;
		}
		
		
		public void setAuteurs(List<Auteur> auteurs)
		{
			this.auteurs = auteurs;
		}
		
		public List<Auteur> getAuteurs()
		{
			return this.auteurs;
		}
		
		
		public String toString()
		{
			String s = "livre numéro ";
			s=s+Integer.toString(this.id)+":\n";
			s=s+this.nom_liv+"\n";
			
			
			if (this.auteurs==null)
			{
				System.out.println("glop");
				s=s+"Auteur(s) non renseigné(s) ou inconnu(s)";
			}
			else if(this.auteurs.isEmpty())
			{ System.out.println("auteur vide");
				s=s+"Auteur(s) non renseigné(s) ou inconnu(s)";
			}
			
			else
			{
				s=s+"Auteur(s):"+"\n";
				for(int i=0;i<this.auteurs.size();i++)
				{
					s=s+this.auteurs.get(i).getNom()+" "+this.auteurs.get(i).getPrenom()+"\n";
				}
			}
			s=s+"\n";
			
			
			
			s=s+"identificateur genre: "+Integer.toString(this.genre)+"\n";
			s=s+"identificateur langue: "+Integer.toString(this.langue)+"\n";
			// les deux lignes précédentes pourront faire appel aux classes DAO pour av oir les vrais noms
			s=s+"date de publication: "+this.datePub+"\n";
			s=s+"Présence d'un résumé: ";
			if(this.un_resume) 
			{
				s=s+"oui";
			}
			else
			{
				s=s+"non";
			};
			
			s=s+"\n";
			s= s+"Classement: "+this.classement;
	
		
		return s;		
			
			
		}

		 
}
