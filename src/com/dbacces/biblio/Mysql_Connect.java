package com.dbacces.biblio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Mysql_Connect {
	// URL de connexion
		private String url = "jdbc:mysql://localhost:3306/mediatheque";
		// Nom du user
		private String user = "root";
		// Mot de passe de l'utilisateur
		private String passwd = "root";
		// Objet Connection
		private static Connection connect;
		private static Mysql_Connect instance = new Mysql_Connect();

		// Constructeur priv�
		private Mysql_Connect() {
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// M�thode d'acc�s au singleton
		public static Connection getInstance() {
			if (connect == null)
				instance = new Mysql_Connect();

			return connect;
}
}
