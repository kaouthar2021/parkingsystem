package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {


    //ajouter un deuxiéme  argument ticketNumber pour vérifier si un utilisateur est reccurent
    public void calculateFare(Ticket ticket,int ticketNumber) {
        //recurrent true si ticketNumber >1
        boolean recurrent=ticketNumber>1;
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }
        //Nous utiliserons la méthode getTime pour obtenir l'heure en millisecondes, puis convertissez-le en heures.
        //La variable de durée doit être un double.
        // int inHour = ticket.getInTime().getHours();
        // int outHour = ticket.getOutTime().getHours();
        double inHour = ticket.getInTime().getTime();
        double outHour = ticket.getOutTime().getTime();


        //TODO: Some tests are failing here. Need to check if this logic is correct
        // int duration = outHour - inHour;
        double duration = outHour - inHour;
        duration = duration / (1000 * 60 * 60);
       // si la durée est inférieur à 30 minutes le parking est gratuit
        if (duration <= 0.5) {
            ticket.setPrice(0);

        }else {


            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                    //Cette méthode renvoie l'arrondi entier au plus proche de la valeur spécifiée en paramètre
                   double res = Math.round(duration * Fare.CAR_RATE_PER_HOUR * 100.0) / 100.0;
                    ticket.setPrice(res);
                    break;
                }
                case BIKE: {
                    ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                    double res = Math.round(duration * Fare.BIKE_RATE_PER_HOUR* 100.0) / 100.0;
                    ticket.setPrice(res);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unkown Parking Type");
            }

        }
        // affecter une reduction de 5% pour les utilisateures reccurents
         if(recurrent){
             ticket.setPrice(ticket.getPrice()*0.95);
         }


    }

}