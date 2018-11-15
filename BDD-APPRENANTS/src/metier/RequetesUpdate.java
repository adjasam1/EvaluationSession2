package metier;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
	
	
	// Ajouter apprenant
			Apprenant nouveauApprenant = new Apprenant();
			nouveauApprenant.setPrenom("Jack");
			nouveauApprenant.setNom("Daniel");
			String dateJack = "1846-11-23";
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateJack);
			nouveauApprenant.setDateNaissance(date);
			nouveauApprenant.seteMail("jack.daniel@whisky.com");
			nouveauApprenant.setPhoto(null);
			nouveauApprenant.setRegion();
			
			Requetes.ajouterApprenant(nouveauApprenant);


}