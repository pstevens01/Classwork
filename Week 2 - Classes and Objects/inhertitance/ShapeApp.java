/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.inhertitance;

/**
 *
 * @author Paul
 */
public class ShapeApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circle circle = new Circle(10, 15, "Blue");
        Square square = new Square(20, 40, "Yellow");
        Triangle triangle = new Triangle(7, 10, "Purple");
        Rectangle rectangle = new Rectangle(30, 70, "Green");
        
        System.out.println("Area of circle is: " + circle.getArea());
        System.out.println("Perimeter of circle is: " + circle.getPerimeter());
        
        System.out.println("Area of square is: " + square.getArea());
        System.out.println("Perimeter of square is: " + square.getPerimeter());
        
        System.out.println("Area of triangle is: " + triangle.getArea());
        System.out.println("Perimeter of triangle is: " + triangle.getPerimeter());
        
        System.out.println("Area of rectangle is: " + rectangle.getArea());
        System.out.println("Perimeter of rectangle is: " + rectangle.getPerimeter());
    }
    
}
