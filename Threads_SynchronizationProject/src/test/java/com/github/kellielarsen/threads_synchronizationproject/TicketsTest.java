package com.github.kellielarsen.threads_synchronizationproject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class TicketsTest {
    
    public TicketsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of bookTicket method, of class Tickets.
     */
    @Test
    public void testBookTicket() {
        System.out.println("bookTicket");
        String name = "";
        Tickets instance = new Tickets();
        instance.bookTicket(name, 5);
        //should book 5 tickets with 5 remaining
        assertEquals(instance.availableSeats, 5);
        instance.bookTicket(name, 6);
        //should not book any tickets, 5 remaining
        assertEquals(instance.availableSeats, 5);
        instance.bookTicket(name, 1);
        //should book 1 ticket with 4 remaining
        assertEquals(instance.availableSeats, 4);
    }
}
