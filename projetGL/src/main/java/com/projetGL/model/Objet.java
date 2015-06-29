package com.projetGL.model;

public class Objet {

	// region properties

	public int Id;
	
	public String Designation;
	
	
	// endregion properties
	
	
	// region Constructors
	
	public Objet(){
	}
	
	public Objet(int id){
		Id = id;
	}
	
	public Objet(int id, String designation){
		Id = id;
		Designation = designation;
	}
	
	// public methods
	public String toString(){
		return "Objet : " + Id + ", " + Designation;
	};
}
