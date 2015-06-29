package com.projetGL.model;

import java.util.Date;


public class Outil {

	// region properties
	
	public int Id;
	
	public String Designation;
	
	public int Quantite;
	
	public Date Dlu;
	
	public String Reference;
	
	// endregion properties
	
	
	// region Constructors
	
	public Outil(){
		
	}
	
	public Outil(int id){
		Id = id;
	}
	
	public Outil(int id, String outil_Designation, int quantite, Date dlu, String ref){
		Id = id;
		Designation = outil_Designation;
		Quantite = quantite;
		Dlu = dlu;
		Reference = ref;
	}
	
	// endregion Constructors
	
	// public methods
	public String toString(){
		return "Outil : " + Id + ", " + Designation + ", " + Quantite + ", " + Dlu + ", " + Reference;
	};
}
