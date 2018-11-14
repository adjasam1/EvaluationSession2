package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.AccesBD;
import model.Activite;
import model.Apprenant;
import model.Region;

public class Requetes {
	
	public static Region getRegionByIdAvecMapping(int idRegion) throws SQLException {
		
		PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement("SELECT * FROM region WHERE idRegion = ?");
		preparedStatement.setInt(1, idRegion);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();
		
		return Mapping.mapperRegion(resultat);
	}
	
	

	public static ArrayList<Apprenant> getAllApprenantsAvecMapping() throws ClassNotFoundException, SQLException {
		ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
		String requete = "SELECT * FROM apprenant ORDER BY idApprenant";
		ResultSet resultat = AccesBD.executerQuery(requete);

		while (resultat.next()) {
			Apprenant apprenant = Mapping.mapperApprenant(resultat);
			apprenants.add(apprenant);
		}

		return apprenants;
	}
	
	
	// methode jdbc
	
		public static void ajouterApprenant(Apprenant apprenant) throws SQLException {
			
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("INSERT INTO apprenant VALUES( null , ? , ? , ? , ? , ? , ? )");
			prepareStatement.setString(1, apprenant.getPrenom());
			prepareStatement.setString(2, apprenant.getNom());
			prepareStatement.setDate(3, apprenant.getDateNaissance());
			prepareStatement.setString(4, apprenant.geteMail());
			prepareStatement.setString(5, apprenant.getPhoto());
			prepareStatement.setInt(6, apprenant.getRegion().getIdRegion());
			prepareStatement.executeUpdate();
		}
		
		public static void modifierRegion(Region region) throws SQLException {
			
			try {
				PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement("UPDATE categorie SET nomRegion = ? WHERE idRegion = ? ");
				preparedStatement.setString(1, region.getNomRegion());
				preparedStatement.setInt(2, region.getIdRegion());
				preparedStatement.executeUpdate();
				System.out.println("Modification effectuee pour la region : " + region);
		
			}
			catch (SQLException e) {
				System.out.println("Erreur lors de la modification !");
			}
		}
		
		public static void supprimerActivite(Activite activite) {

			Statement statement = null;

			try {
				statement = AccesBD.getConnection().createStatement();
				String sql = "DELETE FROM activite WHERE idActivite = " + activite.getIdActivite();
				statement.executeUpdate(sql);
				System.out.println("Suppression de l'activite " + activite + " effectuee");
			}
			catch(SQLException e){
				System.out.println("Erreur lors de la suppression du pilote !");
			}
		}
	
		public static ArrayList<Activite> getActivitesByApprenantId(int id) throws SQLException {
			ArrayList<Activite> activites = new ArrayList<>();
			PreparedStatement preparedStatement = 
				AccesBD.getConnection().prepareStatement("SELECT activite.*  FROM apprenant, liste_activites, activite WHERE (liste_activites.idApprenant = ? && liste_activites.idActivite = activite.idActivite && apprenant.idApprenant = ?)");
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, id);
				ResultSet resultat = preparedStatement.executeQuery();
				while (resultat.next()) {
					Activite activite = Mapping.mapperActivite(resultat);
					activites.add(activite);
				}
			return activites;
		}
		
		public static boolean checkIfNomExiste(String nom) throws SQLException, ClassNotFoundException {
			String requete = "SELECT * FROM apprenant WHERE nom = \"" + nom +"\"";
			ResultSet resultat = AccesBD.executerQuery(requete);
			return resultat.next();
		}
		
		public static void afficheActivites(ArrayList<Activite> liste_activites) {
			for (Activite activite : liste_activites) {
				System.out.println(activite.getNomActivite());
			}
		}
}
