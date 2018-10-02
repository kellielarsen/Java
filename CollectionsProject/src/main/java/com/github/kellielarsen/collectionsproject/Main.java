package com.github.kellielarsen.collectionsproject;
import java.util.LinkedList;
/*
@author kellie
*/
public class Main {
    public static void main(String args[]) throws Exception {
        Main main = new Main();
        main.run();
    }
    
    void run() {
        LinkedList<Employee> emps = new LinkedList<Employee>();
        Employee John = new Employee("John", 123456, 10.50, 40);
        John.addEmployee(John, emps);
        John.calculateTax(John);
        Employee Megan = new Employee("Megan", 243654, 11, 36);
        Megan.addEmployee(Megan, emps);
        Megan.calculateTax(Megan);
        Payroll payroll = new Payroll();
        payroll.addToPayroll(emps);
        payroll.displayPayroll(emps);
    }
}
