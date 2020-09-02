package admin.Controllers;

import admin.StudentData;
import database.dbUtils.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField idInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private TableView<StudentData> studentDataTableView;
    @FXML
    private TableColumn<StudentData, String> idColumn;
    @FXML
    private TableColumn<StudentData, String> firstNameColumn;
    @FXML
    private TableColumn<StudentData, String> lastNameColumn;
    @FXML
    private TableColumn<StudentData, String> emailColumn;
    @FXML
    private TableColumn<StudentData, String> dateColumn;

    private ObservableList<StudentData> studentDataObservableList;

    private String sqlQuery = "SELECT * FROM students";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database.dbUtils.dbConnection dbConnection = new dbConnection();
    }


    public void loadStudentData(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getConnection();
            this.studentDataObservableList = FXCollections.observableArrayList();

            ResultSet resultSet = Objects.requireNonNull(connection).createStatement().executeQuery(sqlQuery);

            while (resultSet.next()){
                this.studentDataObservableList.add(new StudentData(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }

        } catch (SQLException sqlException) {
            System.err.println("Error" + sqlException);
        }

        this.idColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("ID"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("lastName"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("email"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("additionDate"));
        this.studentDataTableView.setItems(null);
        this.studentDataTableView.setItems(studentDataObservableList);
    }


    public void addStudent(javafx.event.ActionEvent actionEvent){
        String sqlInsert = "INSERT INTO students(id, firstName, lastName, email, additionDate) VALUES(?,?,?,?,?)";
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sqlInsert);
            preparedStatement.setString(1,this.idInput.getText());
            preparedStatement.setString(2,this.firstNameInput.getText());
            preparedStatement.setString(3,this.lastNameInput.getText());
            preparedStatement.setString(4,this.emailInput.getText());
            preparedStatement.setString(5,this.dateInput.getEditor().getText());

            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearForm(javafx.event.ActionEvent actionEvent){
        this.idInput.setText("");
        this.firstNameInput.setText("");
        this.lastNameInput.setText("");
        this.emailInput.setText("");
        this.dateInput.setValue(null);
    }
}
