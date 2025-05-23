/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Sresponsability;

import core.models.Passenger;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author USER
 */
public class CalcAgePassenger {
    private Passenger passenger;

    public CalcAgePassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public static int calculateAge(Passenger passenger) {
        return Period.between(passenger.getBirthDate(), LocalDate.now()).getYears();
    }
}
