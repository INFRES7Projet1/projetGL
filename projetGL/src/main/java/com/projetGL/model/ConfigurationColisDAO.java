package com.projetGL.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationColisDAO extends DAO<ConfigurationColis> {

	public ConfigurationColis create(ConfigurationColis conf) {
		try {
			PreparedStatement statement = connexion.prepareStatement(_insertConfiguration);
			statement.setInt(1, conf.Id);
			statement.setString(2, conf.Designation);
			
			ResultSet resultat = statement.executeQuery();
			
			conf = find(conf.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}
	
	
	//Retourne le contenu de tout un colis en fonction de son id
	public ConfigurationColis find(int conf_id){
		ConfigurationColis result = null;
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getConfigurationContent);
			statement.setInt(1, conf_id);
			ResultSet resultat = statement.executeQuery();
			
			
			int i = 0; 
			while ( resultat.next() ) {
				if ( result == null) {
					result = new ConfigurationColis(resultat.getInt("config_Id"));
					result.Designation = resultat.getString("config_Designation");
					result.ListeColis = new ArrayList<Colis>();
				}
				result.ListeColis.add(i, ColisDAO.GetColisContent(resultat.getInt("colis_Id")));
			    i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne le contenu de tous les colis
	public List<Colis> GetAllColis(){
		
		return result;
	}
	
	public ConfigurationColis update(ConfigurationColis colis) {
		Colis co = null;
		try {
			PreparedStatement statement = this.connect.prepareStatement(_updateConfiguration);
			statement.setString(1, conf.Designation);
			statement.setInt(2, conf.Id);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public void delete(Colis colis) {
		
	}
	
	// Configuration
	private static String _getListeConfiguration = "SELECT * FROM configuration;";
	private static String _getConfigurationContent = "SELECT CC.config_Id, CO.config_Designation, C.colis_Id FROM configuration CO, configuration_colis CC, colis C WHERE CC.colis_Id = C.colis_Id AND CO.config_Id = CC.config_Id AND CC.config_Id = ?;";
		
		
	//Insert Colis
	private static String _insertConfiguration= "INSERT INTO configuration (config_Id, config_Designation) VALUES ( ?, ?)";
	
	// Update Colis
	private static String _updateConfiguration = "UPDATE configuration SET config_Designation = ? WHERE config_Id = ?";
	
	// Delete Colis
	private static String _deleteConfiguration= "DELETE FROM configuration WHERE config_Id = ?";
	
}
