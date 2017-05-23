package main.java.com.hotel.metier;

/**
 * Created by Admin on 18/05/2017.
 */
public interface Gestion {
    void ajouter();
    void modifier(Object object);
    void supprimer(Object toDelete);
    void chercher();
}
