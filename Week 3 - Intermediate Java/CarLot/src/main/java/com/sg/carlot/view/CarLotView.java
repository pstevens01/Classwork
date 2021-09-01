/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carlot.view;

import com.sg.carlot.dto.Car;
import com.sg.carlot.dto.CarKey;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Paul
 */
public class CarLotView {
    
    UserIO io;
    public CarLotView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("Car Lot Main Menu");
        io.print("1. Car Lookup by VIN");
        io.print("2. List All Cars");
        io.print("3. List Cars by Color");
        io.print("4. List Cars Under A Price");
        io.print("5. List Cars by Make and Model");
        io.print("6. Apply Discount to VIN");
        io.print("7. Purchase Vehicle");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public void printCarLookupBaner() {
        io.print("----- Car Lookup by VIN -----");
    }
    
    public void printListAllCarsBanner() {
        io.print("----- List All Cars -----");
    }
    public void printListByColorBanner() {
        io.print("----- All Cars by Color -----");
    }
    
    public void printListUnderPriceBanner() {
        io.print("----- Cars under Price -----");
    }
    
    public void printListCarsByMakeAndModelBanner() {
        io.print("----- Cars by Make and Model -----");
    }
    
    public void printDiscountVINBanner() {
        io.print("----- Discount VIN -----");
    }
    
    public void printBuyVehicleBanner() {
        io.print("----- Buy a Vehicle -----");
    }
    
    public String getVIN() {
        String VIN;
        VIN = io.readString("Please enter the vehicle's VIN");
        return VIN;
    }
    
    public void displayCar(Car retrievedCar) {
        io.print("VIN: " + retrievedCar.getVIN());
        io.print("Make: " + retrievedCar.getMake());
        io.print("Model: " + retrievedCar.getModel());
        io.print("Color: " + retrievedCar.getColor());
        io.print("Mileage: " + retrievedCar.getOdometerMiles());
        io.print("\nPrice: " + retrievedCar.getPrice());
        
        io.readString("Press Enter to Continue");
    }
    
    public Car getNewCarDetails() {
    Car newCar = new Car();
    String VIN = io.readString("Vin: ");
    newCar.setVIN(VIN);
    newCar.setMake(io.readString("Make: "));
    newCar.setModel(io.readString("Model: "));
    newCar.setColor(io.readString("Color: "));
    // Using 0 for min, Using max long - 1 for max
    newCar.setOdometerMiles(io.readLong("Mileage: ", 0, 9223372036854775807l));
    CarKey newKey = new CarKey();
    newKey.setVIN(VIN);
    newKey.setLaserCut(true);
    newCar.setKey(newKey);
    long priceLong = io.readLong("Price: ");
    BigDecimal price = BigDecimal.valueOf(priceLong);
    newCar.setPrice(price);
    return newCar;
    }
    
    public void printCarList(List<Car> carList) {
        for (Car vehicle : carList) {
            io.print(vehicle.getVIN() + " - " + vehicle.getMake() + " " + vehicle.getModel());
        }
        io.readString("Press Enter to Continue");
    }
    
    public String getColorToList() {
        return io.readString("What color cars would you like to see?");
    }
    
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(io.readLong("Please etner the maximum price you'd like to see"));
    }
    
    public String getMake() {
        return io.readString("Make: ");
    }
    
    public String getModel() {
        return io.readString("Model: ");
    }
    
    public void displayPrice(Car retrievedCar) {
        io.print("Current price: " + retrievedCar.getPrice().toString()); 
    }
    
    public BigDecimal getDiscountPct() {
        return BigDecimal.valueOf((io.readDouble("Please enter a discount percentage: ", 0, 100)) / 100d);
    }
    
    public BigDecimal getCashPaid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
