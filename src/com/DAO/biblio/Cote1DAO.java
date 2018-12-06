package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.DAO.biblio.DAO;
import com.DAO.biblio.DAO_Noms;
import com.DAO.biblio.OptionRecherche;
import com.metier.biblio.Cote1;

public class Cote1DAO  extends DAO<Cote1> implements DAO_Noms<Cote1>
{

	public Cote1DAO(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(Cote1 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cote1 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cote1 obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cote1 findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cote1> selectAll() {
		// TODO Auto-generated method stub
		return null;
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

}

