package main.java.com.hotel.model;
// Generated 30 avr. 2017 02:56:43 by Hibernate Tools 5.2.0.CR1

import javax.persistence.*;
import java.util.Date;

/**
 * Client generated by hbm2java
 */
@Entity
@Table(name = "client", catalog = "hotel")
public class Client implements java.io.Serializable {

	private int idClient;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String tel;

	public Client() {
	}

	public Client(int idClient) {
		this.idClient = idClient;
	}

	public Client(int idClient, String nom, String prenom, Date dateNaissance, String tel) {
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.tel = tel;

	}

	@Id

	@Column(name = "idClient", unique = true, nullable = false)
	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	@Column(name = "nom", length = 50)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "prenom", length = 50)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateNaissance", length = 10)
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Column(name = "tel", length = 15)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


}
