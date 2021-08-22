/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.userio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class StudentQuizIOImpl implements StudentQuizIO {

    private Map<String, List<Integer>> studentGrades = new HashMap<>();
    
    @Override
    public int displayMenu() {
        Scanner in = new Scanner(System.in);
        int choice;
        System.out.println("====Student's Quiz Grade Application====");
        System.out.println("1- View list of students in the system\n2- Add a student\n"
                + "3- Remove a student\n4- View student's quiz scores\n5- View student's quiz scores average");
        choice = Integer.parseInt(in.nextLine());
        return choice;
    }
    
    @Override
    public void printStudentList() {
        System.out.println("List of all the students in the system:");
        for (String k : studentGrades.keySet()) {
            System.out.println(k);
        }
    }

    @Override
    public void addStudent() {
        System.out.println("Enter the name of the student you wish to add:");
        Scanner in = new Scanner(System.in);
        int scoreInput;
        List<Integer> scores = new ArrayList<>();
        String name = in.nextLine();
        
        do {
            System.out.println("Please enter the student's score or -1 to exit.");
            scoreInput = Integer.parseInt(in.nextLine());
            if (scoreInput > 0) {
                scores.add(scoreInput);
            } else {
                break;
            }
        } while (true);
        
        studentGrades.put(name, scores);
    }

    @Override
    public void removeStudent() {
        System.out.println("Enter the name of the student you want to remove:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        studentGrades.remove(name);
    }

    @Override
    public void studentScores() {
        System.out.println("Which student's scores would you like?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
       
        System.out.println(name + "'s Quiz Scores:");
        System.out.println(studentGrades.get(name));
    }

    @Override
    public void studentAverage() {
        System.out.println("Which student's average would you like to calculate?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int elements = studentGrades.get(name).size();
        int avg, sum = 0;
        
        for (int score : studentGrades.get(name)) {
            sum += score;
        }
        
        avg = sum / elements;
        System.out.println(name + "'s Quiz Score Average:\n" + avg);
    }
}
