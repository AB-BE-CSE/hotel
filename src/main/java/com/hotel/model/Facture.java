package main.java.com.hotel.model;
// Generated 30 avr. 2017 02:56:43 by Hibernate Tools 5.2.0.CR1

import javafx.beans.property.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author  Abderrahmane Benyettou
 */
@Entity
@Table(name = "facture", catalog = "hotel")
public class Facture implements java.io.Serializable {

	private IntegerProperty idFacture;
	private ObjectProperty<Date> datePaiement;
	private DoubleProperty somme;

	public Facture() {
		this.idFacture = new SimpleIntegerProperty();
		this.datePaiement = new SimpleObjectProperty<>();
		this.somme = new SimpleDoubleProperty();
	}

	public Facture(int idFacture) {
		this.idFacture = new SimpleIntegerProperty(idFacture);
		this.datePaiement = new SimpleObjectProperty<>();
		this.somme = new SimpleDoubleProperty();
	}

	public Facture(int idFacture, Date datePaiement, Double somme) {
		this.idFacture = new SimpleIntegerProperty(idFacture);
		this.datePaiement = new SimpleObjectProperty<>(datePaiement);
		this.somme = new SimpleDoubleProperty(somme);

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idFacture", unique = true, nullable = false)
	public int getIdFacture() {
		return this.idFacture.get();
	}

	public void setIdFacture(int idFacture) {
		this.idFacture.set(idFacture);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "datePaiement", length = 10)
	public Date getDatePaiement() {
		return this.datePaiement.get();
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement.set(datePaiement);
	}

	@Column(name = "somme", precision = 22, scale = 0)
	public Double getSomme() {
		return this.somme.get();
	}

	public void setSomme(Double somme) {
		this.somme.set(somme);
	}

	public IntegerProperty idFactureProperty() {
		return idFacture;
	}

	public ObjectProperty<Date> datePaiementProperty() {
		return datePaiement;
	}

	public DoubleProperty sommeProperty() {
		return somme;
	}
}
