package com.projetGL.controller;

import java.sql.ResultSet;
import java.util.List;

import com.projetGL.model.*;
import com.projetGL.vue.MedicamentView;

public class MedicamentController extends Controller<Medicament>{

	private MedicamentDAO maDAO = new MedicamentDAO();
	private List<Medicament> result;
	
	public MedicamentController () {	
		
	}//Medicament()
	
//	public MedicamentController (Medicament modele, MedicamentView vue) {
//		this.modele = modele;
//		this.vue = vue;		
//	}//Medicament()
	
	public List<Medicament> returnListe () {
		result = maDAO.findListe();
		if (result.isEmpty()){
			System.out.println("Liste vide");
		} 
		//rentre dans la boucle, result n'est pas vide, mais les donnees ne s'affichent pas
		for (Medicament co : result){
			System.out.println(co.toString());
		}		
		return result;	
		
	} //returnInfoMedicament()
	
}

