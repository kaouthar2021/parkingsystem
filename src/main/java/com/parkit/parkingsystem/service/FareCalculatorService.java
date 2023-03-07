package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {


    public void calculateFare(Ticket ticket,int ticketNumber) {
        boolean recurrent=ticketNumber>1;
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }

        // int inHour = ticket.getInTime().getHours();
        // int outHour = ticket.getOutTime().getHours();
        double inHour = ticket.getInTime().getTime();
        double outHour = ticket.getOutTime().getTime();


        //TODO: Some tests are failing here. Need to check if this logic is correct
        // int duration = outHour - inHour;
        double duration = outHour - inHour;
        duration = duration / (1000 * 60 * 60);
        if (duration <= 0.5) {
            ticket.setPrice(0);

        }else {


            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    double res = Math.round(duration * Fare.CAR_RATE_PER_HOUR * 100.0) / 100.0;
                    ticket.setPrice(res);
                    break;
                }
                case BIKE: {
                    double res = Math.round(duration * Fare.BIKE_RATE_PER_HOUR* 100.0) / 100.0;
                    ticket.setPrice(res);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unkown Parking Type");
            }

        }




    }

}