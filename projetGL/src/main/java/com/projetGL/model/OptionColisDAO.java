package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class OptionColisDAO extends DAO<OptionColis> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public OptionColis find(int id){
		OptionColis opt = null;
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			
			ResultSet resultat = statement.executeQuery();
			if (resultat.next())
				opt = new OptionColis(id,  resultat.getString("options_Designation"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return opt;
	}
	
	public OptionColis create(OptionColis op){
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setInt(1, op.Id);
			statement.setString(2, op.Designation);
			ResultSet resultat = statement.executeQuery();
			
			op = find(op.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}
	
	public OptionColis update(OptionColis op){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString(1, op.Designation);
			statement.setInt(2, op.Id);
			ResultSet resultat = statement.executeQuery();
			
			op = find(op.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}
	
	public void delete(OptionColis op){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, op.Id);
			
			ResultSet resultat = statement.executeQuery();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Retourne la liste des Options
	public List<OptionColis> findListe(){
		List<OptionColis> result = new ArrayList<OptionColis>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				OptionColis opt = find(resultat.getInt("options_Id"));
				result.add(i, opt);
				i++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// Options
	private static String _getListe = "SELECT options_Id FROM options;";
	private static String _get = "SELECT options_Id, options_Designation FROM options WHERE options_Id = ?";

	private static String _insert = "INSERT INTO options (options_Id, options_Designation) VALUES ( ?, ?)";
	private static String _update = "UPDATE option SET options_Designation = ? WHERE options_Id = ?";
	private static String _delete = "DELETE FROM options WHERE options_Id = ?";
}