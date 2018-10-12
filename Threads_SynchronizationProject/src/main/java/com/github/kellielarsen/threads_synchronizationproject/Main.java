package com.github.kellielarsen.threads_synchronizationproject;

/* @author kellie */
public class Main {
    public static void main(String args[]) {
        Tickets ticketCount = new Tickets();
        Thread p1 = new Thread(new TicketThread(ticketCount, "pass1", 2));
        Thread p2 = new Thread(new TicketThread(ticketCount, "pass2", 1));
        Thread p3 = new Thread(new TicketThread(ticketCount, "pass3", 3));
        Thread p4 = new Thread(new TicketThread(ticketCount, "pass4", 2));
        Thread p5 = new Thread(new TicketThread(ticketCount, "pass5", 4));
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
