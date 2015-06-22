package com.projetGL.model;
import java.util.Date;

public class Medicament {
	
	// region properties
	
	public int Id;
	
	public int quantite;
	
	public String FormeDosage;
	
	public String lot;
	
	public Date Dlu;
	
	public String Dotation;
	
	public DCI Dci;
	
	
	// enregion properties
	
	
	// region Constructors
	
	public Medicament(){
	}
	
	public Medicament(int id){
		Id = id;
	}
	
	// endregion Constructors
}
