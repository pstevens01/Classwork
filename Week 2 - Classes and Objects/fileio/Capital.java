/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.fileio;

/**
 *
 * @author Paul
 */
public class Capital {

    private String name;
    private String population;
    private String sqMiles;
    
    public Capital(String name, String population, String sqMiles) {
        this.name = name;
        this.population = population;
        this.sqMiles = sqMiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return Integer.parseInt(population);
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public double getSqMiles() {
        return Double.parseDouble(sqMiles);
    }

    public void setSqMiles(String sqMiles) {
        this.sqMiles = sqMiles;
    }
    
    
}
