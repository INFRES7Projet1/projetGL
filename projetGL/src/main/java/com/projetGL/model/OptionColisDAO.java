package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class OptionColisDAO extends DAO<OptionColis> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public OptionColis find(int id){
		OptionColis opt = null;
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getOption);
			statement.setInt(1, id);
			
			ResultSet resultat = statement.executeQuery();
			resultat.next();
			
			opt = new OptionColis(id,  resultat.getString("options_Designation"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return opt;
	}
	
	public OptionColis create(OptionColis op){
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insertOption);
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
			PreparedStatement statement = this.connect.prepareStatement(_updateOption);
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
			PreparedStatement statement = this.connect.prepareStatement(_deleteOption);
			statement.setInt(1, op.Id);
			
			ResultSet resultat = statement.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des Options
	public List<OptionColis> GetListeOptions(){
		List<OptionColis> result = new ArrayList<OptionColis>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListeOptions);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				OptionColis opt = new OptionColis(resultat.getInt("options_Id"),  resultat.getString("options_Designation"));
				result.add(i, opt);
				i++;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// Options
	private static String _getListeOptions = "SELECT * FROM options;";
	private static String _getOption = "SELECT options_Id, options_Designation FROM options WHERE options_Id = ?";

	private static String _insertOption= "INSERT INTO options (options_Id, options_Designation) VALUES ( ?, ?)";
	
	private static String _updateOption= "UPDATE option SET options_Designation = ? WHERE options_Id = ?";
	
	private static String _deleteOption= "DELETE FROM options WHERE options_Id = ?";
}