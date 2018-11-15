package metier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import connection.AccesBD;
import model.Apprenant;

public class RequetesUpdate {

	
	public static void updateNomApprenant(int id, String nouveauNom) throws ClassNotFoundException, SQLException {
		
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("UPDATE apprenant SET nom = ? WHERE idApprenant = ?");
			prepareStatement.setString(1, nouveauNom);
			prepareStatement.setInt(2, id);
			prepareStatement.executeUpdate();
			System.out.println("Modification effectuée pour l'apprenant n° : " + Requetes.getApprenantById(id).getIdApprenant());
		} catch (SQLException e) {
			System.out.println("Erreur lors de la modification !");
		}
	}
	
	public static void ajouterApprenant(Apprenant apprenant) throws SQLException {	
		PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("INSERT INTO apprenant VALUES( null , ? , ? , ? , ? , ? , ? )");
		prepareStatement.setString(1, apprenant.getPrenom());
		prepareStatement.setString(2, apprenant.getNom());
		java.sql.Date sqlDate = new java.sql.Date(apprenant.getDateNaissance().getTime());
		
		prepareStatement.setDate(3, sqlDate);
		prepareStatement.setString(4, apprenant.geteMail());
		prepareStatement.setString(5, apprenant.getPhoto());
		prepareStatement.setInt(6, apprenant.getRegion().getIdRegion());
		prepareStatement.executeUpdate();
	}
	
	
	// ajouter 2 activites à un apprenant
	public static void ajouterActiviteAUnApprenant(int idApprenant, int idActivite) throws ClassNotFoundException, SQLException {
		try {
		PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("INSERT INTO liste_activites (idActivite, idApprenant) VALUES (?,?)");		
		prepareStatement.setInt(1, idActivite);
		prepareStatement.setInt(2, idApprenant);
		prepareStatement.executeUpdate();
		System.out.println("Ajout d'activite effectué.");
		} catch (SQLException e) {
			System.out.println("L'ajout de l'activité a échoué !");
		}
	}
	
	// supprimer apprenant
				public static void supprimerApprenant(Apprenant apprenant) {

					Statement statement = null;

					try {
						statement = AccesBD.getConnection().createStatement();
						String sql = "DELETE FROM apprenant WHERE idApprenant = " + apprenant.getIdApprenant();
						statement.executeUpdate(sql);
						System.out.println("Suppression de l'apprenant " + apprenant + " effectuee");
					}
					catch(SQLException e){
						System.out.println("Erreur lors de la suppression du pilote !");
					}
				}

}