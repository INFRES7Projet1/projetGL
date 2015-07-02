package com.projetGL.controller;

import java.sql.ResultSet;
import java.util.List;

import com.projetGL.model.*;
import com.projetGL.vue.OutilView;

public class OutilController extends Controller<Outil>{

	private OutilDAO maDAO = new OutilDAO();
	private List<Outil> result;
	
	public OutilController () {	
		
	}//Outil()
	
//	public OutilController (Outil modele, OutilView vue) {
//		this.modele = modele;
//		this.vue = vue;		
//	}//Outil()
	
	public List<Outil> returnListe () {
		result = maDAO.findListe();
		if (result.isEmpty()){
			System.out.println("Liste vide");
		} 
		//rentre dans la boucle, result n'est pas vide, mais les donnees ne s'affichent pas
		for (Outil ou : result){
			System.out.println(ou.toString());
		}		
		return result;	
		
	} //returnInfoOutil()
	
}

