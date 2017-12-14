/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dataObjects.Product;
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
public class ModifyProductController extends AddProductController implements Initializable, GlobalController {

    @FXML
    private TextField name, inStock, price, max, min, productId; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currProd=(Product)App.getControllerObj();  
        prodDto=new Product();
        prodDto.getProductParts().addAll(currProd.getProductParts());
        productId.setText(Integer.toString(currProd.getProductId()));
        name.setText(currProd.getName());
        inStock.setText(Integer.toString(currProd.getInStock()));
        price.setText(Double.toString(currProd.getPrice()));
        max.setText(Integer.toString(currProd.getMax()));
        min.setText(Integer.toString(currProd.getMin()));        
        loadAllPartsTable();
        loadProductPartsTable();
    }
}