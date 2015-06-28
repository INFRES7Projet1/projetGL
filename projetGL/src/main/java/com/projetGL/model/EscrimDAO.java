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
	
	// Retourne la liste des Medicaments
	public List<Medicament> GetListeMedicaments(){
		List<Medicament> result = new ArrayList<Medicament>();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_getListeMedicament);
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
	
	//Retourne la liste des Outils
	public List<Outil> GetListeOutils(){
		Connect();
		
		List<Outil> result = new ArrayList<Outil>();
		try {
			PreparedStatement statement = connexion.prepareStatement(_getListeOutils);
			ResultSet resultat = statement.executeQuery();
			
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
			e.printStackTrace();
		}
		return result;
	}
	
	//Retourne la liste des Objets
	public List<Objet> GetListeObjets(){
		Connect();
		
		List<Objet> result = new ArrayList<Objet>();
		try {
			PreparedStatement statement = connexion.prepareStatement(_getListeObjets);
			ResultSet resultat = statement.executeQuery();
			
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
	
	// Retourne la liste des configurations sans les colis
	public List<ConfigurationColis> GetListeConfiguration(){

		Connect();
		
		List<ConfigurationColis> result = new ArrayList<ConfigurationColis>();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_getListeConfiguration);
			
			ResultSet resultat = statement.executeQuery();
			
			while ( resultat.next() ) {
				ConfigurationColis conf = new ConfigurationColis(resultat.getInt("config_Id"));
				conf.Designation = resultat.getString("config_Designation");
				    
			    result.add(conf.Id, conf);
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
	
	//Retourne la liste de tous les médicaments contenu dans un colis
	public List<Medicament> GetMedicamentInColis(int colis_id){
		Connect();
		
		List<Medicament> result = new ArrayList<Medicament>();
		try {
			PreparedStatement statement = connexion.prepareStatement(_getMedicamentInColis);
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
	
	//Retourne la liste de tous les outils contenu dans un colis
	public List<Outil> GetOutilsInColis(int colis_id){
		Connect();
		
		List<Outil> result = new ArrayList<Outil>();
		try {
			PreparedStatement statement = connexion.prepareStatement(_getOutilsInColis);
			statement.setInt(1, colis_id);
			ResultSet resultat = statement.executeQuery();
			
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
	
	//Retourne la liste de tous les objets contenu dans un colis
	
	//Retourne la liste des objets d'un colis
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
	
	// -----------------------
	// Insert Function
	// -----------------------
	
	// Insert un outils dans la BDD
	public boolean InsertOutil(Outil outil){
		Connect();
		
		try {
			PreparedStatement statement = connexion.prepareStatement(_insertOutil);
			statement.setInt(1, outil.Id);
			statement.setString(2, outil.Designation);
			statement.setInt(3, outil.Quantite);
			statement.setDate(4, (java.sql.Date)outil.Dlu);
			statement.setString(5, outil.Reference);
			ResultSet resultat = statement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Insert un outils dans la BDD et le lie au colis
	public boolean InsertOutilInColis(Outil outil, int colis_id){
		Connect();
		//TODO : gestion de la presence u non de l'outils dans la BDD
		try {
			if (InsertOutil(outil)){
				PreparedStatement statement = connexion.prepareStatement(_insertOutil_Colis);
				statement.setInt(1, outil.Id);
				statement.setInt(2, colis_id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
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
	
	// Static attributes
	
	// User of mysql server
	private static String _user = "EscrimUser";
	
	// Password of mysql server
	private static String _pwd = "EscrimPassword";
	
	// Select Queries
	
	// Colis
	private static String _getAllColis = "SELECT colis_Id FROM colis;";
	private static String _getColis = "SELECT * FROM colis WHERE colis_Id = ?";
	
	// Configuration
	private static String _getListeConfiguration = "SELECT * FROM configuration;";
	private static String _getConfigurationContent = "SELECT CC.config_Id, CO.config_Designation, C.colis_Id FROM configuration CO, configuration_colis CC, colis C WHERE CC.colis_Id = C.colis_Id AND CO.config_Id = CC.config_Id AND CC.config_Id = ?;";
	
	// Options
	private static String _getListeOptions = "SELECT * FROM options;";
	private static String _getOptionInColis = "SELECT options_Id, options_Designation FROM options WHERE options_Id = ?";

	// Medicament
	private String _getListeMedicament = "SELECT M.medicament_Id, produit, quantite, forme_dosage, lot, dlu, dotation, D.dci_Id, D.dci_Designation, CT.therapeutique_Id, CT.therapeutique_Designation FROM medicament M,  dci D, classe_therapeutique CT WHERE D.dci_Id = M.dci_Id AND D.therapeutique_Id = CT.therapeutique_Id";
	private static String _getMedicamentInColis = "SELECT colis_Id, M.medicament_Id, produit, quantite, forme_dosage, lot, dlu, dotation, D.dci_Id, D.dci_Designation, CT.therapeutique_Id, CT.therapeutique_Designation FROM medicament M, medicament_colis MC, dci D, classe_therapeutique CT WHERE M.medicament_Id = MC.medicament_Id AND D.dci_Id = M.dci_Id AND D.therapeutique_Id = CT.therapeutique_Id AND colis_Id = ?";
	
	//Objet
	private static String _getListeObjets = "SELECT * FROM objet; ";
	private static String _getObjetsInColis = "SELECT colis_Id, O.objet_Id, objet_Designation FROM objet O, objet_colis OC WHERE O.objet_Id = OC.objet_Id AND colis_Id = ?";
	
	//Outils 
	private static String _getListeOutils = "SELECT * FROM outil;";
	private static String _getOutilsInColis = "SELECT colis_Id, O.outil_Id, outil_Designation, quantite, dlu, reference FROM outil O, outil_colis OC WHERE O.outil_Id = OC.outil_Id AND colis_Id = ?";
	
	//TypeColis
	private static String _getListeTypeColis = "SELECT * FROM TypeColis";
	private static String _getTypeInColis = "SELECT * FROM TypeColis WHERE typeColis_Id = ?";
	
	// -----------------------------
	// Insert Queries
	// -----------------------------
	
	private static String _insertColis = "INSERT INTO colis (colis_Id, designation, etat, poids, affectataire, option_Id, typeColis_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	private static String _insertOutil = "INSERT INTO outil (outil_Id, outil_Designation, quantite, dlu, reference) VALUES (?, ?, ?, ?, ?)";
	private static String _insertObjet = "INSERT INTO objet (objet_Id, objet_Designation, secteur_Id) VALUES ( ?, ?, ?)";
	private static String _insertMedicament = "INSERT INTO medicament (medicament_Id, produit, quantite, forme_dosage, dlu, dotation, dci_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	private static String _insertClasseTherapeutique = "INSERT INTO classe_therapeutique (therapeutique_Id, therapeutique_Designation) VALUES ( ?, ?)";
	private static String _insertDCI = "INSERT INTO dci (dci_Id, dci_Designation, therapeutique_Id) VALUES ( ?, ?, ?)";
	private static String _insertOption= "INSERT INTO options (options_Id, options_Designation) VALUES ( ?, ?)";
	private static String _insertTypeColis = "INSERT INTO typecolis (typeColis_Id, typeColis_Designation, hauteur, largeur, longueur) VALUES ( ?, ?, ?, ?, ?)";
	private static String _insertConfiguration= "INSERT INTO configuration (config_Id, config_Designation) VALUES ( ?, ?)";
	private static String _insertSecteur= "INSERT INTO secteur (secteur_Id, secteur_Designation, Dgenerique_Id) VALUES ( ?, ?, ?)";
	private static String _insertDesignationGenerique= "INSERT INTO designation_generique (Dgenerique_Id, Dgenerique_Designation) VALUES ( ?, ?)";
	
	// -----------------------------
	// Insert Join Queries
	// -----------------------------
	
	private static String _insertConfiguration_Colis = "INSERT INTO configuration_colis ( config_Id, colis_Id) VALUES ( ?, ?)";
	private static String _insertMedicament_Colis = "INSERT INTO medicament_colis ( medicament_Id, colis_Id) VALUES ( ?, ?)";
	private static String _insertObjet_Colis = "INSERT INTO objet_colis ( objet_Id, colis_Id) VALUES ( ?, ?)";
	private static String _insertOutil_Colis = "INSERT INTO outil_colis ( outil_Id, colis_Id) VALUES ( ?, ?)";
	
	// -----------------------------
	// Update Queries
	// -----------------------------
	
	private static String _updateColis = "UPDATE colis SET WHERE medicament_Id = ?";
	private static String _updateOutil = "UPDATE outil SET outil_Id = ?, outil_Designation = ?, quantite = ?, dlu = ?, reference = ? WHERE outil_Id = ?";
	private static String _updateObjet = "UPDATE objet SET objet_Id = ?, objet_Designation = ?, secteur_Id = ? WHERE outil_Id = ? ";
	private static String _updateMedicament = "UPDATE medicament SET medicament_Id = ?, produit = ?, quantite = ?, forme_dosage = ?, dlu = ?, dotation = ?, dci_Id = ? WHERE medicament_Id = ?";
	private static String _updateClasseTherapeutique = "UPDATE classe_therapeutique SET therapeutique_Id = ?, therapeutique_Designation = ? WHERE therapeutique_Id = ?";
	private static String _updateDCI = "UPDATE dci SET dci_Id = ?, dci_Designation = ?, therapeutique_Id = ? WHERE dci_Id = ?";
	private static String _updateOption= "UPDATE option SET options_Id = ?, options_Designation = ? WHERE options_Id = ?";
	private static String _updateTypeColis = "UPDATE typecolis SET typeColis_Id = ?, typeColis_Designation = ?, hauteur = ?, largeur = ?, longueur = ? WHERE typeColis_Id = ?";
	private static String _updateConfiguration = "UPDATE configuration SET config_Id = ?, config_Designation = ? WHERE config_Id = ?";
	private static String _updateSecteur = "UPDATE configuration SET secteur_Id = ?, secteur_Designation = ?, Dgenerique_Id = ? WHERE secteur_Id = ?";
	private static String _updateDesignationGenerique = "UPDATE configuration SET Dgenerique_Id = ?, Dgenerique_Designation = ? WHERE Dgenerique_Id = ?";
	
	// -----------------------------
	// Delete Queries
	// -----------------------------
	
	private static String _deleteColis = "DELETE FROM colis WHERE colis_Id = ?";
	private static String _deleteOutil = "DELETE FROM outil WHERE outil_Id = ?";
	private static String _deleteObjet = "DELETE FROM objet WHERE objet_Id = ?";
	private static String _deleteMedicament = "DELETE FROM medicament WHERE medicament_Id = ?";
	private static String _deleteClasseTherapeutique = "DELETE FROM classe_therapeutique WHERE therapeutique_Id = ?";
	private static String _deleteDCI = "DELETE FROM dci WHERE dci_Id = ?";
	private static String _deleteOption= "DELETE FROM options WHERE options_Id = ?";
	private static String _deleteTypeColis = "DELETE FROM typecolis WHERE typeColis_Id = ?";
	private static String _deleteConfiguration= "DELETE FROM configuration WHERE config_Id = ?";
	private static String _deleteSecteur= "DELETE FROM secteur WHERE secteur_Id = ?";
	private static String _deleteDesignationGenerique= "DELETE FROM designation_generique WHERE Dgenerique_Id = ?";
	
	
	// -----------------------------
	// Delete Join Queries
	// -----------------------------
	
	private static String _deleteConfFromConfiguration_Colis = "DELETE FROM configuration_colis WHERE conf_Id = ?";
	private static String _deleteColisFromConfiguration_Colis = "DELETE FROM configuration_colis WHERE colis_Id = ?";
	
	private static String _deleteMedicamentFromMedicament_Colis = "DELETE FROM medicament_colis WHERE medicament_Id = ?";
	private static String _deleteColisFromMedicament_Colis = "DELETE FROM medicament_colis WHERE colis_Id = ?";
	
	private static String _deleteObjetFromObjet_Colis = "DELETE FROM objet_colis WHERE objet_Id = ?";
	private static String _deleteColisFromObjet_Colis = "DELETE FROM objet_colis WHERE colis_Id = ?";
	
	private static String _deleteOutilFromOutil_Colis = "DELETE FROM outil_colis WHERE outil_Id = ?";
	private static String _deleteColisFromOutil_Colis = "DELETE FROM outil_colis WHERE colis_Id = ?";
	
}//EscrimDAO
