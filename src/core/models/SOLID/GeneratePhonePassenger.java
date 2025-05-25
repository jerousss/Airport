/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.SOLID;

import core.models.Passenger;

/**
 *
 * @author USER
 */
public class GeneratePhonePassenger {
    private Passenger passenger;

    public GeneratePhonePassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public static String generateFullPhone(Passenger passenger) {
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }
}
