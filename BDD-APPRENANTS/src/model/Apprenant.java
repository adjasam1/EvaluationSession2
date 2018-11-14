package model;

import java.sql.Date;

public class Apprenant {

	private int id;
	private String prenom, nom, mail, photo;
	private Date dateDeNaissance;
	private Region region;
	/**
	 * @param id
	 * @param prenom
	 * @param nom
	 * @param mail
	 * @param photo
	 * @param dateDeNaissance
	 * @param region
	 */
	public Apprenant(int id, String prenom, String nom, String mail, String photo, Date dateDeNaissance,
			Region region) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.mail = mail;
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
		this.region = region;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "Apprenant [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + ", photo=" + photo
				+ ", dateDeNaissance=" + dateDeNaissance + ", region=" + region + "]";
	}
	
	
	
}
