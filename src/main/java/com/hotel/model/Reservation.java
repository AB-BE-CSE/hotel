package main.java.com.hotel.model;

import java.util.Date;

/**
 * Created by Admin on 12/04/2017.
 */
public class Reservation {
    private int id;
    private Date dateReserv;
    private Date dateArriv;
    private Date dateSortie;
    private Client client;
    private Facture facture;
    private Utilisateur user;
    private Chambre chambre;

}
