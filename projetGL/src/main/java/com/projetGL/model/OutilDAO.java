package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class OutilDAO extends DAO<Outil> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public Outil find(int id){
		Outil outil = null;
		try {
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			ResultSet resultat = statement.executeQuery();
			 
			if (resultat.next())
			{
				outil = new Outil(resultat.getInt("outil_Id"));
				outil.Designation = resultat.getString("outil_Designation");
				outil.Quantite = resultat.getInt("quantite");
				outil.Dlu = resultat.getDate("dlu");
				outil.Reference = resultat.getString("reference");
			}
				    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outil;
	}
	
	public Outil create(Outil outil){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setInt(1, outil.Id);
			statement.setString(2, outil.Designation);
			statement.setInt(3, outil.Quantite);
			statement.setDate(4, (java.sql.Date)outil.Dlu);
			statement.setString(5, outil.Reference);
			ResultSet resultat = statement.executeQuery();
			
			outil = find(outil.Id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outil;
	}
	
	public Outil update(Outil outil){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString(2, outil.Designation);
			statement.setInt(3, outil.Quantite);
			statement.setDate(4, (java.sql.Date)outil.Dlu);
			statement.setString(5, outil.Reference);
			statement.setInt(1, outil.Id);
			
			ResultSet resultat = statement.executeQuery();
			
			outil = find(outil.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outil;
	}
	
	public void delete(Outil outil){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, outil.Id);
			
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Delete la jointure liant un medicament à son colis
	public boolean DeleteOutilFromOutilColis(int outil_id){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteOutilFromOutil_Colis);
			statement.setInt(1, outil_id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//Retourne la liste des Outils
	public List<Outil> findListe(){
		List<Outil> result = new ArrayList<Outil>();
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0; 
			while ( resultat.next() ) {
				Outil outil = find(resultat.getInt("outil_Id"));
				
			    result.add(i, outil);
			    i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//Retourne la liste de tous les outils contenu dans un colis
	public List<Outil> GetOutilsInColis(int colis_id){
		
		List<Outil> result = new ArrayList<Outil>();
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getOutilsInColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0; 
			while ( resultat.next() ) {
				Outil outil = find(resultat.getInt("outil_Id"));
				
			    result.add(i, outil);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//Outils 
	private static String _get = "SELECT * FROM outil WHERE outil_Id";
	private static String _getListe = "SELECT * FROM outil;";
	private static String _getOutilsInColis = "SELECT colis_Id, O.outil_Id FROM outil O, outil_colis OC WHERE O.outil_Id = OC.outil_Id AND colis_Id = ?";
	
	private static String _insert = "INSERT INTO outil (outil_Id, outil_Designation, quantite, dlu, reference) VALUES (?, ?, ?, ?, ?)";
	private static String _update = "UPDATE outil SET outil_Designation = ?, quantite = ?, dlu = ?, reference = ? WHERE outil_Id = ?";
	private static String _delete = "DELETE FROM outil WHERE outil_Id = ?";
	private static String _deleteOutilFromOutil_Colis = "DELETE FROM outil_colis WHERE outil_Id = ?";
	
}