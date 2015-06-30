package com.projetGL.model;

import java.sql.*;
import java.util.*;

public class EscrimDAO {

	Connection connexion = null;
	
	//Constructeur
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
	
	
	// -----------------------------------
	// SELECT Functions
	// -----------------------------------
	
	public List<TypeColis> GetListeTypeColis(){
		List<TypeColis> result = new ArrayList<TypeColis>();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_getListeTypeColis);
			
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
			
				TypeColis tc = new TypeColis(resultat.getInt("typeColis_Id"));
				tc.Designation = resultat.getString("typeColis_Designation");
				tc.Hauteur = resultat.getInt("hauteur");
				tc.Largeur = resultat.getInt("largeur");
				tc.Longueur = resultat.getInt("longueur");
				
				result.add(i, tc);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// Retourne la liste des Options
	public List<OptionColis> GetListeOptions(){
		List<OptionColis> result = new ArrayList<OptionColis>();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_getListeOptions);
			ResultSet resultat = statement.executeQuery();
			
			int i = 0;
			while(resultat.next()){
				OptionColis opt = new OptionColis(resultat.getInt("options_Id"),  resultat.getString("options_Designation"));
				result.add(i, opt);
				i++;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne la liste des configurations sans les colis
	public List<ConfigurationColis> GetListeConfiguration(){

		Connect();
		
		List<ConfigurationColis> result = new ArrayList<ConfigurationColis>();
		int i = 0;
		try {
			PreparedStatement statement = connexion.prepareStatement(_getListeConfiguration);
			
			ResultSet resultat = statement.executeQuery();
			
			while ( resultat.next() ) {
				ConfigurationColis conf = new ConfigurationColis(resultat.getInt("config_Id"));
				conf.Designation = resultat.getString("config_Designation");
				    
			    result.add(i, conf);
			    i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne le contenue d'une Configuration
	public ConfigurationColis GetConfigurationContent(int conf_id)
	{
		ConfigurationColis result = null;
		
		try {
			Connect();
			PreparedStatement statement = connexion.prepareStatement(_getConfigurationContent);
			statement.setInt(1, conf_id);
			ResultSet resultat = statement.executeQuery();
			
			
			int i = 0; 
			while ( resultat.next() ) {
				if ( result == null) {
					result = new ConfigurationColis(resultat.getInt("config_Id"));
					result.Designation = resultat.getString("config_Designation");
					result.ListeColis = new ArrayList<Colis>();
				}
				result.ListeColis.add(i, GetColisContent(resultat.getInt("colis_Id")));
			    i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// Retourne la liste de toute les Configurations avec la liste des Colis
	public List<ConfigurationColis> GetAllConfiguration()
	{
		List<ConfigurationColis> result = GetListeConfiguration();
		
		//int i=0;
		for(ConfigurationColis conf : result){
			ConfigurationColis c = GetConfigurationContent(conf.Id);
			
			if (c != null){
				result.set( conf.Id, c);
				//i++;
			}
		}
		
		return result;
	}
	
	//Retourne le contenu de tout un colis en fonction de son id
	public Colis GetColisContent(int colis_id){
		
		Colis result = null;
		
		try {
			Connect();
			PreparedStatement statement = connexion.prepareStatement(_getColis);
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
		Connect();
		
		List<Colis> result = new ArrayList<Colis>();
		try {
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery( _getAllColis );
			
			int i = 0; 
			while ( resultat.next() ) {
			    // Insertion des valeurs récupérées dans la BDD
			    Colis colis = GetColisContent(resultat.getInt( "colis_Id" ));
			    
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
	
	// Retourne le contenu d'une option
	public OptionColis GetOption(int option_id){
		OptionColis opt = null;
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_getOptionInColis);
			statement.setInt(1, option_id);
			
			ResultSet resultat = statement.executeQuery();
			resultat.next();
			
			opt = new OptionColis(option_id,  resultat.getString("options_Designation"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return opt;
	}
	
	// Retourne le Type d'un colis
	public TypeColis GetTypeColis(int type_id){
		TypeColis tc = null;
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_getTypeInColis);
			statement.setInt(1, type_id);
			
			ResultSet resultat = statement.executeQuery();
			resultat.next();
			
			tc = new TypeColis(type_id);
			tc.Designation = resultat.getString("typeColis_Designation");
			tc.Hauteur = resultat.getInt("hauteur");
			tc.Largeur = resultat.getInt("largeur");
			tc.Longueur = resultat.getInt("longueur");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tc;
	}
	
	
	// -----------------------
	// INSERT Function
	// -----------------------
	
	
	
	
	// Insert un Objet dans la BDD
	public boolean InsertObjet(Objet objet){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_insertObjet);
			statement.setInt(1, objet.Id);
			statement.setString(2, objet.Designation);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	// Insert une Option dans la BDD
	public boolean InsertOption(OptionColis op){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_insertOption);
			statement.setInt(1, op.Id);
			statement.setString(2, op.Designation);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Insert une Type Colis dans la BDD
	public boolean InsertTypeColis(TypeColis tp){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_insertTypeColis);
			statement.setInt(1, tp.Id);
			statement.setString(2, tp.Designation);
			statement.setInt(3, tp.Hauteur);
			statement.setInt(4, tp.Largeur);
			statement.setInt(5, tp.Longueur);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Insert une Secteur dans la BDD
	public boolean InsertSecteur(Secteur sec){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_insertSecteur);
			statement.setInt(1, sec.Id);
			statement.setString(2, sec.Designation);
			statement.setInt(3, sec.DesignationGenerique.Id);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	
	// Insert une configuration dans la BDD
	public boolean InsertConfiguration(ConfigurationColis conf){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_insertConfiguration);
			statement.setInt(1, conf.Id);
			statement.setString(2, conf.Designation);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	// --------------------------------
	// UPDATE Values
	// --------------------------------
	
	
	
	// Update une Option dans la BDD
	public boolean UpdateOption(OptionColis op){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_updateOption);
			statement.setString(1, op.Designation);
			statement.setInt(2, op.Id);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Update un Type Colis dans la BDD
	public boolean UpdateTypeColis(TypeColis tp){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_updateTypeColis);
			statement.setString(1, tp.Designation);
			statement.setInt(2, tp.Hauteur);
			statement.setInt(3, tp.Largeur);
			statement.setInt(4, tp.Longueur);
			statement.setInt(5, tp.Id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Update un Secteur dans la BDD
	public boolean UpdateSecteur(Secteur sec){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_updateSecteur);
			statement.setString(1, sec.Designation);
			statement.setInt(2, sec.DesignationGenerique.Id);
			statement.setInt(3, sec.Id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
		

	// Update une configuration dans la BDD
	public boolean UpdateConfiguration(ConfigurationColis conf){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_updateConfiguration);
			statement.setString(1, conf.Designation);
			statement.setInt(2, conf.Id);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
		
	
	
	// -----------------------
	// DELETE Queries
	// -----------------------
	
		
	// Delete une Option dans la BDD
	public boolean DeleteOption(int op_id){
			Connect();
			
			try {
				PreparedStatement statement = connexion.prepareStatement(_deleteOption);
				statement.setInt(1, op_id);
				
				ResultSet resultat = statement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		
	// Delete une Designation Generique dans la BDD
	public boolean DeleteDesignationGenerique(int dg_id){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_deleteDesignationGenerique);
			statement.setInt(1, dg_id);
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Delete une configuration dans la BDD
	public boolean DeleteConfiguration(int conf_id){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_deleteConfiguration);
			statement.setInt(1, conf_id);
			
			ResultSet resultat = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	

	
	

	
	

	// -----------------------
	// Connections Handlers
	// -----------------------
	
	// Connection à la BDD
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
	
	// Deconnection à la BDD
	private void Disconnect(){
		
		
	}
	
	// -----------------------
	// Static attributes
	// -----------------------
	
	// User of mysql server
	private static String _user = "EscrimUser";
	
	// Password of mysql server
	private static String _pwd = "EscrimPassword";
	
	// Select Queries
	
	// Configuration
	private static String _getListeConfiguration = "SELECT * FROM configuration;";
	private static String _getConfigurationContent = "SELECT CC.config_Id, CO.config_Designation, C.colis_Id FROM configuration CO, configuration_colis CC, colis C WHERE CC.colis_Id = C.colis_Id AND CO.config_Id = CC.config_Id AND CC.config_Id = ?;";
	
	// Options
	private static String _getListeOptions = "SELECT * FROM options;";
	private static String _getOptionInColis = "SELECT options_Id, options_Designation FROM options WHERE options_Id = ?";

	//TypeColis
	private static String _getListeTypeColis = "SELECT * FROM TypeColis";
	private static String _getTypeInColis = "SELECT * FROM TypeColis WHERE typeColis_Id = ?";
	
	// -----------------------------
	// Insert Queries
	// -----------------------------
	
	private static String _insertOption= "INSERT INTO options (options_Id, options_Designation) VALUES ( ?, ?)";
	private static String _insertTypeColis = "INSERT INTO typecolis (typeColis_Id, typeColis_Designation, hauteur, largeur, longueur) VALUES ( ?, ?, ?, ?, ?)";
	private static String _insertConfiguration= "INSERT INTO configuration (config_Id, config_Designation) VALUES ( ?, ?)";
	

	
	// -----------------------------
	// Delete Join Queries
	// -----------------------------
	
	
	
	
	
	
	
}//EscrimDAO
