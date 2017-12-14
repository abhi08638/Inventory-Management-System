/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import main.Constants;
import main.Helper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Abhi
 */
public interface GlobalController {

    default void doCancel(boolean showConfirm, Button cancel,String path) throws Exception {
        boolean cancelConfirmed;
        if (showConfirm) {
            cancelConfirmed = Helper.doCancelAlert();
        } else {
            cancelConfirmed = true;
        }
        if (cancelConfirmed) {
            Stage stage;
            stage = (Stage) cancel.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(Constants.FXML_PATH + path));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
