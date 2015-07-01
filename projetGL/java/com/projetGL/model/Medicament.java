package com.projetGL.model;
import java.util.Date;

public class Medicament {
	
	// region properties
	
	public int Id;
	
	public String Produit;
	
	public int Quantite;
	
	public String FormeDosage;
	
	public String Lot;
	
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
	
	// function public
	
	public String toString(){
		return "Medicament : " + Id + ", " + Produit + "," + Quantite + "," + FormeDosage + "," + Lot + "," + Dlu + "," + Dotation + "," + Dci.toString() ; // TODO
	};
}
