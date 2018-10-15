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
        int numSeats = 1;
        Tickets instance = new Tickets();
        instance.bookTicket(name, numSeats);
        assertEquals(instance.availableSeats, 9);
        numSeats = 11;
        instance.bookTicket(name, numSeats);
        assertEquals(instance.availableSeats, 9);
    }
}
