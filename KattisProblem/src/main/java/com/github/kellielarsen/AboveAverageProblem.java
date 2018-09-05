/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kellielarsen;
/*
 * @author kellie
 */

public class AboveAverageProblem {
    public static double calculateAverage(int size, int grades[]) {
        int sum = 0;
        double average = 0.000;
        for (int i = 0; i < size; i++) {
            sum = sum + grades[i];
        }
        average = (sum / size) * 1.000;
        return average;
    }
    
    public static double findAboveAverage(double average, int size, int grades[]) {
        int count = 0;
        double aboveAverage = 0;
        for (int i = 0; i < size; i++) {
            if (grades[i] > average)
                count++;
        }
        aboveAverage = (count / size) * 1.000;
        return aboveAverage;
    }
    
    public static void main(String [] args) throws Exception {
        AboveAverageProblem instance = new AboveAverageProblem();
        int grades[] = {};
        int numRecords = 0, size = 0;
        double average = 0, aboveAverage = 0;
        numRecords = System.in.read();
        for (int i = 0; i < numRecords; i++) {
            size = System.in.read();
            for (int j = 0; j < size; j++) 
                grades[i] = System.in.read();
            average = calculateAverage(size, grades);
            aboveAverage = findAboveAverage(average, size, grades);
            System.out.println(aboveAverage + '%');
        }
    }
}
