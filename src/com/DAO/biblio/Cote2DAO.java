package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.Cote2;

public class Cote2DAO extends DAO<Cote2> implements DAO_Noms<Cote2>
{

	public Cote2DAO(Connection conn) {
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
	public boolean create(Cote2 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cote2 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cote2 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cote2 findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cote2> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
