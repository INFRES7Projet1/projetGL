package com.projetGL.model;

public class ClasseTherapeutique {
	
	// region properties
	
	public int Id;
	
	public String Designation;
	
	// endregion properties
	
	
	// region Constructors
	
	public ClasseTherapeutique(){
		
	}
	
	public ClasseTherapeutique(int id){
		Id = id;
	}
	
	public ClasseTherapeutique(int id, String designation){
		Id = id;
		Designation = designation;
	}
	
	// endregion Constructors
	
	public String toString(){
		return "( ClasseT : " + Id + ", " + Designation + ")";
	};
}
