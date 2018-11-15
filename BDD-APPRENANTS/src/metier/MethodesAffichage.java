package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Activite;
import model.Apprenant;

public class MethodesAffichage {

	
	
	/**
	 * Affiche tous les apprenants sous forme de liste 'nom prénom'
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static void afficherListeApprenants() throws ClassNotFoundException, SQLException {
		for (Apprenant apprenant : Requetes.getAllApprenantsAvecMapping()) {

			System.out.println(apprenant.getNom() + " " + apprenant.getPrenom());
		}
	}

	/**
	 * Affiche tous les noms des apprenants sous forme de liste sur une ligne
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void afficherListeNomsApprenantsEnLigne() throws ClassNotFoundException, SQLException {
		for (Apprenant apprenant : Requetes.getAllApprenantsAvecMapping()) {
			System.out.print("|" + apprenant.getNom());
		}
		System.out.println("|\n");
	}

	
	/**
	 * Affiche la liste des apprenants pratiquant une activité dont l'id a été passé en paramètre
	 * (sous la forme 'prénom nom')
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void afficherApprenantsParActiviteById(int id) throws ClassNotFoundException, SQLException {

		System.out.println(
				"\nListe des apprenants qui pratiquent l'activité : " + Requetes.getActiviteById(id).getNomActivite());
		ArrayList<Apprenant> apprenants = Requetes.getApprenantsByActiviteById(id);
		if (apprenants.size() > 0) {
			for (Apprenant apprenant : apprenants) {
				System.out.println(apprenant.getPrenom() + " " + apprenant.getNom());
			}
		} else {
			System.out.println("Aucun apprenant ne pratique cette activité !");
		}

	}

	/**
	 * Affiche un menu qui permet d'entrer le nom de l'apprenant dont on veut voir les activités
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void choisirApprenantDontOnVeutVoirLesActivites() throws SQLException, ClassNotFoundException {
		System.out.println("Activités pratiquées par un des apprenants dont voici la liste : ");
		afficherListeNomsApprenantsEnLigne();
		boolean continuer = true;
		do {
			System.out.println(
					"Tapez le nom de l'apprenant dont vous voulez connaître les activités ou ' 0 ' pour quitter : ");
			Scanner sc = new Scanner(System.in);
			String entree = sc.nextLine();
			if (entree.equals("0")) {
				continuer = false;
			} else {
				if (!Requetes.checkIfNomExiste(entree)) {
					System.out.println("Cet apprenant n'est pas dans la liste !");
				} else {
					Apprenant apprenant = Requetes.getApprenantByName(entree);
					if (Requetes.getActivitesByApprenantId(apprenant.getIdApprenant()).size() > 0) {
						MethodesAffichage.afficheActivites(Requetes.getActivitesByApprenantId(apprenant.getIdApprenant()));
					} else {
						System.out.println("Aucune activité !");
					}
				}
				System.out.println();
			}
		} while (continuer);
	}

	/**
	 * Affiche un menu qui permet de choisir l'activité dont on souhaite voir par qui elle est pratiquée 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void choisirUneActiviteById() throws ClassNotFoundException, SQLException {
		for (Activite activite : Requetes.getAllActivitesAvecmapping()) {
			System.out.println(activite.getIdActivite() + " : " + activite.getNomActivite());
		}
		boolean continuer = true;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(
					"Indiquez le numéro de l'activité dont vous souhaitez voir quels apprenants la pratiquent (ou 0 pour quitter) : ");
			String rep = sc.nextLine();
			if (rep.equals("0")) {
				continuer = false;
			} else {
				MethodesAffichage.afficherApprenantsParActiviteById(Integer.parseInt(rep));
			}

		} while (continuer);
	}

	
	/**
	 * Affiche la liste des apprenants par région
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void afficherApprenantsParRegion() throws ClassNotFoundException, SQLException {
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

	
	/**
	 * Affiche la liste des activités qui ne sont pratiquées par personne
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void afficherActivitesQuePersonneNefait() throws ClassNotFoundException, SQLException {

		System.out.println("\nActivités que personne ne pratique : ");
		ArrayList<Activite> activites = Requetes.getActivitesQuePersonneNeFait();
		if (activites.size() > 0) {
			for (Activite activite : activites) {
				System.out.println(activite.getNomActivite());
			}
		} else {
			System.out.println("Aucune activité n'est pratiquée par au moins un apprenant !");

		}
	}
	
	/**
	 * Affiche la liste des activités
	 * @param liste_activites
	 */
	public static void afficheActivites(ArrayList<Activite> liste_activites) {
		for (Activite activite : liste_activites) {
			System.out.println(activite.getNomActivite());
		}
	}

}
