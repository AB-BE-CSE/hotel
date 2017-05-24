package main.java.com.hotel;

import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.hotel.login.LoginController;
import main.java.com.hotel.modeldao.HibernateFactory;
/** @author Nadir belarouci*/
public class Main extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        HibernateFactory.buildSessionFactory();

        Flow flow = new Flow(LoginController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView(), false, true, true);
        decorator.setCustomMaximize(true);
        Scene scene = new Scene(decorator, 400, 500);

        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-design.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("/main/java/com/hotel/presentation/controls.css").toExternalForm());

        decorator.setOnCloseButtonAction(() -> {
            HibernateFactory.closeFactory();
            System.exit(0);
        });
        stage.setOnCloseRequest(e -> {
            HibernateFactory.closeFactory();
        });
        stage.setScene(scene);
        stage.show();

    }

}
