package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.LivreAuteur;
import com.outils.biblio.Cles;

public class LivreAuteurDAO extends DAO<LivreAuteur> implements DAO_Liaison<LivreAuteur>
{
 
	public LivreAuteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(LivreAuteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(LivreAuteur obj) {
		// N'est pas  implémenter
		return false;
	}

	@Override
	public boolean delete(LivreAuteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LivreAuteur findId(int id) {
		// N'est pas  implémenter
		return null;
	}

	@Override
	public int lastId() {
		// N'est pas  implémenter
		return 0;
	}

	@Override
	public List<LivreAuteur> selectAll() {
		// N'est pas  implémenter
		return null;
	}

	@Override
	public Object getByCleLiaison(Cles cle, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPresent(LivreAuteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
