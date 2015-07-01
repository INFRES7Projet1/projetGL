package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class ObjetDAO extends DAO<Objet> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public Objet find(int id){
		Objet obj = null;
		try {
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			ResultSet resultat = statement.executeQuery();
			 
			if (resultat.next())
			{
				obj = new Objet(resultat.getInt("objet_Id"));
				obj.Designation = resultat.getString("objet_Designation");
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public Objet create(Objet objet){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setString(1, objet.Designation);
			
			if(statement.executeUpdate() != 0)
				objet = find(objet.Id);
			else
				objet = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objet;
	}
	
	public Objet update(Objet objet){
			
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString( 1, objet.Designation);
			statement.setInt( 2, objet.Id);
			
			if(statement.executeUpdate() != 0)
				objet = find(objet.Id);
			else
				objet = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objet;
	}
	
	public void delete(Objet objet){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, objet.Id);

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// Delete la jointure liant un medicament à son colis
	public boolean DeleteObjetFromObjetColis(int obj_id){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteObjetFromObjet_Colis);
			statement.setInt(1, obj_id);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	//Retourne la liste des Objets
	public List<Objet> findListe(){
		List<Objet> result = new ArrayList<Objet>();
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0; 
			while ( resultat.next() ) {
				Objet obj = find(resultat.getInt("objet_Id"));
				
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
			
			int i = 0; 
			while ( resultat.next() ) {
				
				Objet obj = find(resultat.getInt("objet_Id"));
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
	private static String _get = "SELECT * FROM objet WHERE objet_Id = ?";
	private static String _getListe = "SELECT objet_Id FROM objet; ";
	private static String _getObjetsInColis = "SELECT colis_Id, O.objet_Id FROM objet O, objet_colis OC WHERE O.objet_Id = OC.objet_Id AND colis_Id = ?";
	
	private static String _insert = "INSERT INTO objet ( objet_Designation) VALUES ( ?)";
	
	private static String _update = "UPDATE objet SET objet_Designation = ? WHERE objet_Id = ? ";
	
	private static String _delete = "DELETE FROM objet WHERE objet_Id = ?";
	private static String _deleteObjetFromObjet_Colis = "DELETE FROM objet_colis WHERE objet_Id = ?";
	
}