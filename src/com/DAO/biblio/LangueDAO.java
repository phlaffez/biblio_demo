package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.Langue;

public class LangueDAO extends DAO<Langue> implements DAO_Noms<Langue>{

	public LangueDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Langue obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Langue obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Langue obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Langue findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Langue> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNom(String n) {
		// Retourne un objet "Langue"
		return null;
	}




}
