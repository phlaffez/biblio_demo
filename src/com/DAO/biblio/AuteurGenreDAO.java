package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.AuteurGenre;
import com.outils.biblio.Cles;

public class AuteurGenreDAO extends DAO<AuteurGenre> implements DAO_Liaison<AuteurGenre>
{

	public AuteurGenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(AuteurGenre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(AuteurGenre obj) {
		// N'est pas  implémenter
		return false;
	}

	@Override
	public boolean delete(AuteurGenre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AuteurGenre findId(int id) {
		// N'est pas  implémenter
		return null;
	}

	@Override
	public int lastId() {
		// N'est pas  implémenter
		return 0;
	}

	@Override
	public List<AuteurGenre> selectAll() {
		// N'est pas  implémenter
		return null;
	}

	@Override
	public Object getByCleLiaison(Cles cle, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPresent(AuteurGenre obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
