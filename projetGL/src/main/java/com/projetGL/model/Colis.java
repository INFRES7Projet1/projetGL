package com.projetGL.model;

import java.util.ArrayList;
import java.util.List;

public class Colis {
	
	// Différents états possible du colis
	public enum Status {
		vide,
		demi_plein,
		plein
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
	public String Etat;
	
	// Option du colis
	public OptionColis Option;
	
	// Option du colis
	public TypeColis Type;
	
	// Liste des mEdicaments contenus dans le colis
	public List<Medicament> ListeMedicaments;
	
	// Liste des outils contenus dans le colis
	public List<Outil> ListeOutils;
	
	// Liste des Objets contenus dans le colis
	public List<Objet> ListeObjets;
	
	// Secteur d'utilisation du colis
	public Secteur SecteurUtilisation;
	
	//endregion properties
	
	
	// region Constructor
	
	// Constructeur par defauts
	public Colis()
	{ 
		ListeMedicaments = new ArrayList<Medicament>(); 
		ListeOutils = new ArrayList<Outil>();
		ListeObjets = new ArrayList<Objet>();
	}
	
	//Constructeur prenant juste son id
	public Colis(int id)
	{ 
		Id = id;
		ListeMedicaments = new ArrayList<Medicament>(); 
		ListeOutils = new ArrayList<Outil>();
		ListeObjets = new ArrayList<Objet>();
	}
	
	// Constructeur initialisant toute ses données membres
	public Colis(int id, String designation, String etat, int poids, String affectataire, OptionColis option, TypeColis type, Secteur secteur, List<Medicament> listeMedicaments, List<Outil> listeOutils, List<Objet> listeObjets)
	{ 
		Id = id;
		Designation = designation;
		Etat = etat;
		Poids = poids;
		Affectataire = affectataire;
		Option = option;
		Type = type;
		ListeMedicaments = listeMedicaments;
		ListeOutils = listeOutils;
		ListeObjets = listeObjets;
		SecteurUtilisation = secteur;
	}
	
	// endregion Constructor
	
	
	// region public methods
	
	public String toString(){
		String ret = "Colis : " + Id + ", " + Designation + ", " + Etat + ", " + Poids + ", " + Affectataire + ", " + Option.toString() + ", " + Type.toString() + ", " + SecteurUtilisation.toString() +"\n";
		
		if(ListeMedicaments != null){
			
			for(Medicament med : ListeMedicaments){
				ret += med.toString();
				ret += "\n";
			}
		}
		
		if(ListeOutils != null){
			for(Outil outil : ListeOutils){
				ret += outil.toString();
				ret += "\n";
			}
		}
		
		if(ListeObjets != null){
			for(Objet obj : ListeObjets){
				ret += obj.toString();
				ret += "\n";
			}
		}
		
		return ret; // TODO
	};
	
	// endregion public methods
	
	
}
