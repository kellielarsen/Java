package com.github.kellielarsen.collectionsproject;
/*
@author kellie
*/
public class Payroll {
    double totalPay = 0;
    
    Payroll(Employee emp) {
        if (emp.taxed == true) 
            totalPay = totalPay + emp.pay;
        else
            throw new UnsupportedOperationException("Tax has not been deducted.");
    }
}
