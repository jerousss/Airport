/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tablecontrol;

import core.controllers.FlightController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Sresponsability.CalcArrivalDateFlight;
import core.models.storage.FlightStorage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class TableController {

    public static Response refreshTableFlights(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ArrayList<Flight> flights = FlightStorage.getInstance().getFlightsSorted();

            if (flights.isEmpty()) {
                return new Response("List of flights is empty", Status.NO_CONTENT);
            } else {

                for (Flight fl : flights) {
                    LocalDateTime arrivalDateTime = CalcArrivalDateFlight.calculateArrivalDate(fl);
                    model.addRow(new Object[]{fl.getId(),fl.getPlane().getId(),fl.getDepartureLocation().getAirportId(),fl.getArrivalLocation().getAirportId(),fl.getDepartureDate(), arrivalDateTime, fl.getPlane().getId(),fl.getNumPassengers()});
                }
                return new Response("List of flights updated correctly", Status.OK);
            }
        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
