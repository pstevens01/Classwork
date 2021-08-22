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
public class Circle extends Shape {
    private float area;
    private float perimeter;

    public Circle(float area, float perimeter, String color) {
        super(color);
        this.area = area;
        this.perimeter = perimeter;
    }
    
    @Override
    public float getPerimeter() {
        return this.perimeter;
    }

    @Override
    public float getArea() {
        return this.area;
    }
    
}
