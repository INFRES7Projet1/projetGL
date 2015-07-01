package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class SecteurDAO extends DAO<Secteur> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public Secteur find(int id){
		Secteur sec = null;
		
		try{
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			ResultSet resultat = statement.executeQuery();
			if (resultat.next())
			{
				sec = new Secteur(resultat.getInt("secteur_Id"));
			    sec.Designation = resultat.getString("secteur_Designation");
			    sec.DesignationGenerique = new DesignationGeneriqueDAO().find(resultat.getInt("Dgenerique_Id"));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sec;
		
	}
	
	public Secteur create(Secteur sec){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setString(1, sec.Designation);
			statement.setInt(2, sec.DesignationGenerique.Id);
			
			if(statement.executeUpdate() != 0)
				sec = find(sec.Id);
			else
				sec = null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sec;
	}
	
	public Secteur update(Secteur sec){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString(1, sec.Designation);
			statement.setInt(2, sec.DesignationGenerique.Id);
			statement.setInt(3, sec.Id);
			
			if(statement.executeUpdate() != 0)
				sec = find(sec.Id);
			else
				sec = null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sec;
	}
	
	public void delete(Secteur sec){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, sec.Id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Retourne la liste des Secteur
	public List<Secteur> findListe(){
		List<Secteur> result = new ArrayList<Secteur>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				
				// Insertion de la classe therapeutique à laquelle appartient le médicament
				Secteur sec = find(resultat.getInt("secteur_Id"));
				
			    result.add(i, sec);
			    i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// Options
	private static String _getListe = "SELECT secteur_Id FROM secteur"; 		
	private static String _get = "SELECT * FROM secteur WHERE secteur_Id = ?"; 	

	private static String _insert = "INSERT INTO secteur ( secteur_Designation, Dgenerique_Id) VALUES ( ?, ?)";
	private static String _update = "UPDATE secteur SET secteur_Designation = ?, Dgenerique_Id = ? WHERE secteur_Id = ?";
	private static String _delete = "DELETE FROM secteur WHERE secteur_Id = ?";
	
	
}