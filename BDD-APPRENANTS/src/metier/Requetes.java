package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.AccesBD;
import metier.Mapping;
import model.Activite;
import model.Apprenant;
import model.Region;

public class Requetes {

	
	/**
	 * Retourne la région dont l'id a été passé en argument
	 * @param idRegion
	 * @return
	 * @throws SQLException
	 */
	public static Region getRegionById(int idRegion) throws SQLException {

		PreparedStatement preparedStatement = AccesBD.getConnection()
				.prepareStatement("SELECT * FROM region WHERE idRegion = ?");
		preparedStatement.setInt(1, idRegion);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();

		return Mapping.mapperRegion(resultat);
	}

	
	/**
	 * Retourne la liste de toutes les activités 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Activite> getAllActivites() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> activites = new ArrayList();
		String requete = "SELECT * FROM activite ORDER BY idActivite";
		ResultSet resultat = AccesBD.executerQuery(requete);

		while (resultat.next()) {
			Activite activite = Mapping.mapperActivite(resultat);
			activites.add(activite);
		}

		return activites;
	}

	
	/**
	 * Retourne la liste de tous les apprenants
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Apprenant> getAllApprenants() throws ClassNotFoundException, SQLException {
		ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
		String requete = "SELECT * FROM apprenant ORDER BY idApprenant";
		ResultSet resultat = AccesBD.executerQuery(requete);

		while (resultat.next()) {
			Apprenant apprenant = Mapping.mapperApprenant(resultat);
			apprenants.add(apprenant);
		}

		return apprenants;
	}

	
	/**
	 * Retourne l'apprenant dont le nom a été passé en argument
	 * @param nom
	 * @return
	 * @throws SQLException
	 */
	
	public static Apprenant getApprenantByName(String nom) throws SQLException {
		PreparedStatement preparedStatement = AccesBD.getConnection()
				.prepareStatement("SELECT * FROM apprenant WHERE nom = ?");
		preparedStatement.setString(1, nom);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();
		return Mapping.mapperApprenant(resultat);

	}
	

	/**
	 * Retourne l'activité correspondant à l'id passé en argument
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static Activite getActiviteById(int id) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = AccesBD.getConnection()
				.prepareStatement("SELECT * FROM activite WHERE idActivite = ?");
		preparedStatement.setInt(1, id);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();
		return Mapping.mapperActivite(resultat);
	}

	
	/**
	 * Retourne la liste des activités pratiquées par un apprenant dont l'id a été passé en argument
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	
	public static ArrayList<Activite> getActivitesByApprenantId(int id) throws SQLException {
		ArrayList<Activite> activites = new ArrayList<>();
		PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement(
				"SELECT activite.*  FROM apprenant, liste_activites, activite WHERE (liste_activites.idApprenant = ? && liste_activites.idActivite = activite.idActivite && apprenant.idApprenant = ?)");
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		ResultSet resultat = preparedStatement.executeQuery();
		while (resultat.next()) {
			Activite activite = Mapping.mapperActivite(resultat);
			activites.add(activite);
		}
		return activites;
	}

	
	/**
	 * Retourne la liste des apprenants qui pratiquent une activité dont l'id a été passé en argument
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	
	public static ArrayList<Apprenant> getApprenantsByActiviteById(int id) throws SQLException {
		ArrayList<Apprenant> apprenants = new ArrayList<>();
		PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement(
				"SELECT apprenant.*  FROM apprenant, liste_activites, activite WHERE (liste_activites.idActivite = ? && activite.idActivite = ? && liste_activites.idApprenant = apprenant.idApprenant)");
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		ResultSet resultat = preparedStatement.executeQuery();
		while (resultat.next()) {
			Apprenant apprenant = Mapping.mapperApprenant(resultat);
			apprenants.add(apprenant);
		}
		return apprenants;
	}

	
	
	/**
	 * Vérfie si un nom d'apprenant passé en argument est présent dans la table `apprenant`
	 * @param nom
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean checkIfNomExiste(String nom) throws SQLException, ClassNotFoundException {
		String requete = "SELECT * FROM apprenant WHERE nom = \"" + nom + "\"";
		ResultSet resultat = AccesBD.executerQuery(requete);
		return resultat.next();
	}

	public static boolean checkIfApprenantIdExiste(int id) throws SQLException, ClassNotFoundException {
		String requete = "SELECT * FROM apprenant WHERE idApprenant = \"" + id + "\"";
		ResultSet resultat = AccesBD.executerQuery(requete);
		return resultat.next();
	}

	
	/**
	 * Retourne la liste des activités qui ne sont pratiquées par aucun apprenant
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Activite> getActivitesQuePersonneNeFait() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> activites = new ArrayList<>();
		String requete = ("SELECT *  FROM activite WHERE NOT EXISTS (select null from liste_activites where activite.idActivite = liste_activites.idActivite) ");
		ResultSet resultat = AccesBD.executerQuery(requete);
		while (resultat.next()) {
			Activite activite = Mapping.mapperActivite(resultat);
			activites.add(activite);
		}
		return activites;
	}

	
	/**
	 * Retourne un Apprenant selon son id
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static Apprenant getApprenantById(int id) throws ClassNotFoundException, SQLException {
		Apprenant apprenant = new Apprenant();
		String requete = "SELECT * FROM apprenant WHERE idApprenant = " + id;
		ResultSet resultat = AccesBD.executerQuery(requete);

		if (resultat.next()) {
			apprenant = Mapping.mapperApprenant(resultat);
			return apprenant;			
		} 
		return null;

	}
	
	/**
	 * Modifie l'id Region d'un Apprenant 
	 * @param apprenant
	 */
	public static void modifierRegionApprenant(Apprenant apprenant) {
		
		try {
			PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement("UPDATE apprenant SET idRegion = ? WHERE idApprenant = ? ");
			preparedStatement.setInt(1, apprenant.getRegion().getIdRegion());
			preparedStatement.setInt(2, apprenant.getIdApprenant());
			preparedStatement.executeUpdate();
			System.out.println("Modification effectuee pour la region de l'apprenant : " + apprenant);
	
		}
		catch (SQLException e) {
			System.out.println("Erreur lors de la modification !");
		}
	}
	
	
	

}
