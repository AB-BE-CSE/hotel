package main.java.com.hotel.modeldao;

import main.java.com.hotel.model.Categorie;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Admin on 03/04/2017.
 */
public class CategorieDAO extends DAO {
    private static List<Observer> observers = new ArrayList<>();


    public CategorieDAO() {
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
     * Insert a new Categorie into the database.
     *
     * @param categorie
     */
    public void create(Categorie categorie) {

        try {
            super.saveOrUpdate(categorie);
//            updateObservers(StringRessource.MSG_ETD_SUCCES);
        } catch (DataAccessLayerException e) {
//            updateObservers(StringRessources.MSG_ETD_ERREUR);
        }
    }

    /**
     * Delete a detached Categorie from the database.
     *
     * @param categorie
     */
    public void delete(Categorie categorie) throws DataAccessLayerException {
        super.delete(categorie);
    }

    /**
     * Find an Categorie by its primary key.
     *
     * @param id
     * @return
     */
    public Categorie find(int id) throws DataAccessLayerException {
        return (Categorie) super.find(Categorie.class, id);
    }

    /**
     * Updates the state of a detached Categorie.
     *
     * @param categorie
     */
    public void update(Categorie categorie) throws DataAccessLayerException {
        super.saveOrUpdate(categorie);
    }

    /**
     * Finds all Categorie in the database.
     *
     * @return
     */
    public List<Categorie> findAll() throws DataAccessLayerException {
        return super.findAll(Categorie.class);
    }
}
