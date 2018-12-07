package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.Cote3;

public class Cote3DAO extends DAO<Cote3> implements DAO_Noms<Cote3>{

	public Cote3DAO(Connection conn) {
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
	public boolean create(Cote3 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cote3 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cote3 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cote3 findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cote3> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}


