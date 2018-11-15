package metier;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Activite;
import model.Apprenant;
import model.Region;

public class MethodesAffichage {

	/**
	 * Affiche tous les apprenants sous forme de liste 'nom prénom'
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static void afficherListeApprenants() throws ClassNotFoundException, SQLException {
		for (Apprenant apprenant : Requetes.getAllApprenants()) {

			System.out.println(apprenant.getNom() + " " + apprenant.getPrenom());
		}
	}

	/**
	 * Affiche tous les noms des apprenants sous forme de liste sur une ligne
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void afficherListeNomsApprenantsEnLigne() throws ClassNotFoundException, SQLException {
		for (Apprenant apprenant : Requetes.getAllApprenants()) {
			System.out.print("| id:" + apprenant.getIdApprenant() + "-" + apprenant.getNom() + " ");
		}
		System.out.println(" |\n");
	}

	/**
	 * Affiche la liste des apprenants pratiquant une activité dont l'id a été passé
	 * en paramètre (sous la forme 'prénom nom')
	 * 
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
	 * Affiche un menu qui permet d'entrer le nom de l'apprenant dont on veut voir
	 * les activités
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void choisirApprenantDontOnVeutVoirLesActivites() throws SQLException, ClassNotFoundException {
		System.out.println("Activités pratiquées par un des apprenants dont voici la liste : ");
		afficherListeNomsApprenantsEnLigne();
		boolean continuer = true;
		do {
			System.out.println(
					"Tapez le NOM de l'apprenant dont vous voulez connaître les activités ou ' 0 ' pour quitter : ");
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
						afficheActivites(Requetes.getActivitesByApprenantId(apprenant.getIdApprenant()));
					} else {
						System.out.println("Aucune activité !");
					}
				}
				System.out.println();
			}
		} while (continuer);
	}

	/**
	 * Affiche un menu qui permet de choisir l'activité dont on souhaite voir par
	 * qui elle est pratiquée
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void choisirUneActiviteById() throws ClassNotFoundException, SQLException {
		for (Activite activite : Requetes.getAllActivites()) {
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
				afficherApprenantsParActiviteById(Integer.parseInt(rep));
			}

		} while (continuer);
	}

	/**
	 * Affiche la liste des apprenants par région
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void afficherApprenantsParRegion() throws ClassNotFoundException, SQLException {
		System.out.println("\n========================================\n");

		// afficher liste apprenants pour chaque region
		ArrayList<String> ileDeFrance = new ArrayList<String>();
		ArrayList<String> paysDeLoire = new ArrayList<String>();
		ArrayList<String> aquitaine = new ArrayList<String>();

		for (Apprenant apprenant : Requetes.getAllApprenants()) {

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
	 * 
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
	 * 
	 * @param liste_activites
	 */
	public static void afficheActivites(ArrayList<Activite> liste_activites) {
		for (Activite activite : liste_activites) {
			System.out.println(activite.getNomActivite());
		}
	}

	/**
	 * Crée un nouvel Apprenant en fonction des entrées clavier de l'utilisateur
	 * Ajoute cet Apprenant à la BDD
	 * 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void creerApprenantEtAjouterABDD() throws SQLException, ParseException {

		Apprenant nouveauApprenant = new Apprenant();
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez le prénom de l'apprenant : ");
		nouveauApprenant.setPrenom(sc.nextLine());
		System.out.println("Entrez le nom de l'apprenant : ");
		nouveauApprenant.setNom(sc.nextLine());
		System.out.println("Entrez la date de naissance au format AAAA-MM-JJ :");
		java.sql.Date dateNaissance = Date.valueOf(sc.nextLine());
		nouveauApprenant.setDateNaissance(dateNaissance);
		System.out.println("Entrez l'adresse mail :");
		nouveauApprenant.seteMail(sc.nextLine());
		nouveauApprenant.setPhoto("");
		System.out.println("Entrez l'identifiant de région [1,2 ou3] :");
		int idRegion = Integer.parseInt(sc.nextLine());
		Region region = new Region();
		nouveauApprenant.setRegion(Requetes.getRegionById(idRegion));
		RequetesUpdate.ajouterApprenant(nouveauApprenant);
	}

	/**
	 * Choisir un apprenant et lui ajouter une activité
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static void choisirApprenantEtAjouterActivite() throws ClassNotFoundException, SQLException {
		boolean continuer = true;
		do {
			System.out.println("Ajouter une activité à un apprenant : ");
			afficherListeNomsApprenantsEnLigne();
			System.out.println("Indiquer l'ID de l'apprenant concerné :");
			Scanner sc = new Scanner(System.in);
			int idApprenant = Integer.parseInt(sc.nextLine());
			afficherActivitesAvecLeurId();
			System.out.println("Indiquer l'id de l'activité concernée :");
			int idActivite = Integer.parseInt(sc.nextLine());
			RequetesUpdate.ajouterActiviteAUnApprenant(idApprenant, idActivite);
			System.out.println("L'apprenant " + Requetes.getApprenantById(idApprenant).getNom()
					+ " pratique désormais les activités suivantes :");
			afficheActivites(
					Requetes.getActivitesByApprenantId(Requetes.getApprenantById(idApprenant).getIdApprenant()));
			System.out.println("Faut-il ajouter une autre activité (o/n) ? ");
			if (!sc.nextLine().toLowerCase().equals("o")) {
				continuer = false;
			}
		} while (continuer);
	}

	/**
	 * Affiche les activités avec leur id
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static void afficherActivitesAvecLeurId() throws ClassNotFoundException, SQLException {
		for (Activite activite : Requetes.getAllActivites()) {

			System.out.println(activite.getIdActivite() + " " + activite.getNomActivite());
		}

	}
}
