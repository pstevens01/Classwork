/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carlot.dao;

import com.sg.carlot.dto.Car;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Paul
 */
public class CarLotDAOFileImpl implements CarLotDAO {
    private Map<String, Car> cars = new HashMap<>();
    public final String CAR_DELIMITER = ":_:";
    public final String KEY_DELIMITER = "'-'";
    public final String CAR_LOT = "carlot.txt";
    
    
    @Override
    public Car addCar(String VIN, Car car) {
        Car returnedCar = null;
        if(cars.containsKey(VIN)) {
            returnedCar = cars.get(VIN);
        } else cars.put(VIN, car);
        return returnedCar;
    }

    @Override
    public Car getCar(String VIN) {
        return cars.get(VIN);
    }

    @Override
    public List<Car> getCars() {
        Set<String> vinList = cars.keySet();
        List<Car> carList = new ArrayList<>();
        
        for (String VIN : vinList) {
            carList.add(cars.get(VIN));
        }
        return carList;
    }

    @Override
    public void editCar(String VIN, Car car) {
        cars.replace(VIN, car);
    }

    @Override
    public Car removeCar(String VIN) {
        Car removedCar = new Car();
        if (cars.containsKey(VIN)) {
            removedCar = cars.remove(VIN);
        } else {
            removedCar = null;
        }
        return removedCar;
    }
    
}
