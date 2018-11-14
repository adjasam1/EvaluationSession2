package main;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import metier.Requetes;
import model.Apprenant;
import model.Region;

public class Application {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// afficher noms et prénoms des apprenants
		for (Apprenant apprenant : Requetes.getAllApprenantsAvecMapping()) {
			
			System.out.println(apprenant.getNom() + " " + apprenant.getPrenom());
		}
		
		System.out.println("\n========================================\n");
		
		// afficher liste apprenants pour chaque region
		ArrayList<String> ileDeFrance = new ArrayList<String>();
		ArrayList<String> paysDeLoire = new ArrayList<String>();
		ArrayList<String> aquitaine = new ArrayList<String>();
		
		for (Apprenant apprenant : Requetes.getAllApprenantsAvecMapping()) {
			
			if (apprenant.getRegion().getIdRegion() == 1) {
				ileDeFrance.add(apprenant.getPrenom() + " " + apprenant.getNom());
			}
			
			if (apprenant.getRegion().getIdRegion() == 2) {
				paysDeLoire.add(apprenant.getPrenom() + " " + apprenant.getNom());
			}
			
			if (apprenant.getRegion().getIdRegion() == 3) {
				aquitaine.add(apprenant.getPrenom() + " " + apprenant.getNom());				
			}
			
		}
		System.out.println("Liste des apprenants pour l'Ile de France :");
		System.out.println(ileDeFrance);
		
		System.out.println("\nListe des apprenants pour le Pays de Loire :");
		System.out.println(paysDeLoire);
		
		System.out.println("\nListe des apprenants pour l'Aquitaine :");
		System.out.println(aquitaine);
		
		System.out.println("\n========================================\n");
		
		
	}
	
	
	
	
	
}
