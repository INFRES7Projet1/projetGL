package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class ClasseTherapeutiqueDAO extends DAO<ClasseTherapeutique> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public ClasseTherapeutique find(int id){
		ClasseTherapeutique ct = null;
		try{
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			ResultSet resultat = statement.executeQuery();
			if (resultat.next())
			{
				ct = new ClasseTherapeutique(resultat.getInt("therapeutique_Id"));
			    ct.Designation = resultat.getString("therapeutique_Designation");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
		
	}
	
	public ClasseTherapeutique create(ClasseTherapeutique ct){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setInt(1, ct.Id);
			statement.setString(2, ct.Designation);
			ResultSet resultat = statement.executeQuery();
			
			ct = find(ct.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
		
	}
	
	public ClasseTherapeutique update(ClasseTherapeutique ct){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString( 1, ct.Designation);
			statement.setInt( 2, ct.Id);
			ResultSet resultat = statement.executeQuery();
			
			ct = find(ct.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	public void delete(ClasseTherapeutique ct){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, ct.Id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des ClasseTherapeutique
	public List<ClasseTherapeutique> findListe(){
		List<ClasseTherapeutique> result = new ArrayList<ClasseTherapeutique>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				
				// Insertion de la classe therapeutique à laquelle appartient le médicament
				ClasseTherapeutique ct = find(resultat.getInt("therapeutique_Id"));
				
			    result.add(i, ct);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// Options
	private static String _getListe = "SELECT therapeutique_Id FROM classe_therapeutique"; // TODO
	private static String _get = "SELECT * FROM classe_therapeutique WHERE therapeutique_Id = ?"; // TODO

	private static String _insert = "INSERT INTO classe_therapeutique (therapeutique_Id, therapeutique_Designation) VALUES ( ?, ?)";
	private static String _update = "UPDATE classe_therapeutique SET therapeutique_Designation = ? WHERE therapeutique_Id = ?";
	private static String _delete = "DELETE FROM classe_therapeutique WHERE therapeutique_Id = ?";
	
}