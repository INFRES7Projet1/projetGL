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
			PreparedStatement statement = this.connect.prepareStatement(_insertConfiguration);
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
	
	
	//Retourne le contenu de tout une configuration en fonction de son id
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
				result.ListeColis.add(i, new ColisDAO().find(resultat.getInt("colis_Id")));
			    i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne la liste des configurations
	public List<ConfigurationColis> GetListeConfiguration(){

		List<ConfigurationColis> result = new ArrayList<ConfigurationColis>();
		int i = 0;
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListeConfiguration);
			
			ResultSet resultat = statement.executeQuery();
			
			while ( resultat.next() ) {
				ConfigurationColis conf = new ConfigurationColis(resultat.getInt("config_Id"));
				conf.Designation = resultat.getString("config_Designation");
				    
			    result.add(i, conf);
			    i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne la liste de toute les Configurations avec la liste des Colis
	public List<ConfigurationColis> GetAllConfiguration()
	{
		List<ConfigurationColis> result = GetListeConfiguration();
		
		//int i=0;
		for(ConfigurationColis conf : result){
			ConfigurationColis c = find(conf.Id);
			
			if (c != null){
				result.set( conf.Id, c);
				//i++;
			}
		}
		
		return result;
	}
	
	public ConfigurationColis update(ConfigurationColis conf) {
		ConfigurationColis co = null;
		try {
			PreparedStatement statement = this.connect.prepareStatement(_updateConfiguration);
			statement.setString(1, conf.Designation);
			statement.setInt(2, conf.Id);
			
			ResultSet resultat = statement.executeQuery();
			
			co = find(co.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}


	public void delete(ConfigurationColis conf) {

		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteConfiguration);
			statement.setInt(1, conf.Id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
