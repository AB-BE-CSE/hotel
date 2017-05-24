package main.java.com.hotel.metier.menus;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.java.com.hotel.metier.StringRessources;

import java.util.Observer;


/**
 * Created by Hicham Bali on 18/05/2017.
 */
public class ReservationContextMenu {
    private static ReservationContextMenu reservationContextMenu = null;
    private ContextMenu cm;
    private MenuItem start;
    private MenuItem end;
    private Observer observer;

    private ReservationContextMenu() {
        cm = new ContextMenu();
        start = new MenuItem(StringRessources.DEBUT);
        end = new MenuItem(StringRessources.FIN);
        start.setOnAction(e -> {
            observer.update(null, true);
            if (!start.isDisable()) {
                start.setDisable(true);
                end.setDisable(false);
            }
        });
        end.setOnAction(e -> {
            observer.update(null, false);
            if (!end.isDisable()) {
                start.setDisable(false);
                end.setDisable(true);
            }
        });
        end.setDisable(true);
        cm.getItems().addAll(start, end);
    }

    public static ReservationContextMenu getInstance(Observer observer) {
        if (reservationContextMenu == null)
            reservationContextMenu = new ReservationContextMenu();
        reservationContextMenu.observer = observer;

        return reservationContextMenu;
    }

    public void show(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton() == MouseButton.SECONDARY)
                cm.show(node, e.getScreenX(), e.getScreenY());
        });
    }
}
