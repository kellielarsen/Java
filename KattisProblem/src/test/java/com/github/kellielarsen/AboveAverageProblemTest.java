/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kellielarsen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kelli
 */
public class AboveAverageProblemTest {
    
    public AboveAverageProblemTest() {
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
     * Test of calculateAverage method, of class AboveAverageProblem.
     */
    @Test
    public void testCalculateAverage() {
        System.out.println("calculateAverage");
        int size = 0;
        int[] grades = null;
        double expResult = 0.0;
        double result = AboveAverageProblem.calculateAverage(size, grades);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of findAboveAverage method, of class AboveAverageProblem.
     */
    @Test
    public void testFindAboveAverage() {
        System.out.println("findAboveAverage");
        double average = 0.0;
        int size = 0;
        int[] grades = null;
        double expResult = 0.0;
        double result = AboveAverageProblem.findAboveAverage(average, size, grades);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of main method, of class AboveAverageProblem.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        AboveAverageProblem.main(args);
    }
    
}