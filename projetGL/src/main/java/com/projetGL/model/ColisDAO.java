package com.projetGL.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColisDAO extends DAO<Colis> {

	public Colis create(Colis colis) {
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insertColis);
			statement.setInt(1, colis.Id);
			statement.setString(2, colis.Designation);
			statement.setString(3, colis.Etat.toString());
			statement.setInt(4, colis.Poids);
			statement.setString(5, colis.Affectataire);
			statement.setInt(6, colis.Option.Id);
			statement.setInt(7, colis.Type.Id);
			ResultSet resultat = statement.executeQuery();
			
			colis = find(colis.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colis;
	}
	
	
	//Retourne le contenu de tout un colis en fonction de son id
	public Colis find(int colis_id){
		
		Colis result = null;
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
			if (resultat.next())
			{
			    // Insertion des valeurs récupérées dans la BDD
				result = new Colis(colis_id);
			    
			    // Si etat = "Demi-Plein", on formate le string pour qu'il corresponde à la valeur d'enumération
				result.Etat = Colis.Status.valueOf((resultat.getString( "etat" ) == "Demi-Plein" ? "DemiPlein" : resultat.getString( "etat" )));
				result.Poids = resultat.getInt( "poids" );
				result.Designation = resultat.getString( "designation" );
				result.Affectataire = resultat.getString( "affectataire" );
				
				System.out.println(resultat.getInt("options_Id"));
				result.Option = GetOption(resultat.getInt("options_Id"));
			    
				result.Type = GetTypeColis(resultat.getInt( "typeColis_Id" ));
			    
				result.ListeMedicaments = GetMedicamentInColis(result.Id);
			    if (result.ListeMedicaments.isEmpty())
			    	result.ListeMedicaments = null;
			    
			    result.ListeOutils = GetOutilsInColis(result.Id);
			    if (result.ListeOutils.isEmpty())
			    	result.ListeOutils= null;
			    
			    result.ListeObjets =  GetObjetsInColis(result.Id);
			    if (result.ListeObjets.isEmpty())
			    	result.ListeObjets= null;
			    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne le contenu de tous les colis
	public List<Colis> GetAllColis(){
		
		List<Colis> result = new ArrayList<Colis>();
		try {
			Statement statement = this.connect.createStatement();
			ResultSet resultat = statement.executeQuery( _getAllColis );
			
			int i = 0; 
			while ( resultat.next() ) {
			    // Insertion des valeurs récupérées dans la BDD
			    Colis colis = find(resultat.getInt( "colis_Id" ));
			    
			    if(colis != null){
			    	result.add(i, colis);
			    	i++;
			    }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Colis update(Colis colis) {
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_updateColis);
			statement.setString(1, colis.Designation);
			statement.setString(2, colis.Etat.toString());
			statement.setInt(3, colis.Poids);
			statement.setString(4, colis.Affectataire);
			statement.setInt(5, colis.Option.Id);
			statement.setInt(6, colis.Type.Id);
			statement.setInt(7, colis.Id);
			ResultSet resultat = statement.executeQuery();

			colis = find(colis.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colis;
	}


	public void delete(Colis colis) {
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteColis);
			statement.setInt(1, colis.Id);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Colis
	private static String _getAllColis = "SELECT colis_Id FROM colis;";
	private static String _getColis = "SELECT * FROM colis WHERE colis_Id = ?";
		
	//Insert Colis
	private static String _insertColis = "INSERT INTO colis (colis_Id, designation, etat, poids, affectataire, option_Id, typeColis_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	
	// Update Colis
	private static String _updateColis = "UPDATE colis SET WHERE medicament_Id = ?";
	
	// Delete Colis
	private static String _deleteColis = "DELETE FROM colis WHERE colis_Id = ?";
}
