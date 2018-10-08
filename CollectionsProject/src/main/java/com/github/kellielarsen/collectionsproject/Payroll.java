package com.github.kellielarsen.collectionsproject;
import java.text.DecimalFormat;
import java.util.LinkedList;

/* @author kellie */
public class Payroll {
    
    double totalPay = 0;
    void addToPayroll(LinkedList<Employee> emps) {
        for (int i = 0; i < emps.size(); i++) {
            if (emps.get(i).taxed == true) 
                totalPay = totalPay + emps.get(i).pay;
            else
                throw new UnsupportedOperationException("Tax has not been deducted.");
        }
    }
    
    void displayPayroll(LinkedList<Employee> emps) {
        DecimalFormat dec = new DecimalFormat("0.00");
        System.out.println("******************PAYROLL******************");
        String s = String.format("%-15s%-15s%-15s", "Employee ID", "Employee Name", "Employee Pay");
        System.out.println(s);
        for (int i = 0; i < emps.size(); i++) {
            Employee emp = emps.get(i);
            s = String.format("%-15s%-15s%-15s", emp.id, emp.name, dec.format(emp.pay));
            System.out.println(s);
        }
        System.out.println("*******************************************");
        System.out.println("Total pay: " + dec.format(totalPay));
        System.out.println("*******************************************");
    }
}
