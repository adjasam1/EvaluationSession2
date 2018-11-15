package main;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import metier.MethodesAffichage;
import metier.RequetesUpdate;	

public class Application {

	public static Scanner saisie = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		/**
		 * 
		 * MENU PRINCIPAL
		 * 
		 */

		boolean continuer = true;

		do {

			affichageDuMenuPrincipal();

			int choix = choixMenuPrincipal();

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
				MethodesAffichage.choisirUneActiviteById();
				break;

			case 5:
				MethodesAffichage.creerApprenantEtAjouterABDD();
				break;

			case 6:
				MethodesAffichage.choisirApprenantEtAjouterActivite();
				break;

			case 7:
				MethodesAffichage.afficherActivitesQuePersonneNefait();
				break;

			case 8:
				RequetesUpdate.updateNomApprenant();
				break;

			case 9:
				RequetesUpdate.supprimerApprenant();
				break;

			case 10:
				continuer = false;
				break;

			default:
				System.out.println("Veuillez entrer un choix valable !");

			}

		} while (continuer);

		/*
		 * Modification region d'apprenant (a été utilisé pour mettre à jour la BDD avec
		 * les bonnes valeurs d'idRegion pour certains apprenants)
		 * 
		 * Apprenant geraldine = Requetes.getApprenantById(1);
		 * geraldine.getRegion().setIdRegion(2);
		 * Requetes.modifierRegionApprenant(geraldine);
		 * 
		 * Apprenant mathieu = Requetes.getApprenantById(7);
		 * mathieu.getRegion().setIdRegion(3);
		 * Requetes.modifierRegionApprenant(mathieu);
		 * 
		 * Apprenant davidP = Requetes.getApprenantById(11);
		 * davidP.getRegion().setIdRegion(2); Requetes.modifierRegionApprenant(davidP);
		 * 
		 * Apprenant samS = Requetes.getApprenantById(13);
		 * samS.getRegion().setIdRegion(2); Requetes.modifierRegionApprenant(samS);
		 * 
		 * Apprenant davidS = Requetes.getApprenantById(15);
		 * davidS.getRegion().setIdRegion(3); Requetes.modifierRegionApprenant(davidS);
		 * 
		 * Apprenant cedric = Requetes.getApprenantById(16);
		 * cedric.getRegion().setIdRegion(3); Requetes.modifierRegionApprenant(cedric);
		 */
	}

	/**
	 * Affiche le menu principal
	 */

	public static void affichageDuMenuPrincipal() {
		System.out.println("\n\nMENU PRINCIPAL");
		System.out.println("==============");
		System.out.println("Choix 1 : Affichez les noms et prénoms de tous les apprenant(e)s.");
		System.out.println("Choix 2 : Affichez la liste des apprenants pour chaque région.");
		System.out.println(
				"Choix 3 : Recherchez un apprenant(e) (par son nom) et afficher la liste des activités qu’il ou qu’elle pratique.");
		System.out.println(
				"Choix 4 : Recherchez les apprenants qui pratiquent une activité passée en paramètre d’une méthode.");
		System.out.println("Choix 5 : Ajoutez un(e) nouvel(le) apprenant(e) à la base de données.");
		System.out.println("Choix 6 : Affectez une activité à un(e) apprenant(e)");
		System.out.println("Choix 7 : Affichez la liste des activités que personne ne fait.");
		System.out.println("Choix 8 : Modifiez le nom d’un(e) apprenant(e).");
		System.out.println("Choix 9 : Supprimez un(e) apprenant(e).");
		System.out.println("Choix 10 : Quitter");
		System.out.println(
				"================================================================================================================");
	}

	/**
	 * Demande à l'utilisateur son choix pour le menu principal
	 * 
	 * @return
	 */

	public static int choixMenuPrincipal() {
		System.out.println("Entrez votre choix : ");
		String entree = saisie.nextLine();
		boolean isNumeric = entree.chars().allMatch(x -> Character.isDigit(x));
		if (!isNumeric) {
			System.out.println("Veuillez entrer un choix valide.");
			return 0;
		}
		int choix = Integer.parseInt(entree);
		if (choix < 1 || choix > 10) {
			System.out.println("Veuillez entrer un choix valide.");
			return 0;
		}
		return choix;

	}
}