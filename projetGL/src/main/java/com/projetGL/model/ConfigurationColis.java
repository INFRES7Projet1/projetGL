package com.projetGL.model;

import java.util.List;

public class ConfigurationColis {
	
	//region properties
	
	public int Id;
	public String Designation;
	public List<Colis> ListeColis;
	
	//endregion properties
	
	
	//region Constructors
	
	// Par Defauts
	public ConfigurationColis(){
	}
	
	public ConfigurationColis(int id){
		Id = id;
	}
	
	public ConfigurationColis(int id, String designation){
		Id = id;
		Designation = designation;
	}
	
	public ConfigurationColis(int id, String designation, List<Colis> liste){
		Id = id;
		Designation = designation;
		ListeColis = liste;
	}
	
	// endregion Constructors
	
	
}
