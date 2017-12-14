/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.TextField;

/**
 *
 * @author Abhi
 */
public class Validator {
    
    public static boolean hasRequiredValue(TextField t,String fieldName) throws Exception{
        if(!hasValue(t)){            
            Helper.throwException(fieldName+" cannot be empty");        
            return false;
        }
        return true;
    }
    
    public static boolean hasValue(TextField t){
        if(t.getText().equals(null) || t.getText().trim().equals("")){                       
            return false;
        }
        return true;
    }
    
    public static boolean hasPartId(TextField t){
        try{
            Helper.getInt(t);
            return true;
        }catch(Exception e){
            //do nothing
            return false;
        }
    }
    
    public static boolean isInteger(TextField t,String fieldName) throws Exception{
        try{
            Helper.getInt(t);
            return true;
        }catch(Exception e){
            Helper.throwException(fieldName+" must be a whole number");            
        }
        return false;
    }
    
    public static boolean isDouble(TextField t,String fieldName) throws Exception{
        try{
            Helper.getDouble(t);
            return true;
        }catch(Exception e){
            Helper.throwException(fieldName+" must be a number");            
        }
        return false;
    }
    
    public static boolean isGreater(int max, int min){
        if(max>=min){
            return true;
        }
        return false;
    }
    
    public static boolean isBetween(int max, int min, int val){
        if(val>min && val<max){
            return true;
        }else if(val==min || val==max){
            return true;
        }
        return false;
    }
    
}
