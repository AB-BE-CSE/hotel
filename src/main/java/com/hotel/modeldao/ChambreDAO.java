package main.java.com.hotel.modeldao;

import main.java.com.hotel.login.LoginController;
import main.java.com.hotel.model.Chambre;
import main.java.com.hotel.model.Permission;
import main.java.com.hotel.permission.MyPrivilegedAction;

import javax.security.auth.Subject;
import java.security.AccessControlException;
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
            try {
                Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CHAMBRE", Permission.CREATE));
                super.saveOrUpdate(chambre);
            } catch (AccessControlException e) {
                e.printStackTrace();
            }
//            updateObservers(StringRessource.MSG_ETD_SUCCES);
        } catch (DataAccessLayerException e) {
//            updateObservers(StringRessources.MSG_ETD_ERREUR);
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
}
