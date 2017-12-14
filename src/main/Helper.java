/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 *
 * @author Abhi
 */
public class Helper {

    public static void doExceptionAlert(String message, Exception e) throws Exception {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            throw e;
        }
    }
    
    public static void doAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            
        }
    }

    public static boolean doCancelAlert() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean doDeleteAlert() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }
    }

    public static void throwException(String message) throws Exception {
        CustomMessage e = new CustomMessage(message);
        doExceptionAlert(message, e);
    }

    public static int getInt(TextField t) throws Exception {
        return Integer.parseInt(t.getText());
    }

    public static double getDouble(TextField t) throws Exception {
        String doubleVal = t.getText().trim();
        if (doubleVal.contains("$")) {
            doubleVal = doubleVal.substring(doubleVal.indexOf("$") + 1);
        }
        return Double.parseDouble(doubleVal);
    }
}
