package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class DesignationGeneriqueDAO extends DAO<DesignationGenerique> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public DesignationGenerique find(int id){
		DesignationGenerique dg = null;
		
		try{
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			ResultSet resultat = statement.executeQuery();
			
			if (resultat.next())
			{
				dg = new DesignationGenerique(resultat.getInt("Dgenerique_Id"));
				dg.Designation = resultat.getString("Dgenerique_Designation");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dg;
		
	}
	
	public DesignationGenerique create(DesignationGenerique dg){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setInt(1, dg.Id);
			statement.setString(2, dg.Designation);
			ResultSet resultat = statement.executeQuery();
			
			dg = find(dg.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dg;
		
	}
	
	public DesignationGenerique update(DesignationGenerique dg){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString(1, dg.Designation);
			statement.setInt(2, dg.Id);
			
			ResultSet resultat = statement.executeQuery();
			
			dg = find(dg.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dg;
	}
	
	public void delete(DesignationGenerique dg){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, dg.Id);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des DesignationGenerique
	public List<DesignationGenerique> findListe(){
		List<DesignationGenerique> result = new ArrayList<DesignationGenerique>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				
				// Insertion de la classe therapeutique à laquelle appartient le médicament
				DesignationGenerique dg = find(resultat.getInt("Dgenerique_Id"));
				
			    result.add(i, dg);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// Options
	private static String _getListe = "SELECT Dgenerique_Id FROM designation_generique"; // TODO
	private static String _get = "SELECT * FROM designation_generique WHERE Dgenerique_Id = ?"; // TODO

	private static String _insert = "INSERT INTO designation_generique (Dgenerique_Id, Dgenerique_Designation) VALUES ( ?, ?)";
	private static String _update = "UPDATE configuration SET Dgenerique_Designation = ? WHERE Dgenerique_Id = ?";
	private static String _delete = "DELETE FROM designation_generique WHERE Dgenerique_Id = ?";
	
	
}