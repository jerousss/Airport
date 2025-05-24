/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class PlaneStorage {

    // Instancia Singleton
    private static PlaneStorage instance;

    // Atributos del Storage
    private ArrayList<Plane> planes;

    private PlaneStorage() {
        this.planes = new ArrayList<>();
    }

    public static PlaneStorage getInstance() {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }

    public boolean addPlane(Plane plane) {
        for (Plane pl : this.planes) {
            if (pl.getId().equals(plane.getId())) {
                return false;
            }
        }
        this.planes.add(plane);
        return true;
    }

    public Plane getPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }

    public boolean delPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                this.planes.remove(plane);
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Plane> getPlanes(){
        return planes;
    }
}
