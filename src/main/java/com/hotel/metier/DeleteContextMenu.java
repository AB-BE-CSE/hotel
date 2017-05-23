package main.java.com.hotel.metier;

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
    private Gestion gestion;
    private Object toDelete;

    private DeleteContextMenu() {
        cm = new ContextMenu();
        supp = new MenuItem(StringRessources.SUPPRIMER);
        supp.setOnAction(event -> gestion.supprimer(toDelete));

        cm.getItems().add(supp);
        supp.setOnAction(e -> gestion.supprimer(toDelete));
    }

    public static DeleteContextMenu getInstance(Gestion gestion) {
        if (deleteContextMenu == null)
            deleteContextMenu = new DeleteContextMenu();
        deleteContextMenu.gestion = gestion;

        return deleteContextMenu;
    }

    public void show(Node node, Object toDelete) {
        this.toDelete = toDelete;
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton() == MouseButton.SECONDARY)
                cm.show(node, e.getScreenX(), e.getScreenY());
        });
    }

}
