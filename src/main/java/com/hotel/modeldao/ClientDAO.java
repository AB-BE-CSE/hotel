package main.java.com.hotel.modeldao;

import main.java.com.hotel.login.LoginController;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Client;
import main.java.com.hotel.model.Permission;
import main.java.com.hotel.permission.MyPrivilegedAction;

import javax.security.auth.Subject;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @author Hicham Bali
 */

public class ClientDAO extends DAO {
    private static List<Observer> observers = new ArrayList<>();


    public ClientDAO() {
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
     * Insert a new Client into the database.
     *
     * @param client
     */
    public void create(Client client) {

        try {
            try {
                Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CLIENT", Permission.CREATE));
                super.saveOrUpdate(client);
                updateObservers(StringRessources.MSG_CLIENT_SUCCES);
                updateObservers(client);
            } catch (AccessControlException e) {
                e.printStackTrace();
                updateObservers(StringRessources.MSG_PRIVILEGES);
            }
        } catch (DataAccessLayerException e) {
            updateObservers(StringRessources.MSG_CLIENT_ERREUR);
        } catch (Exception e) {
            updateObservers(StringRessources.MSG_CLIENT_ERREUR);
        }
    }

    /**
     * Delete a detached Client from the database.
     *
     * @param client
     */
    public void delete(Client client) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CLIENT", Permission.DELETE));
            super.delete(client);
            updateObservers(StringRessources.MSG_SUPPRESSION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    /**
     * Find an Client by its primary key.
     *
     * @param id
     * @return
     */
    public Client find(int id) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CLIENT", Permission.READ));
            return (Client) super.find(Client.class, id);
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the state of a detached Client.
     *
     * @param client
     */
    public void update(Client client) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CLIENT", Permission.UPDATE));
            super.saveOrUpdate(client);
            updateObservers(StringRessources.MSG_MODIFICATION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    /**
     * Finds all Client in the database.
     *
     * @return
     */
    public List<Client> findAll() throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("CLIENT", Permission.READ));
            return super.findAll(Client.class);
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
