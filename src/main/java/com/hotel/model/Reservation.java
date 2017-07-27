package main.java.com.hotel.model;
// Generated 30 avr. 2017 02:56:43 by Hibernate Tools 5.2.0.CR1

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Abderrahmane Benyettou
 */

@Entity
@Table(name = "reservation", catalog = "hotel")
public class Reservation implements java.io.Serializable {

    private IntegerProperty idReservation;
    private Set<Chambre> chambres = new HashSet<>(0);
    private Client client;
    private Facture facture;
    private Utilisateur utilisateur;
    private ObjectProperty<Date> dateReservation;
    private ObjectProperty<Date> dateArrive;
    private ObjectProperty<Date> dateSortie;

    public Reservation() {
        this.idReservation = new SimpleIntegerProperty();

        this.dateReservation = new SimpleObjectProperty<>();
        this.dateArrive = new SimpleObjectProperty<>();
        this.dateSortie = new SimpleObjectProperty<>();
    }

    public Reservation(int idReservation) {
        this.idReservation = new SimpleIntegerProperty(idReservation);
        this.dateReservation = new SimpleObjectProperty<>();
        this.dateArrive = new SimpleObjectProperty<>();
        this.dateSortie = new SimpleObjectProperty<>();
    }

    public Reservation(int idReservation, Set<Chambre> chambres, Client client, Facture facture, Utilisateur utilisateur,
                       Date dateReservation, Date dateArrive, Date dateSortie) {
        this.idReservation = new SimpleIntegerProperty(idReservation);
        this.chambres = chambres;
        this.client = client;
        this.facture = facture;
        this.utilisateur = utilisateur;
        this.dateReservation = new SimpleObjectProperty<>(dateReservation);
        this.dateArrive = new SimpleObjectProperty<>(dateArrive);
        this.dateSortie = new SimpleObjectProperty<>(dateSortie);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idReservation", unique = true, nullable = false)
    public int getIdReservation() {
        return this.idReservation.get();
    }

    public void setIdReservation(int idReservation) {
        this.idReservation.set(idReservation);
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "chambre_reservation", catalog = "hotel", joinColumns = {
            @JoinColumn(name = "id_reservation", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_chambre",
                    nullable = false, updatable = false)})
    public Set<Chambre> getChambre() {
        return this.chambres;
    }

    public void setChambre(Set<Chambre> chambres) {
        this.chambres = chambres;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "id_client")
    public Client getClient() {
        return this.client;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "id_facture")
    public Facture getFacture() {
        return this.facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dateReservation", length = 10)
    public Date getDateReservation() {
        return this.dateReservation.get();
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation.set(dateReservation);
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dateArrive", length = 10)
    public Date getDateArrive() {
        return this.dateArrive.get();
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive.set(dateArrive);
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dateSortie", length = 10)
    public Date getDateSortie() {
        return this.dateSortie.get();
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie.set(dateSortie);
    }

    public IntegerProperty idReservationProperty() {
        return idReservation;
    }

    public ObjectProperty<Date> dateReservationProperty() {
        return dateReservation;
    }

    public ObjectProperty<Date> dateArriveProperty() {
        return dateArrive;
    }

    public ObjectProperty<Date> dateSortieProperty() {
        return dateSortie;
    }
}
