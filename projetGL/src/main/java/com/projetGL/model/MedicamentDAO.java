package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class MedicamentDAO extends DAO<Medicament> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public Medicament find(int id){
		Medicament med = null;
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			
			ResultSet resultat = statement.executeQuery();
			
			// A verifier si necessaire
			resultat.next();
		    
		    // Insertion des valeurs récupérées dans la BDD lié au médicament
		    Medicament medicament = new Medicament(resultat.getInt( "medicament_Id"));
		  
		    medicament.Produit = resultat.getString("produit");
		    medicament.Quantite = resultat.getInt("quantite");
		    medicament.FormeDosage = resultat.getString("forme_dosage");
		    medicament.Lot = resultat.getString("lot");
		    medicament.Dlu = resultat.getDate("dlu");
		    medicament.Dotation = resultat.getString("dotation");
		    medicament.Dci =  new DCIDAO().find(resultat.getShort("dci_id"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return med;
	}
	
	public Medicament create(Medicament medicament){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setInt(1, medicament.Id);
			statement.setString(2, medicament.Produit);
			statement.setInt(3, medicament.Quantite);
			statement.setString(4, medicament.FormeDosage);
			statement.setDate(5, (java.sql.Date)medicament.Dlu);
			statement.setString(6, medicament.Dotation);
			statement.setInt(7, medicament.Dci.Id);
			ResultSet resultat = statement.executeQuery();
			
			medicament = find(medicament.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medicament;
	}
	
	public Medicament update(Medicament medicament){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString(1, medicament.Produit);
			statement.setInt(2, medicament.Quantite);
			statement.setString(3, medicament.FormeDosage);
			statement.setDate(4, (java.sql.Date)medicament.Dlu);
			statement.setString(5, medicament.Dotation);
			statement.setInt(6, medicament.Dci.Id);
			statement.setInt(7, medicament.Id);
			
			ResultSet resultat = statement.executeQuery();
			
			medicament = find(medicament.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medicament;
	}
	
	public void delete(Medicament medicament){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, medicament.Id);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des Medicaments
	public List<Medicament> findListe(){
		List<Medicament> result = new ArrayList<Medicament>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				
			    // Insertion des valeurs récupérées dans la BDD lié au médicament
			    Medicament medicament = find(resultat.getInt( "medicament_Id"));
			    
			    result.add(i, medicament);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//Retourne la liste de tous les médicaments contenu dans un colis
	public List<Medicament> GetMedicamentInColis(int colis_id){
		List<Medicament> result = new ArrayList<Medicament>();
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getMedicamentInColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0; 
			while ( resultat.next() ) {
				
			    // Insertion des valeurs récupérées dans la BDD lié au médicament
			    Medicament medicament = find(resultat.getInt( "medicament_Id"));
			    
			    result.add(i, medicament);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	// Delete la jointure liant un medicament à son colis
	public boolean DeleteMedicamentFromMedicamentColis(int med_id){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteMedicamentFromMedicament_Colis);
			statement.setInt(1, med_id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	// Medicament
	private static String _get = "SELECT M.medicament_Id, produit, quantite, forme_dosage, lot, dlu, dotation, D.dci_Id, D.dci_Designation, CT.therapeutique_Id, CT.therapeutique_Designation FROM medicament M, dci D, classe_therapeutique CT WHERE D.dci_Id = M.dci_Id AND D.therapeutique_Id = CT.therapeutique_Id AND medicament_Id = ?";
	private static String _getListe = "SELECT medicament_Id FROM medicament";
	private static String _getMedicamentInColis = "SELECT colis_Id, M.medicament_Id FROM medicament M, medicament_colis MC WHERE M.medicament_Id = MC.medicament_Id AND colis_Id = ?";
	
	private static String _insert = "INSERT INTO medicament (medicament_Id, produit, quantite, forme_dosage, dlu, dotation, dci_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	
	private static String _update = "UPDATE medicament SET produit = ?, quantite = ?, forme_dosage = ?, dlu = ?, dotation = ?, dci_Id = ? WHERE medicament_Id = ?";
	
	private static String _delete = "DELETE FROM medicament WHERE medicament_Id = ?";
	private static String _deleteMedicamentFromMedicament_Colis = "DELETE FROM medicament_colis WHERE medicament_Id = ?";
	
}