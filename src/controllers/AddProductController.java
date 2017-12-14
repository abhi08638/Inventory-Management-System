/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dataObjects.Inventory;
import dataObjects.Part;
import dataObjects.Product;
import main.App;
import main.Constants;
import main.Helper;
import main.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Abhi
 */
public class AddProductController implements Initializable, GlobalController {

    @FXML
    private Button cancel;
    @FXML
    private TextField name, inStock, price, max, min, productId;
    @FXML
    private TableColumn allPartId, allPartPrice, allPartInv, allPartName,
            productPartId, productPartPrice, productPartInv, productPartName;
    @FXML
    private TableView<Part> allPartsTable, productPartsTable;
    @FXML
    private TextField allPartSearchbox;
    
    protected Product currProd,prodDto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currProd=new Product();
        prodDto=new Product();
        prodDto.getProductParts().addAll(currProd.getProductParts());
        inStock.setText("0");
        loadAllPartsTable();
        loadProductPartsTable();
    }

    public void doCancel() throws Exception {
        doCancel(true, cancel, Constants.MAIN);
    }

    private double getPartsTotal(){
        double total=0;
        for(Part p:prodDto.getProductParts()){
            total+=p.getPrice();
        }
        return total;
    }
    
    public void doSave() throws Exception {

        Product newProduct = new Product();
        if (Validator.hasPartId(productId)) {
            newProduct.setProductId(Helper.getInt(productId));
        }
        if (Validator.hasRequiredValue(name, "Name")) {
            newProduct.setName(name.getText());
        }
        if (Validator.isDouble(price, "Price/Cost")) {
            double total=getPartsTotal();
            if(Helper.getDouble(price)>=total){
                newProduct.setPrice(Helper.getDouble(price));
            }else{
                Helper.throwException("Product total cannot be less than the sum of parts which is $"+total);
            }            
        }
        if (Validator.isInteger(max, "Max") && Validator.isInteger(min, "Min")) {
            if (Validator.isGreater(Helper.getInt(max), Helper.getInt(min))) {
                newProduct.setMax(Helper.getInt(max));
                newProduct.setMin(Helper.getInt(min));
            } else {
                Helper.throwException("Min cannot be more then Max");
            }
        }
        if (Validator.isInteger(inStock, "Inv")) {
            if (Validator.isBetween(Helper.getInt(max), Helper.getInt(min), Helper.getInt(inStock))) {
                newProduct.setInStock(Helper.getInt(inStock));
            } else {
                Helper.throwException("Inventory must be between min and max inclusive");
            }
        }
        if (prodDto.getProductParts().size()>0){
            newProduct.getProductParts().addAll(prodDto.getProductParts());
        }else{
            Helper.throwException("A product must have at least one part associated with it");
        }
        
        App.getInventory().addProduct(newProduct);

        Helper.doAlert(Constants.SAVE_SUCCESS);
        doCancel(false, cancel, Constants.MAIN);
    }

    public void doPartSearch() throws Exception {
        Part part;
        if (Validator.hasValue(allPartSearchbox)) {
            if (Validator.isInteger(allPartSearchbox, "Part Search Box")) {
                ObservableList<Part> ol = FXCollections.observableArrayList();
                part = App.getInventory().lookupPart(Helper.getInt(allPartSearchbox));
                if (part != null) {
                    ol.add(part);
                }
                allPartsTable.setItems(ol);
            }
        } else {
            loadAllPartsTable();
        }
    }

    protected void loadAllPartsTable() {
        Inventory inventory = App.getInventory();
        allPartsTable.setItems(inventory.getAllPartsList());
        allPartId.setCellValueFactory(
                new PropertyValueFactory<Part, String>("partId"));
        allPartName.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name"));
        allPartInv.setCellValueFactory(
                new PropertyValueFactory<Part, String>("inStock"));
        allPartPrice.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price"));
    }
    
    protected void loadProductPartsTable() {
        productPartsTable.setItems(prodDto.getProductPartsList());
        productPartId.setCellValueFactory(
                new PropertyValueFactory<Part, String>("partId"));
        productPartName.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name"));
        productPartInv.setCellValueFactory(
                new PropertyValueFactory<Part, String>("inStock"));
        productPartPrice.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price"));
    }
    
    public void addPart(){
        Part part = allPartsTable.getSelectionModel().getSelectedItem();
        if(part!=null){
            prodDto.addAssociatedPart(part);
        }
        loadProductPartsTable();
    }
    
    public void deletePart(){
        Part part = productPartsTable.getSelectionModel().getSelectedItem();
        if(part!=null){
            prodDto.removeAssociatedPart(part);
        }
        loadProductPartsTable();
    }

}
