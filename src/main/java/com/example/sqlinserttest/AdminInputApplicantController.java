package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminInputApplicantController implements Initializable {


    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Close the stage
    }

    private double x = 0;
    private double y = 0;

    @FXML
    private StackPane stackpane;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> scholarshipChoiceBox;
    private String[] scholarshipop = {"RA 10612", "RA 7687", "MERIT"};

    @FXML
    private ChoiceBox<String> sexChoiceBox;
    private final String[] sexop = {"M", "F"};

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private TextField birthOrder;

    @FXML
    private TextField birthplace;

    @FXML
    private TextField citizenship;

    @FXML
    private TextField contactno;

    @FXML
    private TextField course;

    @FXML
    void stackpane_dragged(MouseEvent event) {
        Stage stage = (Stage) stackpane.getScene().getWindow();
        stage.setY(event.getScreenY() - y);
        stage.setX(event.getScreenX() - x);
    }

    @FXML
    void stackpane_pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }


    @FXML
    private ImageView mnmbtn;


    @FXML
    void mnmclick(MouseEvent event) {
        Stage stage = (Stage) mnmbtn.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private ChoiceBox<String> dualCitizStatusChoiceBox;
    private final String[] dualCitizStatusop = {"Yes", "No"};

    @FXML
    private TextField email;

    @FXML
    private ChoiceBox<String> passport;
    private String[] passportop = {"Yes", "No"};

    @FXML
    private TextField permAddress;
    @FXML
    private TextField siblings;
    @FXML
    private TextField uniAddress;

    @FXML
    private TextField uniname;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        scholarshipChoiceBox.getItems().addAll(scholarshipop);
        sexChoiceBox.getItems().addAll(sexop);
        dualCitizStatusChoiceBox.getItems().addAll(dualCitizStatusop);
        passport.getItems().addAll(passportop);
    }

    @FXML
    void submitApplicant(ActionEvent event) {
        try {
            DBUtils.establishConnection();

            String selectedScholarship = scholarshipChoiceBox.getValue();
            String selectedSex = sexChoiceBox.getValue();
            LocalDate selectedBirthday = birthdayPicker.getValue();

            int applicantId = DBUtils.addApplicant(name.getText(), selectedScholarship, selectedSex, selectedBirthday.toString(),
                    birthplace.getText(), citizenship.getText(), dualCitizStatusChoiceBox.getValue(),
                    contactno.getText(), email.getText(), permAddress.getText(),
                    Integer.parseInt(siblings.getText()), Integer.parseInt(birthOrder.getText()), course.getText(),
                    uniname.getText(), uniAddress.getText(), passport.getValue());

            if (applicantId != -1) {
                DBUtils.adminSuccessDialogue();
                System.out.println("Applicant added successfully with ID: " + applicantId);
            } else {
                System.out.println("Failed to add applicant.");
            }
        } catch (NumberFormatException e) {
            DBUtils.errorDialogue("Error","Please check your input!");
            System.err.println("Error: Invalid number format for Birth Order.");
            e.printStackTrace();
        }
        catch (Exception e){
            DBUtils.errorDialogue("Error", e.getMessage());
            System.out.println("An error has occured!");
        }

    }
    @FXML
    public void checkInt(KeyEvent event) {
        // Get the character that was typed
        String character = event.getCharacter();

        // Check if the character is not a digit
        if (!character.matches("[0-9.]")) {
            // If not a digit, prevent the character from being added to the text
            TextField textField = (TextField) event.getSource();
            int caretPosition = textField.getCaretPosition();
            int anchor = textField.getAnchor();

            // Remove the typed character from the text
            String text = textField.getText();
            if (caretPosition > 0 && caretPosition <= text.length()) {
                String newText = text.substring(0, caretPosition - 1) + text.substring(caretPosition);
                textField.setText(newText);
                textField.positionCaret(anchor - 1);
            }
        }
    }



    @FXML
    public void checkElevenDigits(KeyEvent event) {
        // Get the character that was typed
        String character = event.getCharacter();
        String currentText = contactno.getText();
        System.out.println(currentText.length());
        // Check if the character is not a digit
        if (!character.matches("[0-9]")|| currentText.length() > 11) {
            // If not a digit, prevent the character from being added to the text
            TextField textField = (TextField) event.getSource();
            int caretPosition = textField.getCaretPosition();
            int anchor = textField.getAnchor();

            // Remove the typed character from the text
            String text = textField.getText();
            if (caretPosition > 0 && caretPosition <= text.length()) {
                String newText = text.substring(0, caretPosition - 1) + text.substring(caretPosition);
                textField.setText(newText);
                textField.positionCaret(anchor - 1);
            }
        }
    }

}
