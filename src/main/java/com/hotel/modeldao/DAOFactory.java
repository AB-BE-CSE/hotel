package main.java.com.hotel.modeldao;

import main.java.com.hotel.metier.StringRessources;

/**
 * @author Hicham Bali
 */

public class DAOFactory {
    public static DAO getDAO(String dao) {
        if (dao.equals(StringRessources.CHAMBRE)) {
            return new ChambreDAO();
        } else if (dao.equals(StringRessources.RESERVATION)) {
            return new ReservationDAO();
        } else if (dao.equals(StringRessources.CLIENT)) {
            return new ClientDAO();
        } else if (dao.equals(StringRessources.USER)) {
            return new UtilisateurDAO();
        } else if (dao.equals(StringRessources.CATEGORIE)) {
            return new CategorieDAO();
        } else if (dao.equals(StringRessources.FACTURE)) {
            return new FactureDAO();
        }

        return null;
    }
}
