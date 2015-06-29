package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class DCIDAO extends DAO<DCI> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public DCI find(int id){
		//TODO
	}
	
	public DCI create(DCI dci){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_insertDCI);
			statement.setInt(1, dci.Id);
			statement.setString(2, dci.Designation);
			statement.setInt(3, dci.ClasseT.Id);
			ResultSet resultat = statement.executeQuery();
			
			dci = find(dci.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dci;
	}
	
	public DCI update(DCI dci){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_updateDCI);
			statement.setString(1, dci.Designation);
			statement.setInt(2, dci.ClasseT.Id);
			statement.setInt(3, dci.Id);
			
			ResultSet resultat = statement.executeQuery();
			
			dci = find(dci.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dci;
	}
	
	public void delete(DCI dci){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteDCI);
			statement.setInt(1, dci.Id);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des Options
	public List<DCI> GetListeDCI(){
		//TODO
	}

	// Options
	private static String _getListeOptions = "SELECT * FROM options;";
	private static String _getDci = "SELECT * FROM dci WHERE dci_Id = ?";

	private static String _insertDCI = "INSERT INTO dci (dci_Id, dci_Designation, therapeutique_Id) VALUES ( ?, ?, ?)";
	
	private static String _updateDCI = "UPDATE dci SET dci_Designation = ?, therapeutique_Id = ? WHERE dci_Id = ?";
	
	private static String _deleteDCI = "DELETE FROM dci WHERE dci_Id = ?";
}