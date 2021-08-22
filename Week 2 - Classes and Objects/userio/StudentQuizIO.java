/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.userio;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Paul
 */
public interface StudentQuizIO {
    
    int displayMenu();
    
    void printStudentList();
    
    void addStudent();
    
    void removeStudent();
    
    void studentScores();
    
    void studentAverage();
    
}
