package com.projetGL.model;

import java.util.Date;


public class Outil {

	// region properties
	
	public int Id;
	
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
	
	public Outil(int id, int quantite, Date dlu, String ref){
		Id = id;
		Quantite = quantite;
		Dlu = dlu;
		Reference = ref;
	}
	
	// endregion Constructors
}
