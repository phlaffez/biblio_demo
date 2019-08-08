package test.metier.biblio;

/*
 * ***************************************************
 *                                                   *
 *                     Genres Stock                  *
 *  classe testée ok le 08/08/2019                   *
 * ***************************************************   
 */
 
 
public class GenreStock {
	
	
	
	

	private int id;
	private String nom;
	private String remarques;
	
	public GenreStock()
	// testé le 08/08/2019  OK
	{
		this.id = 0;
		this.nom = "";
		this.remarques = "";
	}
	
	public GenreStock(int id, String nom, String abrev)
	// testé le 08/08/2019  OK
	{
		this.id = id;
		this.nom = nom;
		this.remarques = abrev;
	}
	
	public GenreStock(String nom, String abrev)
	// testé le 08/08/2019  OK
	{
				this.nom = nom;
		this.remarques = abrev;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public String getRemarques()
	{
		return this.remarques;
	}
	
	public void setremarques(String remarques)
	{
		this.remarques = remarques;
	}
	
	public String toString()
	{
		// testé le 08/08/2019  OK
		String l=Integer.toString(this.id)+" : "+this.nom+"/  "+this.remarques;
		return l;
	}

}
