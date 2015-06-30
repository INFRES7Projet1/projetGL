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
			PreparedStatement statement = this.connect.prepareStatement(_insert);
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
			PreparedStatement statement = this.connect.prepareStatement(_get);
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
				result.Option = new OptionColisDAO().find(resultat.getInt("options_Id"));
			    
				result.Type = new TypeColisDAO().find(resultat.getInt( "typeColis_Id" ));
			    result.SecteurUtilisation = new SecteurDAO().find(resultat.getShort("secteur_Id"));
				result.ListeMedicaments = new MedicamentDAO().GetMedicamentInColis(result.Id);
			    if (result.ListeMedicaments.isEmpty())
			    	result.ListeMedicaments = null;
			    
			    result.ListeOutils = new OutilDAO().GetOutilsInColis(result.Id);
			    if (result.ListeOutils.isEmpty())
			    	result.ListeOutils= null;
			    
			    result.ListeObjets =  new ObjetDAO().GetObjetsInColis(result.Id);
			    if (result.ListeObjets.isEmpty())
			    	result.ListeObjets= null;
			    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne le contenu de tous les colis
	public List<Colis> findListe(){
		
		List<Colis> result = new ArrayList<Colis>();
		try {
			Statement statement = this.connect.createStatement();
			ResultSet resultat = statement.executeQuery( _getListe );
			
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
	
	// Insert un outils dans la BDD et le lie au colis
	public boolean InsertOutilInColis(Outil outil, int colis_id){
		
		try {
			if (new OutilDAO().find(outil.Id) != null){
				PreparedStatement statement = this.connect.prepareStatement(_insertOutil_Colis);
				statement.setInt(1, outil.Id);
				statement.setInt(2, colis_id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Insert un medicament dans la BDD et le lie au colis
	public boolean InsertMedicamentInColis(Medicament med, int colis_id){
		
		try {
			if (new MedicamentDAO().find(med.Id) != null ){
				PreparedStatement statement = this.connect.prepareStatement(_insertMedicament_Colis);
				statement.setInt(1, med.Id);
				statement.setInt(2, colis_id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Insert un medicament dans la BDD et le lie au colis
	public boolean InsertObjetInColis(Objet obj, int colis_id){
		
		try {
			if (new ObjetDAO().find(obj.Id) != null){
				PreparedStatement statement = this.connect.prepareStatement(_insertObjet_Colis);
				statement.setInt(1, obj.Id);
				statement.setInt(2, colis_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public Colis update(Colis colis) {
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
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

	// -------------------------
	// DELETE Queries
	// -------------------------
	
	public void delete(Colis colis) {
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, colis.Id);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	// Delete la jointure liant un colis à sa configuration
	public boolean DeleteColisFromConfigurationColis(int colis_id){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteColisFromConfiguration_Colis);
			statement.setInt(1, colis_id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Delete la jointure liant un colis à sa configuration
	public boolean DeleteColisFromObjetColis(int colis_id){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteColisFromObjet_Colis);
			statement.setInt(1, colis_id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Delete la jointure liant un colis à sa configuration
	public boolean DeleteColisFromOutilColis(int colis_id){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteColisFromOutil_Colis);
			statement.setInt(1, colis_id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Delete la jointure liant un colis à sa configuration
	public boolean DeleteColisFromMedicamentColis(int colis_id){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteColisFromMedicament_Colis);
			statement.setInt(1, colis_id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// -----------------------
	// QUERIES 
	// -----------------------
	
	// Colis
	private static String _getListe = "SELECT colis_Id FROM colis;";
	private static String _get = "SELECT * FROM colis WHERE colis_Id = ?";
		
	//Insert Colis
	private static String _insert = "INSERT INTO colis (colis_Id, designation, etat, poids, affectataire, option_Id, typeColis_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	private static String _insertMedicament_Colis = "INSERT INTO medicament_colis ( medicament_Id, colis_Id) VALUES ( ?, ?)";
	private static String _insertObjet_Colis = "INSERT INTO objet_colis ( objet_Id, colis_Id) VALUES ( ?, ?)";
	private static String _insertOutil_Colis = "INSERT INTO outil_colis ( outil_Id, colis_Id) VALUES ( ?, ?)";
	
	// Update Colis
	private static String _update = "UPDATE colis SET WHERE medicament_Id = ?";
	
	// Delete Colis
	private static String _delete = "DELETE FROM colis WHERE colis_Id = ?";
	private static String _deleteColisFromConfiguration_Colis = "DELETE FROM configuration_colis WHERE colis_Id = ?";
	private static String _deleteColisFromMedicament_Colis = "DELETE FROM medicament_colis WHERE colis_Id = ?";
	private static String _deleteColisFromObjet_Colis = "DELETE FROM objet_colis WHERE colis_Id = ?";
	private static String _deleteColisFromOutil_Colis = "DELETE FROM outil_colis WHERE colis_Id = ?";
}
