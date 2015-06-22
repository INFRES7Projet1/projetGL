package com.projetGL.model;

public class OptionColis {
	
	// region properties
	
	public int Id;
	
	public String Designation;
	
	// endregion properties
	
	
	// region Constructors
	
	public OptionColis(){}
	
	public OptionColis(int id){}
	
	public OptionColis(int id, String designation){
		Id = id;
		Designation = designation;
	}
	
	// endregion Constructors
}
