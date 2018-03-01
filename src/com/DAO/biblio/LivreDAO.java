package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.Livre;

public class LivreDAO  extends DAO<Livre> implements DAO_Noms<Livre>{

	public LivreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Livre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Livre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Livre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Livre findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Livre> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNom(String n) {
		// TODO Auto-generated method stub
		return null;
	}


}
