import java.sql.*;

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
	
	public String[] GetAllColis(){
		Connect();
		String[] results = new String[10];
		/* Cr�ation de l'objet g�rant les requ�tes */
		try {
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery( _getAllColis );
			
			/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
			
			int i = 0; 
			while ( resultat.next() ) {
			    int idColis = resultat.getInt( "colis_Id" );
			    String designation = resultat.getString( "designation" );
			    String etat = resultat.getString( "etat" );
			    int poids = resultat.getInt( "poids" );
			    String affectataire = resultat.getString( "affectataire" );
			    String option_Id = resultat.getString( "options_Id" );
			    String typeColis_Id = resultat.getString( "typeColis_Id" );
			    
			    results[i] =  idColis + ", "+ designation + ", " + etat + ", " + poids + ", " + affectataire+ ", " + option_Id+ ", " + typeColis_Id;
			    
			    System.out.println(results[i]);
			    /* Traiter ici les valeurs r�cup�r�es. */
			    i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
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
	
	private static String _getAllColis = "SELECT * FROM colis;";
}
