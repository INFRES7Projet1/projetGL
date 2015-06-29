package com.projetGL.model;

public class DesignationGenerique {
	// region properties
	
		public int Id;
		
		public String Designation;
		
		// endregion properties
		
		
		// region Constructors
		
		public DesignationGenerique(){
			
		}
		
		public DesignationGenerique(int id){
			Id = id;
		}
		
		public DesignationGenerique(int id, String designation){
			Id = id;
			Designation = designation;
		}
		
		// endregion Constructors
		
		// region public methods
		
		public String toString(){
			return "(D.G : " + Id + ", " + Designation + " )";
		}
}
