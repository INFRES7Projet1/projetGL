package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class ClasseTherapeutiqueDAO extends DAO<ClasseTherapeutique> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public ClasseTherapeutique find(int id){
		//TODO
	}
	
	public ClasseTherapeutique create(ClasseTherapeutique ct){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insertClasseTherapeutique);
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
			PreparedStatement statement = this.connect.prepareStatement(_updateClasseTherapeutique);
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
			PreparedStatement statement = this.connect.prepareStatement(_deleteClasseTherapeutique);
			statement.setInt(1, ct.Id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des Options
	public List<ClasseTherapeutique> GetListeClasseTherapeutique(){
		//TODO
	}

	// Options
	private static String _getListeClasseTherapeutique = ""; // TODO
	private static String _getClasseTherapeutique = ""; // TODO

	private static String _insertClasseTherapeutique = "INSERT INTO classe_therapeutique (therapeutique_Id, therapeutique_Designation) VALUES ( ?, ?)";
	private static String _updateClasseTherapeutique = "UPDATE classe_therapeutique SET therapeutique_Designation = ? WHERE therapeutique_Id = ?";
	private static String _deleteClasseTherapeutique = "DELETE FROM classe_therapeutique WHERE therapeutique_Id = ?";
	
}