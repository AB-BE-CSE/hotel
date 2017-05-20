package main.java.com.hotel.metier;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


/**
 * Created by Admin on 18/05/2017.
 */
public class DeleteContextMenu {
    private static DeleteContextMenu deleteContextMenu = null;
    private ContextMenu cm;
    private MenuItem supp;

    private DeleteContextMenu() {

        cm = new ContextMenu();
        supp = new MenuItem(StringRessources.SUPPRIMER);

        cm.getItems().add(supp);

    }

    private DeleteContextMenu getInstance() {
        if (deleteContextMenu == null)
            deleteContextMenu = new DeleteContextMenu();

        return deleteContextMenu;
    }

    public void show(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton() == MouseButton.SECONDARY)
                cm.show(node, e.getScreenX(), e.getScreenY());
        });
    }

    public void setAction(EventHandler event) {
        supp.setOnAction(event);
    }
}
