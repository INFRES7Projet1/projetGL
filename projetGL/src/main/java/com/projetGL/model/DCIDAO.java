package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class DCIDAO extends DAO<DCI> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public DCI find(int id){
		// Insertion du DCI à laquelle appartient le médicament
	    DCI dci = null;
	    
		try{
			PreparedStatement statement = this.connect.prepareStatement(_get);
			statement.setInt(1, id);
			ResultSet resultat = statement.executeQuery();
			
			dci = new DCI(resultat.getShort("dci_Id"));
		    dci.Designation = resultat.getString("dci_Designation");
		    dci.ClasseT = new ClasseTherapeutiqueDAO().find(resultat.getInt("therapeutique_Id"));
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dci;
	}
	
	public DCI create(DCI dci){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_insert);
			statement.setInt(1, dci.Id);
			statement.setString(2, dci.Designation);
			statement.setInt(3, dci.ClasseT.Id);
			ResultSet resultat = statement.executeQuery();
			
			dci = find(dci.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dci;
	}
	
	public DCI update(DCI dci){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_update);
			statement.setString(1, dci.Designation);
			statement.setInt(2, dci.ClasseT.Id);
			statement.setInt(3, dci.Id);
			
			ResultSet resultat = statement.executeQuery();
			
			dci = find(dci.Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dci;
	}
	
	public void delete(DCI dci){
		try {
			PreparedStatement statement = this.connect.prepareStatement(_delete);
			statement.setInt(1, dci.Id);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retourne la liste des Options
	public List<DCI> findListe(){
		List<DCI> result = new ArrayList<DCI>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListe);
			ResultSet resultat = statement.executeQuery();
			
			DCI dci = null;
			
			int i = 0;
			while(resultat.next()){
				
				dci = find(resultat.getShort("dci_id"));
			    result.add(i, dci);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	// Options
	private static String _getListe = "SELECT * FROM dci;";
	private static String _get = "SELECT * FROM dci WHERE dci_Id = ?";

	private static String _insert = "INSERT INTO dci (dci_Id, dci_Designation, therapeutique_Id) VALUES ( ?, ?, ?)";
	
	private static String _update = "UPDATE dci SET dci_Designation = ?, therapeutique_Id = ? WHERE dci_Id = ?";
	
	private static String _delete = "DELETE FROM dci WHERE dci_Id = ?";
}