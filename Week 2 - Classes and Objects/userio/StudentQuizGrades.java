/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.userio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Paul
 */
public class StudentQuizGrades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // instantiate IO Implementation
        StudentQuizIO quizIO = new StudentQuizIOImpl();
        int selection;
        
        while (true) {
            selection = quizIO.displayMenu();
            switch (selection) {
                case 1:
                    quizIO.printStudentList(); // List all students in the system
                    break;
                case 2:
                    quizIO.addStudent(); // Add student to the system
                    break;
                case 3:
                    quizIO.removeStudent();  // Remove student from the system
                    break;
                case 4:
                    quizIO.studentScores(); // Display student's score
                    break;
                case 5:
                    quizIO.studentAverage(); // Average student's score
                    break;              
            }
        }
    }
    
}
