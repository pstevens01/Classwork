/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carlot.app;

import com.sg.carlot.controller.CarLotController;
import com.sg.carlot.dao.CarLotDAO;
import com.sg.carlot.dao.CarLotDAOFileImpl;
import com.sg.carlot.service.CarLotNoSuchCarException;
import com.sg.carlot.service.CarLotOverpairPriceException;
import com.sg.carlot.service.CarLotService;
import com.sg.carlot.service.CarLotServiceImpl;
import com.sg.carlot.service.CarLotUnderpaidPriceException;
import com.sg.carlot.view.CarLotView;
import com.sg.carlot.view.UserIO;
import com.sg.carlot.view.UserIOConsoleImpl;

/**
 *
 * @author Paul
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CarLotNoSuchCarException, CarLotUnderpaidPriceException, CarLotOverpairPriceException {
        UserIO myIo = new UserIOConsoleImpl();
        CarLotView myView = new CarLotView(myIo);
        CarLotDAO myDao = new CarLotDAOFileImpl();
        CarLotService myService = new CarLotServiceImpl(myDao);
        CarLotController controller = new CarLotController(myView, myService);
        controller.run();
    }
    
}
