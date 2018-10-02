package com.github.kellielarsen.collectionsproject;
/*
@author kellie
*/
public class Main {
    
    public static void main(String args[]) throws Exception {
        Employee emp = new Employee("name", 123456);
        emp.payRate = 10.50;
        emp.hours = 40;
        Tax emptax = new Tax(emp);
        Payroll emppay = new Payroll(emp);
    }
}
