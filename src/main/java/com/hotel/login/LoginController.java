package main.java.com.hotel.login;


import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.com.hotel.metier.MainController;
import main.java.com.hotel.metier.StringRessources;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by marconi on 28/01/16.
 */
@FXMLController(value = "/main/java/com/hotel/presentation/Login.fxml", title = "")
public class LoginController {
    private static int nbrLogin = 3;
    private static LoginContext lc;

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label erreur;

    public static LoginContext getLoginContext() {
        return lc;
    }


    @PostConstruct
    public void init() throws FlowException, VetoException {
        System.setProperty("java.security.auth.login.config", "login.config");
    }

    @FXML
    private void handlePasswordKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            login();
    }

    @FXML
    private void login() {
        lc = null;
        try {
            lc = new LoginContext("loginConfig",
                    new UsernamePasswordCallbackHandler(username.getText(), password.getText()));
        } catch (LoginException le) {

            return;
        }
        //
        // Login
        try {
            lc.login();
        } catch (LoginException le) {
            le.printStackTrace();
            if (--nbrLogin == 0) {
                System.exit(0);
            }
            erreur.setText(StringRessources.ERR_LOGIN);
            return;
        }

        try {
            Flow flow = new Flow(MainController.class);
            DefaultFlowContainer container = new DefaultFlowContainer();
            ViewFlowContext flowContext = new ViewFlowContext();
            Stage stage = ((Stage) (context.getRegisteredObject("Stage")));
            flowContext.register("Stage", stage);
            stage.close();

            flow.createHandler(flowContext).start(container);
            ((JFXDecorator) stage.getScene().getRoot()).setContent(container.getView());
            stage.setHeight(700);
            stage.setWidth(900);
            ((Stage) (context.getRegisteredObject("Stage"))).show();
        } catch (FlowException e) {
            e.printStackTrace();
        }
    }

    public void update(Object o) {
//        Main.getStage().setResizable(true);
//        Main.getStage().setMaximized(true);
//        Main.getStage().setMinWidth(1000);
//        Main.getStage().setMinHeight(700);
//        startActivity(AppActivity.class);
    }
}
