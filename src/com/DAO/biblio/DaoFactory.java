package com.DAO.biblio;

import com.dbacces.biblio.Mysql_Connect;

public abstract class DaoFactory {

// il y a quelque chose qui merde là dedans. Je ne vais pas utiliserpour le moment
	protected abstract AuteurGenreDAO getAuteurGenreDAO();
	protected abstract AuteurDAO getAuteurDAO();
	protected abstract AuteurLangueDAO getAuteurLangueDAO();
	protected abstract GenreDAO getGenreDAO();
	protected abstract LangueDAO getLangueDAO();
	protected abstract LivreDAO getLivreDAO();
	protected abstract LivreAuteurDAO getGLivreAuteurDAO();
	protected abstract PaysDAO getPaysDAO();
	protected abstract ResumeLivreDAO getResumeLivreDAO();
	 
	 
}
