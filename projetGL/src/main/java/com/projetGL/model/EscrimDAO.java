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
			    			    
			    result.add(i, colis);
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
	private static String _getAllColis = "SELECT * FROM colis;";

}//EscrimDAO
