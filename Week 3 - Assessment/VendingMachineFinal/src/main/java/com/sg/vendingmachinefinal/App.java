/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal;

import com.sg.vendingmachinefinal.controller.VendingMachineController;
import com.sg.vendingmachinefinal.dao.VendingMachineAuditDaoImpl;
import com.sg.vendingmachinefinal.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachinefinal.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachinefinal.ui.VendingMachineView;
import com.sg.vendingmachinefinal.ui.UserIO;
import com.sg.vendingmachinefinal.ui.UserIOConsoleImpl;
import com.sg.vendingmachinefinal.dao.VendingMachineAuditDao;
import com.sg.vendingmachinefinal.dao.VendingMachineDao;
import com.sg.vendingmachinefinal.service.VendingMachineServiceLayer;

/**
 *
 * @author Paul
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
    
}
