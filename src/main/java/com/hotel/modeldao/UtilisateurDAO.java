package main.java.com.hotel.modeldao;

import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Admin on 03/04/2017.
 */
public class UtilisateurDAO extends DAO {
    private static List<Observer> observers = new ArrayList<>();


    public UtilisateurDAO() {
        super();
    }

    public static void addObserver(Observer obs) {
        observers.add(obs);
    }

    public static void updateObservers(Object o) {
        for (Observer obs : observers) {
            obs.update(null, o);
        }
    }

    public static void deleteObservers() {
        observers = new ArrayList<>();
    }

    /**
     * Insert a new Utilisateur into the database.
     *
     * @param utilisateur
     */
    public void create(Utilisateur utilisateur) {

        try {
            super.saveOrUpdate(utilisateur);
            updateObservers(StringRessources.MSG_USER_SUCCES);
        } catch (DataAccessLayerException e) {
            updateObservers(StringRessources.MSG_USER_ERREUR);
        }
    }

    /**
     * Delete a detached Utilisateur from the database.
     *
     * @param utilisateur
     */
    public void delete(Utilisateur utilisateur) throws DataAccessLayerException {
        super.delete(utilisateur);
    }

    /**
     * Find an Utilisateur by its primary key.
     *
     * @param id
     * @return
     */
    public Utilisateur find(int id) throws DataAccessLayerException {
        return (Utilisateur) super.find(Utilisateur.class, id);
    }

    /**
     * Updates the state of a detached Utilisateur.
     *
     * @param utilisateur
     */
    public void update(Utilisateur utilisateur) throws DataAccessLayerException {
        super.saveOrUpdate(utilisateur);
    }

    /**
     * Finds all Utilisateur in the database.
     *
     * @return
     */
    public List<Utilisateur> findAll() throws DataAccessLayerException {
        return super.findAll(Utilisateur.class);
    }
}
