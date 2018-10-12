package com.github.kellielarsen.threads_synchronizationproject;

/* @author kellie */
public class TicketThread implements Runnable {
    public Tickets ticketCount;
    private String name;
    private int numSeats;
    public TicketThread(Tickets ticketCount, String name, int numSeats) {
        this.ticketCount = ticketCount;
        this.name = name;
        this.numSeats = numSeats;
    }
    public void run() {
        ticketCount.bookTicket(name, numSeats);
    }
}
