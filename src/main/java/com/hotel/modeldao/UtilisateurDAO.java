package main.java.com.hotel.modeldao;

import main.java.com.hotel.login.HashPassword;
import main.java.com.hotel.model.Utilisateur;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
//            updateObservers(StringRessource.MSG_ETD_SUCCES);
        } catch (DataAccessLayerException e) {
//            updateObservers(StringRessources.MSG_ETD_ERREUR);
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

    public boolean isExist(String username) {
        try {
            Connection connection = HibernateFactory.getSessionFactory().
                    getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).getConnection();

            String query = "SELECT username FROM Utilisateur where username = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.first();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Utilisateur isValidPassword(String username, char[] password) {

        try {
            Connection connection = HibernateFactory.getSessionFactory().
                    getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).getConnection();
            String query = "SELECT idUser FROM Utilisateur where username = ? and password = ?";
            try {
                String pw = new String(password);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, HashPassword.digest(pw, HashPassword.Size.S256));
                pw = null;
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.first()) {
                    return find(resultSet.getInt("idUser"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
