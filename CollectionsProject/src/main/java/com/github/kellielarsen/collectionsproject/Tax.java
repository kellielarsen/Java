package com.github.kellielarsen.collectionsproject;
/*
@author kellie
*/
public class Tax {
    double taxRate = 0.10;
    
    Tax(Employee emp) {
        emp.pay = emp.hours * emp.payRate;
        double tax = emp.pay * taxRate;
        emp.pay = emp.pay - tax;
        emp.taxed = true;
    }
}
