package main;
import java.sql.SQLException;
import java.text.ParseException;

import metier.MethodesAffichage;
import metier.Requetes;
import metier.RequetesUpdate;
import model.Apprenant;

public class Application {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		// modification region d'apprenant
				Apprenant geraldine = Requetes.getApprenantById(1);
				geraldine.getRegion().setIdRegion(2);
				Requetes.modifierRegionApprenant(geraldine);
				
				Apprenant mathieu = Requetes.getApprenantById(7);
				mathieu.getRegion().setIdRegion(3);
				Requetes.modifierRegionApprenant(mathieu);
				
				Apprenant davidP = Requetes.getApprenantById(11);
				davidP.getRegion().setIdRegion(2);
				Requetes.modifierRegionApprenant(davidP);
				
				Apprenant samS = Requetes.getApprenantById(13);
				samS.getRegion().setIdRegion(2);
				Requetes.modifierRegionApprenant(samS);
				
				Apprenant davidS = Requetes.getApprenantById(15);
				davidS.getRegion().setIdRegion(3);
				Requetes.modifierRegionApprenant(davidS);
				
				Apprenant cedric = Requetes.getApprenantById(16);
				cedric.getRegion().setIdRegion(3);
				Requetes.modifierRegionApprenant(cedric);
		
		int choix = 0;

		while (choix != 10) {

			System.out.println("MENU");
			System.out.println("Choix 1 : Affichez les noms et prénoms de tous les apprenant(e)s.");
			System.out.println("Choix 2 : Affichez la liste des apprenants pour chaque région.");
			System.out.println("Choix 3 : Recherchez un apprenant(e) (par son nom) et afficher la liste des activités qu’il ou qu’elle pratique.");
			System.out.println("Choix 4 : Recherchez les apprenants qui pratiquent une activité passée en paramètre d’une méthode.");
			System.out.println("Choix 5 : Ajoutez un(e) nouvel(le) apprenant(e).");
			System.out.println("Choix 6 : Affectez une activités à un(e) apprenant(e)");
			System.out.println("Choix 7 : Affichez la liste des activités que personne ne fait.");
			System.out.println("Choix 8 : Mettre à jour une région d’un(e) apprenant(e).");
			System.out.println("Choix 9 : Supprimez un(e) apprenant(e).");
			System.out.println("Choix 10 : Quitter");
			System.out.print("Entrez votre choix : ");
			choix = saisie.nextInt();
			
			switch (choix) {

			case 1:
				MethodesAffichage.afficherListeApprenants();
				break;

			case 2:
				MethodesAffichage.afficherApprenantsParRegion();
				break;

			case 3:
				MethodesAffichage.choisirApprenantDontOnVeutVoirLesActivites();
				break;

			case 4:
				
				break;

			case 5:
				
				break;
				
			case 6:
				
				break;	
				
			case 7:
				MethodesAffichage.afficherActivitesQuePersonneNefait();
				break;
				
			case 8:
//				System.out.println("Entrez l'identifiant de l'apprenant : ");
//				int idA = saisie.nextInt();
//				String prenom = Requetes.getApprenantByIdAvecMapping(idA);
//				Apprenant prenom = Requetes.getApprenantByIdAvecMapping(idA);
//				prenom.getRegion().setIdRegion(2);
//				Requetes.modifierRegionApprenant(prenom);
				break;
				
			case 9:
				
				break;
				
			case 10:
				break;

			default:
				System.out.println("Veuillez entrer un choix valable !");
			}
		}
		
		System.out.println("\n========================================\n");
		
		
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