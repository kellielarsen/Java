package com.github.kellielarsen.collectionsproject;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class EmployeeTest {
    
    public EmployeeTest() {
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

    /* Test of calculateTax method, of class Employee. */
    @Test
    public void testCalculateTax() {
        System.out.println("calculateTax");
        Employee emp = new Employee("Name", 12345, 10, 20);
        emp.calculateTax(emp);
        double expectedPay = 180.0;
        assert(expectedPay == emp.pay);
    }   
}
