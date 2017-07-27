package main.java.com.hotel.modeldao;

import main.java.com.hotel.login.LoginController;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Categorie;
import main.java.com.hotel.model.Permission;
import main.java.com.hotel.permission.MyPrivilegedAction;

import javax.security.auth.Subject;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @author Ilies Bouyacoub
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
            try {
                Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CHAMBRE", Permission.CREATE));
                super.saveOrUpdate(categorie);
                updateObservers(StringRessources.MSG_CATEGORIE_SUCCES);
                updateObservers(categorie);
            } catch (AccessControlException e) {
                e.printStackTrace();
                updateObservers(StringRessources.MSG_PRIVILEGES);
            }
        } catch (DataAccessLayerException e) {
            updateObservers(StringRessources.MSG_CATEGORIE_ERREUR);
        } catch (Exception e) {
            updateObservers(StringRessources.MSG_CATEGORIE_ERREUR);
        }
    }

    /**
     * Delete a detached Categorie from the database.
     *
     * @param categorie
     */
    public void delete(Categorie categorie) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CHAMBRE", Permission.DELETE));
            super.delete(categorie);
            updateObservers(StringRessources.MSG_SUPPRESSION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    /**
     * Find an Categorie by its primary key.
     *
     * @param id
     * @return
     */
    public Categorie find(int id) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CHAMBRE", Permission.READ));
            return (Categorie) super.find(Categorie.class, id);
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the state of a detached Categorie.
     *
     * @param categorie
     */
    public void update(Categorie categorie) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CHAMBRE", Permission.UPDATE));
            super.saveOrUpdate(categorie);
            updateObservers(StringRessources.MSG_MODIFICATION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    /**
     * Finds all Categorie in the database.
     *
     * @return
     */


    public List<Categorie> findAll() throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CHAMBRE", Permission.READ));
            return super.findAll(Categorie.class);
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
