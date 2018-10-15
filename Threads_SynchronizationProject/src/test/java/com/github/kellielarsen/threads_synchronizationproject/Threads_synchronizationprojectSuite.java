/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kellielarsen.threads_synchronizationproject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author kelli
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.github.kellielarsen.threads_synchronizationproject.MainTest.class, com.github.kellielarsen.threads_synchronizationproject.TicketsTest.class, com.github.kellielarsen.threads_synchronizationproject.TicketThreadTest.class})
public class Threads_synchronizationprojectSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
