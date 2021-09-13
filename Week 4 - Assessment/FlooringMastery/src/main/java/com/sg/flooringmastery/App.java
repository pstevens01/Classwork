/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDaoImpl;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.flooringmastery.userIO.UserIO;
import com.sg.flooringmastery.userIO.UserIOConsoleImpl;
import com.sg.flooringmastery.view.FlooringMasteryView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Paul
 */
public class App {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // UserIO myIo = new UserIOConsoleImpl();
       // FlooringMasteryView myView = new FlooringMasteryView(myIo);
       // FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
       // FlooringMasteryAuditDao myAuditDao = new FlooringMasteryAuditDaoImpl();
       // FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(myDao, myAuditDao);
       // FlooringMasteryController controller = new FlooringMasteryController(myService, myView);
       // controller.run();

       AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
       appContext.scan("com.sg.flooringmastery");
       appContext.refresh();
       
       FlooringMasteryController controller = appContext.getBean("flooringMasteryController",
               FlooringMasteryController.class);
       controller.run();
    }
}
