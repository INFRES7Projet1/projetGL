package com.projetGL.controller;

import java.util.List;

import com.projetGL.model.*;
import com.projetGL.vue.ColisView;

public class ColisController {
	
	private Colis modele;
	private ColisView vue;

	public List<com.projetGL.model.Colis> result;
	
	public ColisController () {
		

	}//Colis()
	
	public ColisController (Colis modele, ColisView vue) {
		this.modele = modele;
		this.vue = vue;		
	}//Colis()
	
	public List<com.projetGL.model.Colis> returnInfosColis () {
		EscrimDAO maDAO = EscrimDAO.getInstance();
		result = maDAO.GetAllColis();

//		for (Colis co : result){
//			System.out.println(co.toString());
//		}		
		return result;	
		
	} //returnInfoColis()
}
