package com.projetGL.controller;

import java.sql.ResultSet;
import java.util.List;

import com.projetGL.model.*;
import com.projetGL.vue.ColisView;

public class ColisController  extends Controller<Colis>{

	private ColisDAO maDAO = new ColisDAO();
	private List<Colis> result;
	
	public ColisController () {	
		
	}//Colis()
	
//	public ColisController (Colis modele, ColisView vue) {
//		this.modele = modele;
//		this.vue = vue;		
//	}//Colis()
	
	public List<Colis> returnListe () {
		result = maDAO.findListe();
		if (result.isEmpty()){
			System.out.println("Liste vide");
		} 
		//rentre dans la boucle, result n'est pas vide, mais les donnees ne s'affichent pas
		for (Colis co : result){
			System.out.println(co.toString());
		}		
		return result;	
		
	} //returnInfoColis()
	
}

