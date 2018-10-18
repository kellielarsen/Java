package com.github.kellielarsen.threads_synchronizationproject;

/* @author kellie */
public class Tickets {
    int availableSeats = 10;
    
    public synchronized void bookTicket(String name, int numSeats) {
        if ((availableSeats >= numSeats) && (numSeats > 0)) {
            System.out.println(name + ": " + numSeats + " ticket(s) booked successfully.");
            availableSeats -= numSeats;
        }
        else if (numSeats == 0)
            System.out.println(name + ": No tickets requested.");
        else if (numSeats < 0)
            System.out.println(name + ": Error - invalid number of seats requested.");
        else
            System.out.println(name + ": " + numSeats + " seats are not available.");
    }
}
