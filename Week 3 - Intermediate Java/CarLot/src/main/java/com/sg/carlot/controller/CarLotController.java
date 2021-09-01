/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carlot.controller;

import com.sg.carlot.dto.Car;
import com.sg.carlot.dto.CarKey;
import com.sg.carlot.service.CarLotNoSuchCarException;
import com.sg.carlot.service.CarLotOverpairPriceException;
import com.sg.carlot.service.CarLotService;
import com.sg.carlot.service.CarLotUnderpaidPriceException;
import com.sg.carlot.view.CarLotView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Paul
 */
public class CarLotController {
    private CarLotView view;
    private CarLotService service;
    
    public CarLotController(CarLotView view, CarLotService service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() throws CarLotNoSuchCarException, CarLotUnderpaidPriceException, CarLotOverpairPriceException {
        boolean keepGoing = true;
        int selectAction;
        
        do {
            selectAction = view.printMenuAndGetSelection();
            
            switch (selectAction) {
                case 1:
                    carLookup();
                    break;
                case 2:
                    listAllCars();
                    break;
                case 3:
                    listByColor();
                    break;
                case 4:
                    listUnderPrice();
                    break;
                case 5:
                    listCarsByMakeAndModel();
                    break;
                case 6:
                    discountVIN();
                    break;
                case 7:
                    buyVehicle();
                    break;
            }
        } while (keepGoing);
    }
    
    private void carLookup() {
        view.printCarLookupBaner();
        String VIN = view.getVIN();
        Car retrievedCar = service.getACar(VIN);
        view.displayCar(retrievedCar);
    }
    
    private void listAllCars() {
        view.printListAllCarsBanner();
        List<Car> carList = service.getAllCars();
        view.printCarList(carList);
    }
    
    private void listByColor() {
        view.printListByColorBanner();
        String colorToList = view.getColorToList();
        List<Car> carsByColor = service.getCarsByColor(colorToList);
        view.printCarList(carsByColor);
    }
    
    private void listUnderPrice() {
        view.printListUnderPriceBanner();
        BigDecimal price = view.getPrice();
        List<Car> carsUnderPrice = service.getCarsInBudget(price);
        view.printCarList(carsUnderPrice);
    }
    
    private void listCarsByMakeAndModel() {
        view.printListCarsByMakeAndModelBanner();
        String make = view.getMake();
        String model = view.getModel();
        List<Car> carsByMakeModel = service.getCarByMakeAndModel(make, model);
        view.printCarList(carsByMakeModel);
    }
    
    private void discountVIN() {
        view.printDiscountVINBanner();
        
        boolean getDiscountSuccess = true;
        do {
            String VIN = view.getVIN();
            Car retrievedCar = service.getACar(VIN);
            view.displayPrice(retrievedCar);
            BigDecimal dicountPct = view.getDiscountPct();
            BigDecimal newPrice = null;
            try {
                newPrice = service.discountCar(VIN, dicountPct);
            } catch (CarLotNoSuchCarException e) {
                getDiscountSuccess = false;
            }
            retrievedCar.setPrice(newPrice);
            service.updateCar(retrievedCar);
        } while(getDiscountSuccess = false);
    }
    
    private void buyVehicle() throws CarLotNoSuchCarException, CarLotOverpairPriceException, CarLotUnderpaidPriceException {
        view.printBuyVehicleBanner();
        boolean buyVehicleSuccess = true;
        do {
            String VIN = view.getVIN();
            Car carToBeSold = service.getACar(VIN);
            view.displayPrice(carToBeSold);
            BigDecimal cashPaid = view.getCashPaid();
            try {
                CarKey key = service.sellCar(VIN, cashPaid);
            } catch (CarLotNoSuchCarException e) {
                buyVehicleSuccess = false;
            } catch (CarLotOverpairPriceException e) {
                buyVehicleSuccess = false;
            } catch (CarLotUnderpaidPriceException e) {
                buyVehicleSuccess = false;
            }
        } while (buyVehicleSuccess = false);
    }
}
