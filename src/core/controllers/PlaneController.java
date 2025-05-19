/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneStorage;

/**
 *
 * @author USER
 */
public class PlaneController {
    
    // MAXCAPACITY FALTA LA VALIDACION XXYYYY
    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {
            int maxCapacityInt;
            
            try {
                maxCapacityInt = Integer.parseInt(maxCapacity);
                if (maxCapacityInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            
            if (id.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (brand.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            if (model.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            if (airline.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            
          PlaneStorage planeStorage = PlaneStorage.getInstance();

            Plane plane = planeStorage.getPlane(id);
            if (plane == null) {
                return new Response("Plane not found", Status.NOT_FOUND);
            }
            return new Response("Plane found", Status.OK, plane);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
