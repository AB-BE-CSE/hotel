package main.java.com.hotel.modeldao;

import main.java.com.hotel.login.LoginController;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Permission;
import main.java.com.hotel.model.Reservation;
import main.java.com.hotel.permission.MyPrivilegedAction;

import javax.security.auth.Subject;
import java.security.AccessControlException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;

/**
 * Created by Admin on 03/04/2017.
 */
public class ReservationDAO extends DAO {
    private static List<Observer> observers = new ArrayList<>();


    public ReservationDAO() {
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
     * Finds all Reservation in the database.
     *
     * @return
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Insert a new Reservation into the database.
     *
     * @param reservation
     */
    public void create(Reservation reservation) {
        try {
            try {
                Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("RESERVATION", Permission.CREATE));
                super.saveOrUpdate(reservation);
                updateObservers(StringRessources.MSG_RESEVRATION_SUCCES);
            } catch (AccessControlException e) {
                e.printStackTrace();
                updateObservers(StringRessources.MSG_PRIVILEGES);
            }
        } catch (DataAccessLayerException e) {
            updateObservers(StringRessources.MSG_RESERVATION_ERREUR);
        } catch (Exception e) {
            updateObservers(StringRessources.MSG_RESERVATION_ERREUR);
        }
    }

    /**
     * Delete a detached Reservation from the database.
     *
     * @param reservation
     */
    public void delete(Reservation reservation) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("RESERVATION", Permission.DELETE));
            super.delete(reservation);
            updateObservers(StringRessources.MSG_SUPPRESSION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    /**
     * Find an Reservation by its primary key.
     *
     * @param id
     * @return
     */
    public Reservation find(int id) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("RESERVATION", Permission.READ));
            return (Reservation) super.find(Reservation.class, id);
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the state of a detached Reservation.
     *
     * @param reservation
     */
    public void update(Reservation reservation) throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("RESERVATION", Permission.UPDATE));
            super.saveOrUpdate(reservation);
            updateObservers(StringRessources.MSG_MODIFICATION_SUCCES);
        } catch (AccessControlException e) {
            e.printStackTrace();
            updateObservers(StringRessources.MSG_PRIVILEGES);
        }
    }

    public List<Reservation> findAll() throws DataAccessLayerException {
        try {
            Subject.doAs(LoginController.getLoginContext().getSubject(), new MyPrivilegedAction("RESERVATION", Permission.READ));
            return super.findAll(Reservation.class);
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Reservation> findBetween(LocalDate date1, LocalDate date2) {
        Date d1;
        Date d2;

        d1 = asDate(date1);
        d2 = asDate(date2);

        return new ArrayList<>();
    }
}
