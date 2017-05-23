package main.java.com.hotel.modeldao;

import main.java.com.hotel.model.Reservation;

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
     * Insert a new Reservation into the database.
     *
     * @param reservation
     */
    public void create(Reservation reservation) {

        try {
            super.saveOrUpdate(reservation);
//            updateObservers(StringRessource.MSG_ETD_SUCCES);
        } catch (DataAccessLayerException e) {
//            updateObservers(StringRessources.MSG_ETD_ERREUR);
        }
    }

    /**
     * Delete a detached Reservation from the database.
     *
     * @param reservation
     */
    public void delete(Reservation reservation) throws DataAccessLayerException {
        super.delete(reservation);
    }

    /**
     * Find an Reservation by its primary key.
     *
     * @param id
     * @return
     */
    public Reservation find(int id) throws DataAccessLayerException {
        return (Reservation) super.find(Reservation.class, id);
    }

    /**
     * Updates the state of a detached Reservation.
     *
     * @param reservation
     */
    public void update(Reservation reservation) throws DataAccessLayerException {
        super.saveOrUpdate(reservation);
    }

    /**
     * Finds all Reservation in the database.
     *
     * @return
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public List<Reservation> findAll() throws DataAccessLayerException {
        return super.findAll(Reservation.class);
    }
    public List<Reservation> findBetween(LocalDate date1,LocalDate date2){
        Date d1;
        Date d2;

        d1=asDate(date1);
        d2=asDate(date2);

        return new ArrayList<>();
    }
}
