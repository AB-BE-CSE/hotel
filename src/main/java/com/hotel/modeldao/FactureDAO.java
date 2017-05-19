package main.java.com.hotel.modeldao;

import main.java.com.hotel.model.Facture;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Admin on 03/04/2017.
 */
public class FactureDAO extends DAO {
    private static List<Observer> observers = new ArrayList<>();


    public FactureDAO() {
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
     * Insert a new Facture into the database.
     *
     * @param facture
     */
    public void create(Facture facture) {

        try {
            super.saveOrUpdate(facture);
//            updateObservers(StringRessource.MSG_ETD_SUCCES);
        } catch (DataAccessLayerException e) {
//            updateObservers(StringRessources.MSG_ETD_ERREUR);
        }
    }

    /**
     * Delete a detached Facture from the database.
     *
     * @param facture
     */
    public void delete(Facture facture) throws DataAccessLayerException {
        super.delete(facture);
    }

    /**
     * Find an Facture by its primary key.
     *
     * @param id
     * @return
     */
    public Facture find(int id) throws DataAccessLayerException {
        return (Facture) super.find(Facture.class, id);
    }

    /**
     * Updates the state of a detached Facture.
     *
     * @param facture
     */
    public void update(Facture facture) throws DataAccessLayerException {
        super.saveOrUpdate(facture);
    }

    /**
     * Finds all Facture in the database.
     *
     * @return
     */
    public List<Facture> findAll() throws DataAccessLayerException {
        return super.findAll(Facture.class);
    }
}
