package main;
import java.sql.SQLException;

import metier.MethodesAffichage;
import metier.RequetesUpdate;

public class Application {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		

		MethodesAffichage.afficherListeApprenants();
		MethodesAffichage.afficherApprenantsParRegion();
		MethodesAffichage.choisirApprenantDontOnVeutVoirLesActivites();
		MethodesAffichage.choisirUneActiviteById();
		MethodesAffichage.afficherActivitesQuePersonneNefait();
		
		RequetesUpdate.updateNomApprenant(17,"ZébuModif");
	}
	
}
