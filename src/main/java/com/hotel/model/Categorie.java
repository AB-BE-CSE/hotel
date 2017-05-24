package main.java.com.hotel.model;
// Generated 30 avr. 2017 02:56:43 by Hibernate Tools 5.2.0.CR1

import javafx.beans.property.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/** @author Ilies Bouyacoub */

@Entity
@Table(name = "categorie", catalog = "hotel")
public class Categorie implements java.io.Serializable {

    private IntegerProperty idCategorie;
    private StringProperty nom;
    private DoubleProperty prix;
    private StringProperty description;
    public Categorie() {
        idCategorie = new SimpleIntegerProperty();
        description = new SimpleStringProperty();

        this.nom = new SimpleStringProperty();
        this.prix = new SimpleDoubleProperty();
    }

    public Categorie(String nom, Double prix,String description) {
        idCategorie = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty(nom);
        this.prix = new SimpleDoubleProperty(prix);
        this.description = new SimpleStringProperty(description);

    }

    public IntegerProperty idCategorieProperty() {
        return idCategorie;
    }


    public StringProperty nomProperty() {
        return nom;
    }


    public DoubleProperty prixProperty() {
        return prix;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idCategorie", unique = true, nullable = false)
    public Integer getIdCategorie() {
        return this.idCategorie.get();
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie.set(idCategorie);
    }

    @Column(name = "nom", length = 20)
    public String getNom() {
        return this.nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }



    @Column(name = "prix", precision = 22, scale = 0)
    public Double getPrix() {
        return this.prix.get();
    }

    public void setPrix(Double prix) {
        this.prix.set(prix);
    }
    @Column(name = "description", length = 65535)
    public String getDescription() {
        return this.description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }


    public StringProperty descriptionProperty() {
        return description;
    }

    @Override
    public String toString() {
        return getNom();
    }


}
