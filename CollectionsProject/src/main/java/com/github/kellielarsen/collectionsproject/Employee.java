package com.github.kellielarsen.collectionsproject;
/*
@author kellie
*/
public class Employee {
    String name;
    int employeeNum;
    double payRate;
    double hours;
    double pay;
    boolean taxed;
    
    Employee(String _name, int id) {
        name = _name;
        employeeNum = id;
        taxed = false;
    }
}
