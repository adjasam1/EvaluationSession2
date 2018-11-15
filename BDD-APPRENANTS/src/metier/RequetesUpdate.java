package metier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.AccesBD;

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


}