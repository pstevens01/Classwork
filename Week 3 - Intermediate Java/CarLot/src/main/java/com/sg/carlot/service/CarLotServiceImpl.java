/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carlot.service;

import com.sg.carlot.dao.CarLotDAO;
import com.sg.carlot.dto.Car;
import com.sg.carlot.dto.CarKey;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul
 */
public class CarLotServiceImpl implements CarLotService {

    CarLotDAO dao;

    public CarLotServiceImpl(CarLotDAO dao) {
        this.dao = dao;
    }
    
    @Override
    public Car getACar(String VIN) {
        return dao.getCar(VIN);
    }

    @Override
    public List<Car> getAllCars() {
        return dao.getCars();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        List<Car> carList = dao.getCars();
        List<Car> carsByColor = new ArrayList<>();
        for (Car c : carList) {
            if (c.getClass().equals(color)) {
                carsByColor.add(c);
            }
        }
        return carsByColor;
    }

    @Override
    public List<Car> getCarsInBudget(BigDecimal maxPrice) {
        List<Car> carsList = dao.getCars();
        List<Car> carsUnderPrice = new ArrayList<>();
        for (Car c : carsList) {
            if ((c.getPrice().compareTo(maxPrice)) < 1)  {
                carsUnderPrice.add(c);
            }
        }
        return carsUnderPrice;
    }

    @Override
    public List<Car> getCarByMakeAndModel(String make, String model) {
        List<Car> carsList = dao.getCars();
        List<Car> carsByMakeAndModel = new ArrayList<>();
        for (Car c : carsList) {
            if ((make.equals(c.getMake())) && (model.equals(c.getModel()))) {
                carsByMakeAndModel.add(c);
            }
        }
        return carsByMakeAndModel;
    }

    @Override
    public BigDecimal discountCar(String VIN, BigDecimal percentDiscount) throws CarLotNoSuchCarException {
        Car carToModify = getACar(VIN);
        BigDecimal oldPrice = carToModify.getPrice();
        BigDecimal newPrice = oldPrice.multiply(BigDecimal.ONE.subtract(percentDiscount));
        return newPrice;
    }

    @Override
    public CarKey sellCar(String VIN, BigDecimal cashPaid) throws CarLotNoSuchCarException, CarLotOverpairPriceException, CarLotUnderpaidPriceException {
        Car soldCar = dao.removeCar(VIN);
        if (soldCar == null) {
            throw new CarLotNoSuchCarException("No such car exists.");
        }
        if (cashPaid.compareTo(soldCar.getPrice()) > 0) {
            throw new CarLotOverpairPriceException("Overpaid.");
        }
        if (cashPaid.compareTo(soldCar.getPrice()) < 0) {
            throw new CarLotUnderpaidPriceException("Underpaid.");
        }
        return soldCar.getKey();
    }

    @Override
    public void updateCar(Car retrievedCar) {
        dao.editCar(retrievedCar.getVIN(), retrievedCar);
    }
    
}
