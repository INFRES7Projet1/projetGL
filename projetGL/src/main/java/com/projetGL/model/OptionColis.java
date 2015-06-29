package com.projetGL.model;

public class OptionColis {
	
	// region properties
	
	public int Id;
	
	public String Designation;
	
	// endregion properties
	
	
	// region Constructors
	
	public OptionColis(){}
	
	public OptionColis(int id){
		Id =id;
	}
	
	public OptionColis(int id, String designation){
		Id = id;
		Designation = designation;
	}
	
	// endregion Constructors
	
	// region public methods
	
	public String toString(){
		return "( Option : " + Id + ", " + Designation + ")";
	};
}
