/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kellielarsen.kattisproblem;

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
        int size = 5;
        int[] grades = {50, 50, 70, 80, 100};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 70.0;
        double result = instance.calculateAverage(size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalculateAverage2() {
        System.out.println("calculateAverage");
        int size = 7;
        int[] grades = {100, 95, 90, 80, 70, 60, 50};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 545 / 7;
        double result = instance.calculateAverage(size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalculateAverage3() {
        System.out.println("calculateAverage");
        int size = 3;
        int[] grades = {70, 90, 80};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 80.0;
        double result = instance.calculateAverage(size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalculateAverage4() {
        System.out.println("calculateAverage");
        int size = 3;
        int[] grades = {70, 90, 81};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 241 / 3;
        double result = instance.calculateAverage(size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalculateAverage5() {
        System.out.println("calculateAverage");
        int size = 9;
        int[] grades = {100, 99, 98, 97, 96, 95, 94, 93};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 863 / 9;
        double result = instance.calculateAverage(size, grades);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of findAboveAverage method, of class AboveAverageProblem.
     */
    @Test
    public void testFindAboveAverage() {
        System.out.println("findAboveAverage");
        double average = 70.0;
        int size = 5;
        int[] grades = {50, 50, 70, 80, 100};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 40.000;
        double result = instance.findAboveAverage(average, size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testFindAboveAverage2() {
        System.out.println("findAboveAverage");
        double average = 545 / 7;
        int size = 7;
        int[] grades = {100, 95, 90, 80, 70, 60, 50};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 400 / 7.000;
        double result = instance.findAboveAverage(average, size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testFindAboveAverage3() {
        System.out.println("findAboveAverage");
        double average = 80;
        int size = 3;
        int[] grades = {70, 90, 80};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 100 / 3.000;
        double result = instance.findAboveAverage(average, size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testFindAboveAverage4() {
        System.out.println("findAboveAverage");
        double average = 241 / 3;
        int size = 3;
        int[] grades = {70, 90, 81};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 200 / 3.000;
        double result = instance.findAboveAverage(average, size, grades);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testFindAboveAverage5() {
        System.out.println("findAboveAverage");
        double average = 863 / 9;
        int size = 9;
        int[] grades = {100, 99, 98, 97, 96, 95, 94, 93};
        AboveAverageProblem instance = new AboveAverageProblem();
        double expResult = 400 / 9.000;
        double result = instance.findAboveAverage(average, size, grades);
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
