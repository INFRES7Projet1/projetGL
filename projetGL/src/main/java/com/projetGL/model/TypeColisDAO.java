package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class TypeColisDAO extends DAO<TypeColis> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public TypeColis find(int id){
		TypeColis tc = null;
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getTypeInColis);
			statement.setInt(1, id);
			
			ResultSet resultat = statement.executeQuery();
			if (resultat.next())
			{
				tc = new TypeColis(id);
				tc.Designation = resultat.getString("typeColis_Designation");
				tc.Hauteur = resultat.getInt("hauteur");
				tc.Largeur = resultat.getInt("largeur");
				tc.Longueur = resultat.getInt("longueur");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tc;
	}
	
	public TypeColis create(TypeColis tc){
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_insertTypeColis);
			statement.setString(1, tc.Designation);
			statement.setInt(2, tc.Hauteur);
			statement.setInt(3, tc.Largeur);
			statement.setInt(4, tc.Longueur);
			
			if(statement.executeUpdate() != 0)
				tc = find(tc.Id);
			else
				tc = null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tc;
	}
	
	public TypeColis update(TypeColis tc){
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_updateTypeColis);
			statement.setString(1, tc.Designation);
			statement.setInt(2, tc.Hauteur);
			statement.setInt(3, tc.Largeur);
			statement.setInt(4, tc.Longueur);
			statement.setInt(5, tc.Id);
			
			if(statement.executeUpdate() != 0)
				tc = find(tc.Id);
			else
				tc = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tc;
	}
	
	public  void delete(TypeColis tc){

		try {
			PreparedStatement statement = this.connect.prepareStatement(_deleteTypeColis);
			statement.setInt(1, tc.Id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<TypeColis> findListe(){
		List<TypeColis> result = new ArrayList<TypeColis>();
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(_getListeTypeColis);
			
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
			
				TypeColis tc = find(resultat.getInt("typeColis_Id"));
				
				result.add(i, tc);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//TypeColis
	private static String _getListeTypeColis = "SELECT * FROM TypeColis";
	private static String _getTypeInColis = "SELECT * FROM TypeColis WHERE typeColis_Id = ?";

	private static String _insertTypeColis = "INSERT INTO typecolis ( typeColis_Designation, hauteur, largeur, longueur) VALUES ( ?, ?, ?, ?)";
	
	private static String _updateTypeColis = "UPDATE typecolis SET typeColis_Designation = ?, hauteur = ?, largeur = ?, longueur = ? WHERE typeColis_Id = ?";
	
	private static String _deleteTypeColis = "DELETE FROM typecolis WHERE typeColis_Id = ?";
	
}