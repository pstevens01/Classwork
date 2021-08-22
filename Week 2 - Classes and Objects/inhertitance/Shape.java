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
public abstract class Shape {
    protected String color;
    
    protected Shape(String color) {
        this.color = color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    abstract float getPerimeter();
    abstract float getArea();
}
