package com.projetGL.model;

public class TypeColis {

	// region properties
	
	public int Id ;
	
	public String Designation;
	
	public int Hauteur;
	
	public int Largeur;
	
	public int Longueur;
	
	// region properties
	
	
	// region Constructors
	
	public TypeColis(){}
	
	public TypeColis(int id){
		Id = id;
	}
	
	public TypeColis(int id, String designation, int h, int largeur, int longueur){
		Id = id;
		Designation = designation;
		Hauteur = h;
		Largeur = largeur;
		Longueur = longueur;
	}
	
	// endregion Constructors
	
	
	// region public Function
	
	public String toString(){
		return "(Type Colis : " + Id + ", " + Designation + ", " + GetDimension() + ")";
	}
	
	public String GetDimension(){
		return  Longueur + "x" + Largeur + "x" + Hauteur ;
	}
	
	public void SetDimension(int L, int l, int h){
		Longueur = L;
		Largeur = l;
		Hauteur = h;
	}
	
	// region public Function
}
