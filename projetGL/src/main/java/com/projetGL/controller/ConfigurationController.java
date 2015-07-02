package com.projetGL.controller;

import java.sql.ResultSet;
import java.util.List;

import com.projetGL.model.*;
import com.projetGL.vue.ConfigurationView;

public class ConfigurationController extends Controller<ConfigurationColis>{

	private ConfigurationColisDAO maDAO = new ConfigurationColisDAO();
	private List<ConfigurationColis> result;
	
	public ConfigurationController () {	
		
	}
	
	public List<ConfigurationColis> returnListe () {
		result = maDAO.GetListeConfiguration();
		if (result.isEmpty()){
			System.out.println("Liste vide");
		} 
		//rentre dans la boucle, result n'est pas vide, mais les donnees ne s'affichent pas
		for (ConfigurationColis cc : result){
			System.out.println(cc.toString());
		}		
		return result;	
		
	} //returnInfoOutil()
	
}

