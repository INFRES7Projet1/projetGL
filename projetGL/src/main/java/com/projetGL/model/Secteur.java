package com.projetGL.model;

public class Secteur {
	// region properties

	public int Id;
	
	public String Designation;
	
	public DesignationGenerique DesignationGenerique;
	
	// endregion properties
	
	
	// region Constructors
	
	public Secteur(){
		
	}
	
	public Secteur(int id){
		Id = id;
	}
	
	public Secteur(int id, String designation){
		Id = id;
		Designation = designation;
	}
	
	public Secteur(int id, String designation, DesignationGenerique designationG){
		Id = id;
		Designation = designation;
		DesignationGenerique = designationG;
	}
	
	// endregion Constructors
}
