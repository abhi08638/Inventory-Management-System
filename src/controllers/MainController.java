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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Abhi
 */
public class MainController implements Initializable {
    
    @FXML
    private Button exitBtn, addPartBtn, addProductBtn;
   
    @FXML
    private TableColumn partId, partPrice, partInv, partName;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TextField partSearchbox;
    
    @FXML
    private TableColumn productId, productPrice, productInv, productName;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TextField productSearchbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPartsTable();
        loadProductsTable();
    }

    private void loadPartsTable() {
        Inventory inventory = App.getInventory();
        partsTable.setItems(inventory.getAllPartsList());
        partId.setCellValueFactory(
                new PropertyValueFactory<Part, String>("partId"));
        partName.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name"));
        partInv.setCellValueFactory(
                new PropertyValueFactory<Part, String>("inStock"));
        partPrice.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price"));
    }
    
     private void loadProductsTable() {
        Inventory inventory = App.getInventory();
        productsTable.setItems(inventory.getProductsList());
        productId.setCellValueFactory(
                new PropertyValueFactory<Part, String>("productId"));
        productName.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name"));
        productInv.setCellValueFactory(
                new PropertyValueFactory<Part, String>("inStock"));
        productPrice.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price"));
    }

    public void doExit() throws Exception {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void doPartDelete() throws Exception {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        Inventory inventory = App.getInventory();        
        boolean deleteConfirmed=false;
        if(part!=null){
            for(Product p:inventory.getProducts()){
                if(p.getProductParts().contains(part)){
                    Helper.throwException("Cannot Delete: This Part is assigned to Product "+p.getName());
                }
            }
            deleteConfirmed=Helper.doDeleteAlert();
        }else{
            deleteConfirmed=false;
        }
        if(deleteConfirmed){
            App.getInventory().deletePart(part);
            loadPartsTable();
        }
    }
    
    public void doProductDelete() throws Exception {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        boolean deleteConfirmed=false;
        if(product!=null){
            if(product.getProductParts().size()==0){
                deleteConfirmed=Helper.doDeleteAlert();    
            }else{
                Helper.throwException("Cannot Delete: This Product has a part assigned to it");
            }            
        }else{
            deleteConfirmed=false;
        }
        if(deleteConfirmed){
            App.getInventory().deleteProduct(product);
            loadProductsTable();
        }
    }
    
    public void doPartSearch() throws Exception {        
        Part part;
        if(Validator.hasValue(partSearchbox)){
            if(Validator.isInteger(partSearchbox, "Part Search Box")){
                ObservableList<Part> ol=FXCollections.observableArrayList();
                part=App.getInventory().lookupPart(Helper.getInt(partSearchbox));
                if(part!=null)
                    ol.add(part);
                partsTable.setItems(ol);
            }
        }else{
            loadPartsTable();
        }
    }
    
    public void doProductSearch() throws Exception {        
        Product product;
        if(Validator.hasValue(productSearchbox)){
            if(Validator.isInteger(productSearchbox, "Product Search Box")){
                ObservableList<Product> ol=FXCollections.observableArrayList();
                product=App.getInventory().lookupProduct(Helper.getInt(productSearchbox));
                if(product!=null)
                    ol.add(product);
                productsTable.setItems(ol);
            }
        }else{
            loadProductsTable();
        }
    }

    public void doAddPart() throws Exception {
        Stage stage;
        stage = (Stage) addPartBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(Constants.FXML_PATH + Constants.ADD_PART));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    public void doAddProduct() throws Exception {
        Stage stage;
        stage = (Stage) addProductBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(Constants.FXML_PATH + Constants.ADD_PRODUCT));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    public void doModifyPart() throws Exception {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        if(part!=null){
            App.setControllerObj(part);
            Stage stage;
            stage = (Stage) addPartBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(Constants.FXML_PATH + Constants.MODIFY_PART));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        }
    }
    
    public void doModifyProduct() throws Exception {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if(product!=null){
            App.setControllerObj(product);
            Stage stage;
            stage = (Stage) addProductBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(Constants.FXML_PATH + Constants.MODIFY_PRODUCT));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        }
    }

}
