package loginApp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loginApp.Utils.FxmlUtils;

import java.util.Objects;

public class LoginMainApp extends Application {
    private static final String FXML_LOGIN_MAIN_FXML = "/fxml/loginFxml/LoginMain.fxml";
    private double xOffset, yOffset;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Pane root = FxmlUtils.fxmlLoader(FXML_LOGIN_MAIN_FXML);
        Scene scene = new Scene(Objects.requireNonNull(root));

        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}