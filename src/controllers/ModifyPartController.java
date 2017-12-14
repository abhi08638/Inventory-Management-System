/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dataObjects.InHouse;
import dataObjects.Outsourced;
import dataObjects.Part;
import main.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Abhi
 */
public class ModifyPartController extends AddPartController implements Initializable, GlobalController{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        Part p=(Part)App.getControllerObj();        
        partId.setText(Integer.toString(p.getPartId()));
        name.setText(p.getName());
        inStock.setText(Integer.toString(p.getInStock()));
        price.setText(Double.toString(p.getPrice()));
        max.setText(Integer.toString(p.getMax()));
        min.setText(Integer.toString(p.getMin()));
        if(p instanceof Outsourced){
            outSourced.setSelected(true);
            inHouse.setSelected(false);
            radioBtnField.setText(((Outsourced) p).getCompanyName());
            rbTypeLabel.setText("Company Name");
        }else{
            outSourced.setSelected(false);
            inHouse.setSelected(true);
            radioBtnField.setText(Integer.toString(((InHouse) p).getMachineId()));
            rbTypeLabel.setText("Machine ID");
        }
    }
}
