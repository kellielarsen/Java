package com.github.kellielarsen.collectionsproject;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class PayrollTest {
    
    public PayrollTest() {
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

    /* Test of addToPayroll method, of class Payroll. */
    @Test
    public void testAddToPayroll() {
        System.out.println("addToPayroll");
        LinkedList<Employee> emps = new LinkedList<>();
        Employee emp1 = new Employee("emp1", 12345, 15, 40);
        Employee emp2 = new Employee("emp2", 54321, 10, 40);
        emps.add(emp1);
        emps.add(emp2);
        emp1.calculateTax(emp1);
        emp2.calculateTax(emp2);
        Payroll instance = new Payroll();
        instance.addToPayroll(emps);
        double pay = 900.0;
        assert(instance.totalPay == pay);
    }
}
