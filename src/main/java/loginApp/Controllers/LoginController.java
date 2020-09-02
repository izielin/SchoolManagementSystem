package loginApp.Controllers;

import admin.Controllers.AdminController;
import database.models.LoginModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginApp.EnumFiles.option;
import loginApp.Utils.FxmlUtils;
import students.Controllers.StudentsController;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final String FXML_STUDENT_MAIN_FXML = "/fxml/studentFxml/StudentsMain.fxml";
    private static final String FXML_ADMIN_MAIN_FXML = "/fxml/adminFxml/AdminMain.fxml";
    LoginModel loginModel = new LoginModel();

    @FXML
    private TextField UserNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<option> selectionComboBox;
    @FXML
    private Label dbStatus;
    @FXML
    private Button loginButton;

    @FXML
    public void CloseApplicationButton() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbStatus.setText("Connected to Database");
        } else {
            this.dbStatus.setText("Not connected to Database");
        }

        this.selectionComboBox.setItems(FXCollections.observableArrayList(option.values()));
    }


    public void loginOnAction(ActionEvent actionEvent) {
        System.out.println("im working");
        try {
            if (this.loginModel.isLogin(this.UserNameField.getText(), this.passwordField.getText(), ((option) this.selectionComboBox.getValue()).toString())) {
                System.out.println("success");
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                System.out.println("success 2 ");
                switch (((option)this.selectionComboBox.getValue()).toString()){
                    case "admin":
                        System.out.println("admin 1");
                        adminLogin();
                        break;
                    case "student":
                        studentLogin();
                        break;
                }
            } else {
                System.out.println("Wrong password or username");
            }
        } catch (Exception localException) {
        }
    }

    public void studentLogin() {
        Stage studentStage = new Stage();
        Pane root = FxmlUtils.fxmlLoader(FXML_STUDENT_MAIN_FXML);
        FXMLLoader loader = new FXMLLoader();
        StudentsController studentsController = (StudentsController) loader.getController();

        Scene scene = new Scene(Objects.requireNonNull(root));
        studentStage.setScene(scene);
        studentStage.show();
    }

    public void adminLogin() {
        System.out.println("admin 2");
        Stage adminStage = new Stage();
        Pane root = FxmlUtils.fxmlLoader(FXML_ADMIN_MAIN_FXML);
        FXMLLoader loader = new FXMLLoader();
        AdminController adminController = (AdminController) loader.getController();

        Scene scene = new Scene(Objects.requireNonNull(root));
        adminStage.setScene(scene);
        adminStage.show();
        System.out.println("admin 3");
    }
}

