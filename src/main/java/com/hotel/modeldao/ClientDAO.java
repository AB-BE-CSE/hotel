package main.java.com.hotel.modeldao;

import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Admin on 03/04/2017.
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
            super.saveOrUpdate(client);
            updateObservers(StringRessources.MSG_CLIENT_SUCCES);
        } catch (DataAccessLayerException e) {
            updateObservers(StringRessources.MSG_CLIENT_ERREUR);
        }
    }

    /**
     * Delete a detached Client from the database.
     *
     * @param client
     */
    public void delete(Client client) throws DataAccessLayerException {
        super.delete(client);
    }

    /**
     * Find an Client by its primary key.
     *
     * @param id
     * @return
     */
    public Client find(int id) throws DataAccessLayerException {
        return (Client) super.find(Client.class, id);
    }

    /**
     * Updates the state of a detached Client.
     *
     * @param client
     */
    public void update(Client client) throws DataAccessLayerException {
        super.saveOrUpdate(client);
    }

    /**
     * Finds all Client in the database.
     *
     * @return
     */
    public List<Client> findAll() throws DataAccessLayerException {
        return super.findAll(Client.class);
    }
}
