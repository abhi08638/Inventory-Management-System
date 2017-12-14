/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dataObjects.Inventory;
import dataObjects.Part;
import dataObjects.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abhi
 */
public class App extends Application {
    
    private static Inventory inventory;
    
    private static Object controllerObj;
    
    @Override
    public void start(Stage stage) throws Exception {
        inventory=new Inventory();
        
        Parent root = FXMLLoader.load(getClass().getResource(Constants.FXML_PATH+Constants.MAIN));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Inventory getInventory(){
        return inventory;
    }
    
    public static Object getControllerObj(){
        Object obj=controllerObj;
        controllerObj=null;
        return obj;
    }
    
    public static void setControllerObj(Part p){
        controllerObj=p;
    }
    
    public static void setControllerObj(Product p){
        controllerObj=p;
    }
    
}
