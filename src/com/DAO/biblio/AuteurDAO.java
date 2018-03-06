package com.DAO.biblio;

import java.sql.Connection;
import java.util.List;

import com.metier.biblio.Auteur;

public class AuteurDAO  extends DAO<Auteur> implements DAO_Noms<Auteur>, DAO_Noms_Prenoms <Auteur>{

	public AuteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Auteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Auteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Auteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Auteur findId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Auteur> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNom(String n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByNomPrenom(String n, String p) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
