package com.projetGL.controller;

import java.sql.ResultSet;
import java.util.List;

import com.projetGL.model.*;
import com.projetGL.vue.ObjetView;

public class ObjetController extends Controller<Objet>{

	private ObjetDAO maDAO = new ObjetDAO();
	private List<Objet> result;
	
	public ObjetController () {	
		
	}//Objet()
	
//	public ObjetController (Objet modele, ObjetView vue) {
//		this.modele = modele;
//		this.vue = vue;		
//	}//Objet()
	
	public List<Objet> returnListe () {
		result = maDAO.findListe();
		if (result.isEmpty()){
			System.out.println("Liste vide");
		} 
		//rentre dans la boucle, result n'est pas vide, mais les donnees ne s'affichent pas
		for (Objet co : result){
			System.out.println(co.toString());
		}		
		return result;	
		
	} //returnInfoObjet()
	
}

