package com.projetGL.model;

import java.util.List;

public class Colis {
	
	// Différents états possible du colis
	public enum Status {
		vide,
		Demi_Plein,
		Plein
	}
	
	//Region properties
	
	// ID du Colis
	public int Id;
	
	// Poids total du colis
	public int Poids;
	
	//Designation du Colis
	public String Designation;
	
	// Affectataire du Colis
	public String Affectataire;
	
	// Etat courant du colis
	public Status Etat;
	
	// Liste des mEdicaments contenus dans le colis
	public List<Medicament> ListeMedicaments;
	
	// Liste des outils contenus dans le colis
	public List<Outil> ListeOutils;
	
	// Liste des Objets contenus dans le colis
	public List<Objet> ListeObjets;
	
	//endregion properties
	
	// region Constructor
	
	// Constructeur par defauts
	public Colis()
	{ }
	
	//Constructeur prenant juste son id
	public Colis(int id)
	{ 
		Id = id;
	}
	
	// Constructeur initialisant toute ses données membres
	public Colis(int id, String designation, Status etat, int poids, String affectataire)
	{ 
		Id = id;
		Designation = designation;
		Etat = etat;
		Poids = poids;
		Affectataire = affectataire;
	}
	
	// endregion Constructor
	
	
}
