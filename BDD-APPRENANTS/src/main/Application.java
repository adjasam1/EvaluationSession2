package main;
import java.sql.SQLException;
import java.text.ParseException;

import metier.MethodesAffichage;
import metier.Requetes;
import metier.RequetesUpdate;
import model.Apprenant;

public class Application {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		
		
		
		MethodesAffichage.afficherListeApprenants();
		MethodesAffichage.afficherApprenantsParRegion();
		MethodesAffichage.choisirApprenantDontOnVeutVoirLesActivites();
		MethodesAffichage.choisirUneActiviteById();
		
		MethodesAffichage.afficherActivitesQuePersonneNefait();
		
		RequetesUpdate.updateNomApprenant(17,"ZébuModif");
	
		MethodesAffichage.choisirApprenantEtAjouterActivite();

			Apprenant apprenant = Requetes.getApprenantById(17);
	
			if (apprenant != null) {
				RequetesUpdate.supprimerApprenant(apprenant);
			}
	MethodesAffichage.creerApprenantEtAjouterABDD();
}
}