/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneStorage;
import javax.swing.JComboBox;

/**
 *
 * @author USER
 */
public class PlaneController {
    
    
    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {
            try {
                
            
            if (id.equals("")) {
                return new Response("id must be not empty", Status.BAD_REQUEST);
            }
            
            if (id.length() < 7 || id.length() > 7) {
                return new Response("id must be exactly 7 character long", Status.BAD_REQUEST);
            }
            
            if(!Character.isUpperCase(id.charAt(0)) || !Character.isUpperCase(id.charAt(1))) {
                return new Response("id must start with two uppercase letters", Status.BAD_REQUEST);
            }
            
            for(int i = 2; i< 7; i++) {
                if(!Character.isDigit(id.charAt(i))) {
                    return new Response("id must end with five digits", Status.BAD_REQUEST);
                }
            }
            
            
            
            }catch(Exception ex) {
                return new Response("Invalid ID format", Status.BAD_REQUEST);
            }
            
            if (brand.equals("")) {
                return new Response("brand must be not empty", Status.BAD_REQUEST);
            }
            
            if (model.equals("")) {
                return new Response("model must be not empty", Status.BAD_REQUEST);
            }
            
            int maxCapacityInt;
            
            try {
                maxCapacityInt = Integer.parseInt(maxCapacity);
                if (maxCapacityInt < 0) {
                    return new Response("maxCapacity must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("maxCapacity must be numeric", Status.BAD_REQUEST);
            }
            
            
            if (airline.equals("")) {
                return new Response("airline must be not empty", Status.BAD_REQUEST);
            }
            
            
          PlaneStorage storage = PlaneStorage.getInstance();

            if (!storage.addPlane(new Plane(id, brand, model, maxCapacityInt, airline))) {
                return new Response("A plane with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Plane created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static void setPlaneIdCombo(JComboBox<String> comboBox) {
        PlaneStorage storage = PlaneStorage.getInstance();
        for (Plane plane : storage.getPlanes()) {
            comboBox.addItem(String.valueOf(plane.getId()));
        }
    }
    
}
