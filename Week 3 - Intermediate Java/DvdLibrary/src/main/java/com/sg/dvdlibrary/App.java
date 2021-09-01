/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdLibraryController;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Paul
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl(); // Implements UI obj with the Console UI implementation
        DvdLibraryView myView = new DvdLibraryView(myIo); // Initialize View obj with console UI implementation obj
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl(); // Initalize DAO obj with DAO File implementation
        
        // Without known which impementations its using, the controller is initialized with the view and DOA objects
        DvdLibraryController controller = new DvdLibraryController(myView, myDao);
        
        controller.run();
    }
}
