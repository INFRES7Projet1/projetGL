package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class MedicamentDAO extends DAO<Medicament> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public Medicament find(int id){
		Medicament med = null;
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getOption);
			statement.setInt(1, id);
			
			ResultSet resultat = statement.executeQuery();
			resultat.next();
			
			med = new Medicament(id,  resultat.getString("options_Designation"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return med;
	}
	
	public Medicament create(Medicament medicament){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insertMedicament);
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
			PreparedStatement statement = this.connect.prepareStatement(_updateMedicament);
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
			PreparedStatement statement = this.connect.prepareStatement(_deleteMedicament);
			statement.setInt(1, medicament.Id);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des Options
	public List<Medicament> GetListeOptions(){
		List<Medicament> result = new ArrayList<Medicament>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListeOptions);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				Medicament opt = new Medicament(resultat.getInt("options_Id"),  resultat.getString("options_Designation"));
				result.add(i, opt);
				i++;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// Retourne la liste des Medicaments
	public List<Medicament> GetListeMedicaments(){
		List<Medicament> result = new ArrayList<Medicament>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListeMedicament);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				
				// Insertion de la classe therapeutique à laquelle appartient le médicament
				ClasseTherapeutique ct = new ClasseTherapeutique(resultat.getInt("therapeutique_Id"));
			    ct.Designation = resultat.getString("therapeutique_Designation");
				
			    // Insertion du DCI à laquelle appartient le médicament
			    DCI dci = new DCI(resultat.getShort("dci_id"));
			    dci.Designation = resultat.getString("dci_Designation");
			    dci.ClasseT = ct;
			    
			    // Insertion des valeurs récupérées dans la BDD lié au médicament
			    Medicament medicament = new Medicament(resultat.getInt( "medicament_Id"));
			  
			    medicament.Produit = resultat.getString("produit");
			    medicament.Quantite = resultat.getInt("quantite");
			    medicament.FormeDosage = resultat.getString("forme_dosage");
			    medicament.Lot = resultat.getString("lot");
			    medicament.Dlu = resultat.getDate("dlu");
			    medicament.Dotation = resultat.getString("dotation");
			    medicament.Dci = dci;
			    
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
				
				// Insertion de la classe therapeutique à laquelle appartient le médicament
				ClasseTherapeutique ct = new ClasseTherapeutique(resultat.getInt("therapeutique_Id"));
			    ct.Designation = resultat.getString("therapeutique_Designation");
				
			    // Insertion du DCI à laquelle appartient le médicament
			    DCI dci = new DCI(resultat.getShort("dci_id"));
			    dci.Designation = resultat.getString("dci_Designation");
			    dci.ClasseT = ct;
			    
			    // Insertion des valeurs récupérées dans la BDD lié au médicament
			    Medicament medicament = new Medicament(resultat.getInt( "medicament_Id"));
			  
			    medicament.Produit = resultat.getString("produit");
			    medicament.Quantite = resultat.getInt("quantite");
			    medicament.FormeDosage = resultat.getString("forme_dosage");
			    medicament.Lot = resultat.getString("lot");
			    medicament.Dlu = resultat.getDate("dlu");
			    medicament.Dotation = resultat.getString("dotation");
			    medicament.Dci = dci;
			    
			    			    
			    result.add(i, medicament);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// Medicament
	private static String _getListeMedicament = "SELECT M.medicament_Id, produit, quantite, forme_dosage, lot, dlu, dotation, D.dci_Id, D.dci_Designation, CT.therapeutique_Id, CT.therapeutique_Designation FROM medicament M,  dci D, classe_therapeutique CT WHERE D.dci_Id = M.dci_Id AND D.therapeutique_Id = CT.therapeutique_Id";
	private static String _getMedicamentInColis = "SELECT colis_Id, M.medicament_Id, produit, quantite, forme_dosage, lot, dlu, dotation, D.dci_Id, D.dci_Designation, CT.therapeutique_Id, CT.therapeutique_Designation FROM medicament M, medicament_colis MC, dci D, classe_therapeutique CT WHERE M.medicament_Id = MC.medicament_Id AND D.dci_Id = M.dci_Id AND D.therapeutique_Id = CT.therapeutique_Id AND colis_Id = ?";
	
	private static String _insertMedicament = "INSERT INTO medicament (medicament_Id, produit, quantite, forme_dosage, dlu, dotation, dci_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	
	private static String _updateMedicament = "UPDATE medicament SET produit = ?, quantite = ?, forme_dosage = ?, dlu = ?, dotation = ?, dci_Id = ? WHERE medicament_Id = ?";
	
	private static String _deleteMedicament = "DELETE FROM medicament WHERE medicament_Id = ?";
}