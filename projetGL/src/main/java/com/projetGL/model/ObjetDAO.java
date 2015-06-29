package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class ObjetDAO extends DAO<Objet> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public Objet find(int id){
		//TODO
	}
	
	public Objet create(Objet objet){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insertObjet);
			statement.setInt(1, objet.Id);
			statement.setString(2, objet.Designation);
			ResultSet resultat = statement.executeQuery();
			
			objet = find(objet.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objet;
	}
	
	public Objet update(Objet objet){
			
		try {
			PreparedStatement statement = this.connect.prepareStatement(_updateObjet);
			statement.setString( 1, objet.Designation);
			statement.setInt( 2, objet.Id);
			
			ResultSet resultat = statement.executeQuery();
			
			objet = find(objet.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objet;
	}
	
	public void delete(Objet objet){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteObjet);
			statement.setInt(1, objet.Id);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Retourne la liste des Objets
	public List<Objet> GetListeObjets(){
		List<Objet> result = new ArrayList<Objet>();
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListeObjets);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0; 
			while ( resultat.next() ) {
				Objet obj = new Objet(resultat.getInt("objet_Id"));
				obj.Designation = resultat.getString("objet_Designation");
				    
			    result.add(i, obj);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//Retourne la liste des objets d'un colis
	public List<Objet> GetObjetsInColis(int colis_id){
		List<Objet> result = new ArrayList<Objet>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getObjetsInColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
			/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
			
			int i = 0; 
			while ( resultat.next() ) {
				
				Objet obj = new Objet(resultat.getInt("objet_Id"));
				
				obj.Designation = resultat.getString("objet_Designation");
				    
			    result.add(i, obj);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	//Objet
	private static String _getListeObjets = "SELECT * FROM objet; ";
	private static String _getObjetsInColis = "SELECT colis_Id, O.objet_Id, objet_Designation FROM objet O, objet_colis OC WHERE O.objet_Id = OC.objet_Id AND colis_Id = ?";
	
	private static String _insertObjet = "INSERT INTO objet (objet_Id, objet_Designation) VALUES ( ?, ?)";
	
	private static String _updateObjet = "UPDATE objet SET objet_Designation = ? WHERE objet_Id = ? ";
	
	private static String _deleteObjet = "DELETE FROM objet WHERE objet_Id = ?";
	
}