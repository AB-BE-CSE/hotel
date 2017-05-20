package main.java.com.hotel.modeldao;

import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Categorie;
import main.java.com.hotel.model.Chambre;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Admin on 03/04/2017.
 */
public class ChambreDAO extends DAO {
    private static List<Observer> observers = new ArrayList<>();


    public ChambreDAO() {
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
     * Insert a new Chambre into the database.
     *
     * @param chambre
     */
    public void create(Chambre chambre) {

        try {
            super.saveOrUpdate(chambre);
            updateObservers(StringRessources.MSG_CHAMBRE_SUCCES);
        } catch (DataAccessLayerException e) {
            updateObservers(StringRessources.MSG_CHAMBRE_ERREUR);
        } catch (Exception e){
            updateObservers(StringRessources.MSG_CHAMBRE_ERREUR);
        }
    }

    /**
     * Delete a detached Chambre from the database.
     *
     * @param chambre
     */
    public void delete(Chambre chambre) throws DataAccessLayerException {
        super.delete(chambre);
    }

    /**
     * Find an Chambre by its primary key.
     *
     * @param id
     * @return
     */
    public Chambre find(int id) throws DataAccessLayerException {
        return (Chambre) super.find(Chambre.class, id);
    }

    /**
     * Updates the state of a detached Chambre.
     *
     * @param chambre
     */
    public void update(Chambre chambre) throws DataAccessLayerException {
        super.saveOrUpdate(chambre);
    }

    /**
     * Finds all Chambre in the database.
     *
     * @return
     */
    public List<Chambre> findAll() throws DataAccessLayerException {
        return super.findAll(Chambre.class);
    }

    public List<Chambre> createAll(int debut, int fin, Categorie categorie, int etage) {

//            Chambre chambre = new Chambre();
//            chambre.setNumeroChambre(i);
//            chambre.setEtage(Integer.valueOf(etage.getText()));
//            chambre.setCheck(false);
//            chambre.setCategorie(categorie.getValue());
//            ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);

        return null;
    }
}
