/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataObjects;

import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Abhi
 */
public class Inventory {
    
    private int partIdCounter=0,productIdCounter=0;
    
    private ArrayList<Part> allParts;
    private ArrayList<Product> products;

    public ArrayList<Part> getAllParts() {
        if(this.allParts==null){
            this.allParts=new ArrayList();
        }
        return allParts;
    }
    
    public ObservableList<Part> getAllPartsList(){
        ObservableList<Part> ol=FXCollections.observableArrayList();
        ol.addAll(getAllParts());
        return ol;
    }

    public void addPart(Part part) {
        if(this.allParts==null){
            this.allParts=new ArrayList();
        }
        if(part.getPartId()==0){
            //new part generate ID
            part.setPartId(partIdCounter+=1);  
            this.allParts.add(part);
        }else{
            //partId exists so traverse list for part and modify
            updatePart(part);
        }        
        
    }
    
    public void updatePart(Part part){
        Part pInList = lookupPart(part.getPartId());
            int index;
            if(pInList!=null){
                index=this.allParts.indexOf(pInList);
                this.allParts.add(index,part);
                this.allParts.remove(index+1);                
            }
    }
    
    public boolean deletePart(Part part) {
        if(this.allParts.contains(part)){
            this.allParts.remove(part);
            return true;
        }
        return false;
    }
    
    public Part lookupPart(int partId){
        Optional<Part> part = allParts.stream().filter(obj -> obj.getPartId()==partId).findFirst();
        return part.orElse(null);
    }
    
    public ArrayList<Product> getProducts() {
        if(this.products==null){
            this.products=new ArrayList();
        }
        return products;
    }
    
    public ObservableList<Product> getProductsList(){
        ObservableList<Product> ol=FXCollections.observableArrayList();
        ol.addAll(getProducts());
        return ol;
    }

    public void addProduct(Product product) {
        if(this.products==null){
            this.products=new ArrayList();
        }
        if(product.getProductId()==0){
            //new product generate ID
            product.setProductId(productIdCounter+=1);  
            this.products.add(product);
        }else{
            //partId exists so traverse list for part and modify
            updateProduct(product);
        }        
        
    }
    
    public void updateProduct(Product product){
        Product pInList = lookupProduct(product.getProductId());
            int index;
            if(pInList!=null){
                index=this.products.indexOf(pInList);
                this.products.add(index,product);
                this.products.remove(index+1);                
            }
    }
    
    public boolean deleteProduct(Product product) {
        if(this.products.contains(product) && product.getProductParts().size()==0){
            this.products.remove(product);
            return true;
        }
        return false;
    }
    
    public Product lookupProduct(int productId){
        Optional<Product> product = products.stream().filter(obj -> obj.getProductId()==productId).findFirst();
        return product.orElse(null);
    }
}
