/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dataObjects.InHouse;
import dataObjects.Outsourced;
import main.App;
import main.Constants;
import main.Helper;
import main.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Abhi
 */
public class AddPartController implements Initializable, GlobalController {

    @FXML
    protected Label rbTypeLabel;
    @FXML
    protected RadioButton outSourced, inHouse;
    @FXML
    private Button cancel;
    @FXML
    protected TextField name, inStock, price, max, min, radioBtnField,partId;

    ToggleGroup group = new ToggleGroup();

    @FXML
    private void handleRadioButtonAction(ActionEvent event) {
        if (outSourced.selectedProperty().getValue().equals(true)) {
            rbTypeLabel.setText("Company Name");
        } else {
            rbTypeLabel.setText("Machine ID");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        outSourced.setToggleGroup(group);
        inHouse.setToggleGroup(group);
        outSourced.setSelected(true);
        rbTypeLabel.setText("Company Name");
        //inv.setTextFormatter(value);
    }
    
    public void doCancel() throws Exception {
        doCancel(true,cancel,Constants.MAIN);
    }

    public void doSave() throws Exception {
        if (inHouse.isSelected()) {
            InHouse inHousePart = new InHouse();
            if(Validator.hasPartId(partId)){
                inHousePart.setPartId(Helper.getInt(partId));
            }
            if (Validator.hasRequiredValue(name, "Name")) {
                inHousePart.setName(name.getText());
            }
            if (Validator.isDouble(price, "Price/Cost")) {
                inHousePart.setPrice(Helper.getDouble(price));
            }
            if (Validator.isInteger(max, "Max") && Validator.isInteger(min, "Min")) {
                if (Validator.isGreater(Helper.getInt(max), Helper.getInt(min))) {
                    inHousePart.setMax(Helper.getInt(max));
                    inHousePart.setMin(Helper.getInt(min));
                } else {
                    Helper.throwException("Min cannot be more then Max");
                }
            }
            if (Validator.isInteger(inStock, "Inv")) {
                if (Validator.isBetween(Helper.getInt(max), Helper.getInt(min), Helper.getInt(inStock))) {
                    inHousePart.setInStock(Helper.getInt(inStock));
                } else {
                    Helper.throwException("Inventory must be between min and max inclusive");
                }
            }
            if (Validator.hasRequiredValue(radioBtnField, "Machine ID") && Validator.isInteger(radioBtnField, "Machine ID")) {
                inHousePart.setMachineId(Helper.getInt(radioBtnField));
            }
            App.getInventory().addPart(inHousePart);
        } else {
            Outsourced outsourcedPart = new Outsourced();
            if(Validator.hasPartId(partId)){
               outsourcedPart.setPartId(Helper.getInt(partId));
            }            
            if (Validator.hasRequiredValue(name, "Name")) {
                outsourcedPart.setName(name.getText());
            }
            if (Validator.isDouble(price, "Price/Cost")) {
                outsourcedPart.setPrice(Helper.getDouble(price));
            }
            if (Validator.isInteger(max, "Max") && Validator.isInteger(min, "Min")) {
                if (Validator.isGreater(Helper.getInt(max), Helper.getInt(min))) {
                    outsourcedPart.setMax(Helper.getInt(max));
                    outsourcedPart.setMin(Helper.getInt(min));
                } else {
                    Helper.throwException("Min cannot be more then Max");
                }
            }
            if (Validator.isInteger(inStock, "Inv")) {
                if (Validator.isBetween(Helper.getInt(max), Helper.getInt(min), Helper.getInt(inStock))) {
                    outsourcedPart.setInStock(Helper.getInt(inStock));
                } else {
                    Helper.throwException("Inventory must be between min and max inclusive");
                }
            }
            if (Validator.hasRequiredValue(radioBtnField, "Company Name")) {
                outsourcedPart.setCompanyName(radioBtnField.getText());
            }
            App.getInventory().addPart(outsourcedPart);            
        }
        Helper.doAlert(Constants.SAVE_SUCCESS);
        doCancel(false,cancel,Constants.MAIN);
    }
}
