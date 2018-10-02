package com.github.kellielarsen.collectionsproject;
/*
@author kellie
*/
public class Main {
    
    public static void main(String args[]) throws Exception {
        Employee Chris = new Employee("Chris", 123456);
        Chris.payRate = 10.50;
        Chris.hours = 40;
        Tax ChrisTax = new Tax(Chris);
        Payroll ChrisPay = new Payroll(Chris);
    }
}
