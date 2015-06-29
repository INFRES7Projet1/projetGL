package com.projetGL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL{

	/**
	 * URL de connection
	 */
	private static String url = "jdbc:mysql://127.0.0.1:3306/db_escrim";
	/**
	 * Nom du user
	 */
	
	private static String user = "EscrimUser";
	/**
	 * Mot de passe du user
	 */
	private static String passwd = "EscrimPassword";
	/**
	 * Objet Connection
	 */
	private static Connection connect;
	
	/**
	 * Méthode qui va nous retourner notre instance
	 * et la créer si elle n'existe pas...
	 * @return
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}	
}