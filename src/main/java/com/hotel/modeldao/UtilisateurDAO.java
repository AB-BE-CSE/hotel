package main.java.com.hotel.modeldao;

import main.java.com.hotel.login.HashPassword;
import main.java.com.hotel.login.LoginController;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Permission;
import main.java.com.hotel.model.Utilisateur;
import main.java.com.hotel.permission.MyPrivilegedAction;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import javax.security.auth.Subject;
import java.security.AccessControlException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/** @author Zakaria Choukchou*/

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
            try {
                Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("USER", Permission.CREATE));
                super.saveOrUpdate(utilisateur);
                updateObservers(StringRessources.MSG_USER_SUCCES);
                updateObservers(utilisateur);
            } catch (AccessControlException e) {
                e.printStackTrace();
                updateObservers(StringRessources.MSG_PRIVILEGES);
            }
        } catch (DataAccessLayerException e) {
            updateObservers(StringRessources.MSG_USER_ERREUR);
        } catch (Exception e) {
            updateObservers(StringRessources.MSG_USER_ERREUR);
        }
    }

    /**
     * Delete a detached Utilisateur from the database.
     *
     * @param utilisateur
     */
    public void delete(Utilisateur utilisateur) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("USER", Permission.DELETE));
            super.delete(utilisateur);
            updateObservers(StringRessources.MSG_SUPPRESSION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    /**
     * Find an Utilisateur by its primary key.
     *
     * @param id
     * @return
     */
    public Utilisateur find(int id) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("USER", Permission.READ));
            return (Utilisateur) super.find(Utilisateur.class, id);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
        return null;
    }

    /**
     * Updates the state of a detached Utilisateur.
     *
     * @param utilisateur
     */
    public void update(Utilisateur utilisateur) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("USER", Permission.UPDATE));
            super.saveOrUpdate(utilisateur);
            updateObservers(StringRessources.MSG_MODIFICATION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    /**
     * Finds all Utilisateur in the database.
     *
     * @return
     */
    public List<Utilisateur> findAll() throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("USER", Permission.READ));
            return super.findAll(Utilisateur.class);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
        return new ArrayList<>();
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
                    return (Utilisateur) super.find(Utilisateur.class, resultSet.getInt("idUser"));
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
