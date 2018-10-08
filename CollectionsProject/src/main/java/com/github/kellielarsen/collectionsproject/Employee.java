package com.github.kellielarsen.collectionsproject;
import java.util.LinkedList;

/* @author kellie */
public class Employee {
    String name;
    int id;
    double payRate;
    double hours;
    double pay;
    double taxRate = 0.10;
    boolean taxed;
    
    Employee(String _name, int _id, double _payRate, double _hours) {
        name = _name;
        id = _id;
        payRate = _payRate;
        hours = _hours;
        taxed = false;
    }
    
    void calculateTax(Employee emp) {
        emp.pay = emp.hours * emp.payRate;
        double tax = emp.pay * taxRate;
        emp.pay = emp.pay - tax;
        emp.taxed = true;
    }
    
    void addEmployee(Employee emp, LinkedList<Employee> emps) {
        emps.add(emp);
    }
}
