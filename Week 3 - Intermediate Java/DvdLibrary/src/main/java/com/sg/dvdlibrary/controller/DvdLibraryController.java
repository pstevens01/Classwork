/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author Paul
 */
public class DvdLibraryController {
    private DvdLibraryView view;
    private DvdLibraryDao dao;
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            load();
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDvd();
                        save();
                        break;
                    case 2:
                        removeDvd();
                        save();
                        break;
                    case 3:
                        editDvd();
                        save();
                        break;
                    case 4:
                        listDvdLibrary();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        searchDvdKeyword();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    case 8:
                        searchdMoviesLastXYears();
                        break;
                    case 9:
                        searchMoviesByMpaaRating();
                        break;
                    case 10:
                        searchMoviesByDirector();
                        break;
                    case 11:
                        searchMoviesByStudio();
                        break;
                    case 12:
                        displayAverageAge();
                        break;
                    case 13:
                        diplayNewestMovie();
                        break;
                    case 14:
                        displayOldestMovie();
                        break;
                    case 15:
                        displayAvgNoteLength();
                        break;
                    default:
                        unknownCommand();
                }
            }
            save();
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    // Creates a Controller obj using the view obj and dao obj params. The controller
    // doesn't know, or care,  which implementations of the view and dao objs each 
    // are using. Another example of composition.
    public DvdLibraryController(DvdLibraryView view, DvdLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }
    
    // Method call to print menu and return menu selection.
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    // Method call to add a DVD to the library. A new DVD obj is created using the info passed 
    // from the view class getNewDvdInfo method. The controller then passes the returned DVD obj 
    // to the dao class addDvd method.
    private void addDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    // Method call to list all DVDs in the DVD library. The DVD list obj returned from the
    // dao class getAllDvds method is then passed to the view class displayDvdLibrary method
    // for it to display.
    private void listDvdLibrary() throws DvdLibraryDaoException {
        view.displayDisplayAllDvdBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdLibrary(dvdList);
    }
    
    // Method call to print a selected DVD's information. The returned DVD title string
    // from the view class getDvdChoice method is then passed to the dao class getDvd method 
    // which returns the specified DVD obj. This is then passed to the view class displayDvd 
    // method to output information to the console.
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getDvdChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }
    
    // Method call to remove a DVD from the DVD library. The returned DVD title string
    // from the view class getDvdChoice method is then passed to the dao class removeDvd
    // method.
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayDisplayRemoveDvdBanner();
        String title = view.getDvdChoice();
        Dvd dvd = dao.removeDvd(title);
        view.displayRemoveDvdResults(dvd);
    }
    
    // Method call to edit an existing DVD from the DVD library. The returned DVD title string
    // from the view class getDvdChoice method is then passed to the dao class removeDvd
    // method. A new DVD obj is created using the info passed from the view class getNewDvdInfo
    // method. This new DVD obj is passed to the dao class addDvd method.
    private void editDvd() throws DvdLibraryDaoException {
        view.displayDisplayEditDvdBanner();
        String title = view.getDvdChoice();
        dao.removeDvd(title);
        Dvd editedDvd = view.getNewDvdInfo();
        dao.addDvd(editedDvd.getTitle(), editedDvd);
        view.displayEditResults(editedDvd);
    }
    
    // Method to display all DVDs whose title contains the keyword returned from the view class
    // getDvdTitleKeyword method. A List object containing all DVD library elements is passed to
    // the view class displayDvdKeywordList method along with the keyword and all Dvds whose title
    // contains the keyword are displayed.
    private void searchDvdKeyword() throws DvdLibraryDaoException {
        view.displayDisplaySearchKeywordBanner();
        String keyword = view.getDvdTitleKeyword();
        view.displayDisplaySearchKeywordResult(keyword);
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdKeywordList(keyword, dvdList);      
    }
    
    // Method call to load the DVD library from file to memory
    private void load() throws DvdLibraryDaoException { 
        view.displayLoadBanner();
        dao.loadLibrary();
    }
    
    // Method call to save DVD library from memorty to file
    private void save() throws DvdLibraryDaoException {
        view.displaySaveBanner();
        dao.saveLibrary();
    }
    
    // Method call to display unknown command banner
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    // Method call to display exit message banner
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    // Method to display all movies made in th last X number of years
    private void searchdMoviesLastXYears() {
        int years = view.getNYears();
        List<Dvd> lastXYears = dao.releasedInLastXYears(years);
        view.displayDvdLibrary(lastXYears);
    }
    
    private void searchMoviesByDirector() {
        String director = view.getDirectorName();
        List<Dvd> dvdByDirector = dao.searchByDirector(director);
        view.displayDvdLibrary(dvdByDirector);
    }
    
    // Method to display all movies with a certain MPAA rating
    private void searchMoviesByMpaaRating() {
        String rating = view.getMpaaRating();
        List<Dvd> dvdByRating = dao.searchByMpaaRating(rating);
        view.displayDvdLibrary(dvdByRating);
    }
    
    // Method to display all movies produced by a certain studio
    private void searchMoviesByStudio() {
        String studio = view.getProductionStudioName();
        List<Dvd> dvdByStudio = dao.searchByStudio(studio);
        view.displayDvdLibrary(dvdByStudio);
    }
    
    // Method to display the average age of all movies in the DVD collection
    private void displayAverageAge() {
        double averageAge = dao.avgMovieAge();
        view.displayAvgAge(averageAge);
    }
    
    // Method to display the newest movie in the DVD collection
    private void diplayNewestMovie() {
        Dvd newestMovie = dao.searchNewestMovie();
        view.displayDvd(newestMovie);
    }
    
    // Method to display the oldest movie in the DVD collection
    private void displayOldestMovie() {
        Dvd oldestMovie = dao.searchOldestMovie();
        view.displayDvd(oldestMovie);
    }
    
    // Method to find the average length of notes associated with all movies in the DVD collection
    private void displayAvgNoteLength() {
        double avgNotLength = dao.findAvgNoteLength();
        view.displayAvgNoteLength(avgNotLength);
    }    
}
