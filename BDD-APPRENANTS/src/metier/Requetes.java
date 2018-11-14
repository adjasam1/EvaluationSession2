package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.AccesBD;
import model.Region;

public class Requetes {
	
	public static Region getRegionByIdAvecMapping(int idRegion) throws SQLException {
		
		PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement("SELECT * FROM region WHERE idRegion = ?");
		preparedStatement.setInt(1, idRegion);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();
		
		return Mapping.mapperRegion(resultat);
	}
	
	


}
