package com.github.kellielarsen.collectionsproject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/* @author kellie */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.github.kellielarsen.collectionsproject.MainTest.class, com.github.kellielarsen.collectionsproject.EmployeeTest.class, com.github.kellielarsen.collectionsproject.PayrollTest.class})
public class CollectionsprojectSuite {

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
