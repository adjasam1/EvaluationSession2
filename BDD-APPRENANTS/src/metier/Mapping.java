package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Activite;
import model.Apprenant;
import model.Region;

public class Mapping {
	
	public Mapping() {
		
	}
	
	// methode pour instancier objet Apprenant
	public static Apprenant mapperApprenant(ResultSet resultat) throws SQLException {
		
		Apprenant apprenant = new Apprenant();
		apprenant.setIdApprenant(resultat.getInt("idApprenant"));
		apprenant.setPrenom(resultat.getString("prenom"));
		apprenant.setNom(resultat.getString("nom"));
		apprenant.setDateNaissance(resultat.getDate("dateNaissance"));
		apprenant.seteMail(resultat.getString("eMail"));
		apprenant.setPhoto(resultat.getString("photo"));
		apprenant.setIdRegion(Requetes.getRegionByIdAvecMapping(resultat.getInt("idRegion")));
		
		return apprenant;
	}
	
	// methode pour instancier objet Activite
	public static Activite mapperActivite(ResultSet resultat) throws SQLException {
		
		Activite activite = new Activite(0, null);
		activite.setIdActivite(resultat.getInt("idActivite"));
		activite.setNomActivite(resultat.getString("nomActivite"));
		
		return activite;
		
	}
	
	// methode pour instancier objet Region
	public static Region mapperRegion(ResultSet resultat) throws SQLException {
		
		Region region = new Region(0, null);
		region.setIdRegion(resultat.getInt("idRegion"));
		region.setNomRegion(resultat.getString("nomRegion"));
		
		return region;
		
		
	}

}
