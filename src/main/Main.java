/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import core.models.Json.RJSONFlight;
import core.models.Json.RJSONLocation;
import core.models.Json.RJSONPassenger;
import core.models.Json.RJSONPlane;
import core.views.AirportFrame;
import static java.lang.System.setProperty;
import javax.swing.UIManager;

/**
 *
 * @author GERONIMO
 */
public class Main {
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        RJSONPassenger.rJsonPassenger();
        RJSONPlane.rJsonPlane();
        RJSONLocation.rJsonLocation();
        RJSONFlight.rJsonFlight();
        
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirportFrame().setVisible(true);
            }
        });
    }
}
