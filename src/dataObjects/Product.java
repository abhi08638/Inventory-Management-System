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
public class Product {

    private int productId;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    private ArrayList<Part> productParts;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ArrayList<Part> getProductParts() {
        if (this.productParts == null) {
            this.productParts = new ArrayList();
        }
        return productParts;
    }

    public ObservableList<Part> getProductPartsList() {
        ObservableList<Part> ol = FXCollections.observableArrayList();
        ol.addAll(getProductParts());
        return ol;
    }

    public void addAssociatedPart(Part part) {
        if (this.productParts == null) {
            this.productParts = new ArrayList();
        }
        Part pInList = lookupAssociatedPart(part.getPartId());
        if (pInList == null) {
            this.productParts.add(part);
        }

    }

    public boolean removeAssociatedPart(Part part) {
        if (this.productParts.contains(part)) {
            this.productParts.remove(part);
            return true;
        }
        return false;
    }

    public Part lookupAssociatedPart(int partId) {
        Optional<Part> part = productParts.stream().filter(obj -> obj.getPartId() == partId).findFirst();
        return part.orElse(null);
    }

}
