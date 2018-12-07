package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.Cote4;

public class Cote4DAO extends DAO<Cote4> implements DAO_Noms<Cote4>{

	public Cote4DAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getByNom(String n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNomLike(String n, OptionRecherche opr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Cote4 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cote4 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cote4 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cote4 findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cote4> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
