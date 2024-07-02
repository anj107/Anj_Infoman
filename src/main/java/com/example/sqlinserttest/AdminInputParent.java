package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AdminInputParent {

    private double x = 0;
    private double y = 0;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private StackPane stackpane;

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
    void closeclick(MouseEvent event) {
        javafx.application.Platform.exit();
    }
    @FXML
    private ImageView mnmbtn;

    @FXML
    void mnmclick(MouseEvent event) {
        Stage stage = (Stage) mnmbtn.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    private TextField mName;

    @FXML
    private TextField mEdu;

    @FXML
    private TextField mOccu;

    @FXML
    private TextField mIncome;

    @FXML
    private TextField fName;

    @FXML
    private TextField fEdu;

    @FXML
    private TextField fOccu;

    @FXML
    private TextField fIncome;

    @FXML
    private TextField gName;

    @FXML
    private TextField gEdu;

    @FXML
    private TextField gOccu;

    @FXML
    private TextField gIncome;

    @FXML
    private TextField gRelation;
    @FXML
    private TextField mID;
    @FXML
    private TextField fID;
    @FXML
    private TextField gID;
    @FXML
    private TextField applicantID;

    @FXML
    void submit(ActionEvent event) {
        DBUtils.establishConnection();

        if (!mID.getText().isEmpty()) {
            DBUtils.InsertMotherDetails(mID.getText(), applicantID.getText(), mName.getText(), mEdu.getText(), mOccu.getText(), mIncome.getText(), "Mother");
        }
        if (!fID.getText().isEmpty()) {
            DBUtils.InsertFatherDetails(fID.getText(), applicantID.getText(), fName.getText(), fEdu.getText(), fOccu.getText(), fIncome.getText(), "Father");
        }
        if (!gEdu.getText().isEmpty()) {
            DBUtils.InsertGuardianDetails(gID.getText(), applicantID.getText(), gName.getText(), gEdu.getText(), gOccu.getText(), gIncome.getText(), gRelation.getText());
        }
        DBUtils.closeConnection();
    }
}
