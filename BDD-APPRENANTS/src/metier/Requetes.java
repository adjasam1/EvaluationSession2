package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.AccesBD;
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
	

}
