/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kellielarsen.kattisproblem;
/* @author kellie */
import java.util.Scanner;
import java.io.PrintStream;
import java.lang.Math;
import java.text.DecimalFormat;
        
public class AboveAverageProblem {
    public double calculateAverage(int size, int grades[]) {
        int sum = 0;
        double average;
        for (int i = 0; i < size; i++) {
            sum = sum + grades[i];
        }
        average = (sum / size);
        return average;
    }
    
    public double findAboveAverage(double average, int size, int grades[]) {
        double count = 0;
        double aboveAverage;
        for (int i = 0; i < size; i++) {
            if (grades[i] > average)
                count++;
        }
        aboveAverage = (count / size) * 100.000;
        return aboveAverage;
    }
    
    public static void main(String [] args) throws Exception {
        AboveAverageProblem instance = new AboveAverageProblem();
        int grades[];
        grades = new int[15];
        int numRecords, size;
        double average, aboveAverage;
        double printAboveAverage;
        PrintStream print;
        print = new PrintStream(System.out);
        try (Scanner scan = new Scanner(System.in)) {
            while (scan.hasNextInt()) {
                numRecords = scan.nextInt();
                for (int i = 0; i < numRecords; i++) {
                    size = scan.nextInt();
                    for (int j = 0; j < size; j++) 
                        grades[j] = scan.nextInt();
                    average = instance.calculateAverage(size, grades);
                    aboveAverage = instance.findAboveAverage(average, size, grades);
                    DecimalFormat dx = new DecimalFormat("#.000");
                    printAboveAverage = Math.round(aboveAverage * 1000.0) / 1000.0;
                    print.print(dx.format(printAboveAverage) + '%' + "\n");
                }
            }
            scan.close();
        }
    }
}