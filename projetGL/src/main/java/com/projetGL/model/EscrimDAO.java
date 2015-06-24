package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class EscrimDAO {

	Connection connexion = null;
	
	public EscrimDAO(){
		/* Chargement du driver JDBC pour MySQL */
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Colis> GetAllColis(){
		Connect();
		
		List<Colis> result = new ArrayList<Colis>();
		
		/* Cr�ation de l'objet g�rant les requ�tes */
		try {
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery( _getAllColis );
			
			/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
			
			int i = 0; 
			while ( resultat.next() ) {
				
			    // Insertion des valeurs récupérées dans la BDD
			    Colis colis = new Colis(resultat.getInt( "colis_Id" ));
			    
			    // Si etat = "Demi-Plein", on formate le string pour qu'il corresponde à la valeur d'enumération
			    colis.Etat = Colis.Status.valueOf((resultat.getString( "etat" ) == "Demi-Plein" ? "DemiPlein" : resultat.getString( "etat" )));
			    colis.Poids = resultat.getInt( "poids" );
			    colis.Designation = resultat.getString( "designation" );
			    colis.Affectataire = resultat.getString( "affectataire" );
			    
			    colis.Type = new TypeColis(resultat.getInt( "typeColis_Id" ));
			    colis.Option = new OptionColis(resultat.getInt( "options_Id" ));
			    
			    colis.ListeMedicaments = GetMedicamentInColis(colis.Id);
			    if (colis.ListeMedicaments.isEmpty())
			    	colis.ListeMedicaments = null;
			    
			    colis.ListeOutils = GetOutilsInColis(colis.Id);
			    if (colis.ListeOutils.isEmpty())
			    	colis.ListeOutils= null;
			    
			    colis.ListeObjets =  GetObjetsInColis(colis.Id);
			    if (colis.ListeObjets.isEmpty())
			    	colis.ListeObjets= null;
			    
			    result.add(i, colis);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Medicament> GetMedicamentInColis(int colis_id){
		Connect();
		
		List<Medicament> result = new ArrayList<Medicament>();
		
		/* Cr�ation de l'objet g�rant les requ�tes */
		try {
			PreparedStatement statement = connexion.prepareStatement(_getMedicamentInColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
			/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
			
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
	
	public List<Outil> GetOutilsInColis(int colis_id){
		Connect();
		
		List<Outil> result = new ArrayList<Outil>();
		
		/* Cr�ation de l'objet g�rant les requ�tes */
		try {
			PreparedStatement statement = connexion.prepareStatement(_getOutilsInColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
			/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
			
			int i = 0; 
			while ( resultat.next() ) {
				
				Outil outil = new Outil(resultat.getInt("outil_Id"));
				
				outil.Designation = resultat.getString("outil_Designation");
				outil.Quantite = resultat.getShort("quantite");
				outil.Dlu = resultat.getDate("dlu");
				outil.Reference = resultat.getString("reference");
			    
			    			    
			    result.add(i, outil);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Objet> GetObjetsInColis(int colis_id){
		Connect();
		
		List<Objet> result = new ArrayList<Objet>();
		
		/* Cr�ation de l'objet g�rant les requ�tes */
		try {
			PreparedStatement statement = connexion.prepareStatement(_getObjetsInColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
			/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
			
			int i = 0; 
			while ( resultat.next() ) {
				
				Objet obj = new Objet(resultat.getInt("objet_Id"));
				
				obj.Designation = resultat.getString("objet_Designation");
				    
			    result.add(i, obj);
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private void Connect(){
			
			System.out.println("Driver O.K.");
	
			/* Connexion � la base de donn�es */
			String url = "jdbc:mysql://127.0.0.1:3306/db_escrim";
			String utilisateur = "EscrimUser";
			String motDePasse = "EscrimPassword";
			
			try {
			    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			    System.out.println("It works");
			    /* Ici, nous placerons nos requ�tes vers la BDD */
			    /* ... */

			} catch ( SQLException ex ) {
			    /* G�rer les �ventuelles erreurs ici */
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			
			}
			System.out.println("Connexion effective !");       	
	}
	
	private void Disconnect(){
		
		
	}
	
	// Static attributes
	
	// User of mysql server
	private static String _user = "EscrimUser";
	
	// Password of mysql server
	private static String _pwd = "EscrimPassword";
	
	// Select Queries
	
	// Colis
	private static String _getAllColis = "SELECT * FROM colis;";
	
	private static String _getMedicamentInColis = "SELECT colis_Id, M.medicament_Id, produit, quantite, forme_dosage, lot, dlu, dotation, D.dci_Id, D.dci_Designation, CT.therapeutique_Id, CT.therapeutique_Designation FROM medicament M, medicament_colis MC, dci D, classe_therapeutique CT WHERE M.medicament_Id = MC.medicament_Id AND D.dci_Id = M.dci_Id AND D.therapeutique_Id = CT.therapeutique_Id AND colis_Id = ?";
	private static String _getOutilsInColis = "SELECT colis_Id, O.outil_Id, outil_Designation, quantite, dlu, reference FROM outil O, outil_colis OC WHERE O.outil_Id = OC.outil_Id AND colis_Id = ?";
	private static String _getObjetsInColis = "SELECT colis_Id, O.objet_Id, objet_Designation FROM objet O, objet_colis OC WHERE O.objet_Id = OC.objet_Id AND colis_Id = ?";
	// 

}//EscrimDAO
