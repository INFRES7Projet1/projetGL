package com.projetGL.model;

public class DCI {

	// region properties
	
	public int Id;
	
	// Desgination du DCI
	public String Designation;
	
	// Classe Therapeutique à laquelle appartient le DCI
	public ClasseTherapeutique ClasseT;
	
	// endregion properties
	
	
	// region Constructors
	
	public DCI(){
		
	}
	
	public DCI(int id){
		Id = id;
	}
	
	public DCI(int id, String designation){
		Id = id;
		Designation = designation;
	}
	
	public DCI(int id, String designation, ClasseTherapeutique classe){
		Id = id;
		Designation = designation;
		ClasseT = classe;
	}
	
	// endregion Constructors
	
	//Public methods
	public String toString(){
		return "( Dci : " + Id + ", " + Designation + "," + ClasseT.toString() + ")";
	};
}
