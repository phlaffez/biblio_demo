package com.DAO.biblio;

import com.dbacces.biblio.Mysql_Connect;

public class DaoFactoryMySQL  {

	public DaoFactoryMySQL() {
		// TODO Auto-generated constructor stub
	}

	public AuteurGenreDAO getAuteurGenreDAO()
    {
	  return new AuteurGenreDAO(Mysql_Connect.getInstance());
   }
 
 public static AuteurDAO getAuteurDAO()
    {
	  return new AuteurDAO(Mysql_Connect.getInstance());
   }
 
 public AuteurLangueDAO getAuteurLangueDAO()
    {
	  return new AuteurLangueDAO(Mysql_Connect.getInstance());
   }
 
 public GenreDAO getGenreDAO()
    {
	  return new GenreDAO(Mysql_Connect.getInstance());
   }
 
 public LangueDAO getLangueDAO()
    {
	  return new LangueDAO(Mysql_Connect.getInstance());
   }
 
 public LivreDAO getLivreDAO()
    {
	  return new LivreDAO(Mysql_Connect.getInstance());
   } 
 
 public LivreAuteurDAO getGLivreAuteurDAO()
    {
	  return new LivreAuteurDAO(Mysql_Connect.getInstance());
   }


 
 public ResumeLivreDAO getResumeLivreDAO()
    {
	  return new ResumeLivreDAO(Mysql_Connect.getInstance());
   }

public static  PaysDAO getPaysDAO() {
	// je ne comprend pas l'histoire du static ? Si ça ne marche pas il faudra
	// faire sans la factory!
	  return new PaysDAO(Mysql_Connect.getInstance());
}
}
