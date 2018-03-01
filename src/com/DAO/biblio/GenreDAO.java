package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.Genre;

public class GenreDAO  extends DAO<Genre> implements DAO_Noms<Genre>{
	
	

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Genre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Genre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Genre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Genre findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Genre> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNom(String n) {
		// TODO Auto-generated method stub
		return null;
	}

}
