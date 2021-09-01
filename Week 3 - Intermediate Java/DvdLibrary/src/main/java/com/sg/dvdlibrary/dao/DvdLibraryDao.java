/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Paul
 */
public interface DvdLibraryDao {
    /**
     * Adds the DVD to the library and associates it with the given
     * title. If there is already a DVD associated with the given
     * title it will return that DVD object, otherwise it will
     * return null.
     *
     * @param title name with which DVD is to be associated
     * @param dvd DVD to be added to the library.
     * @return the DVD object previously associated with the given  
     * title if it exists, null otherwise
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;
    
    /**
     * Returns a List of all DVDs in the library.
     *
     * @return List containing all DVDs in the library.
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    
    /**
     * Returns the DVD object associated with the given title.
     * Returns null if no such DVD exists
     *
     * @param title title of the DVD to retrieve
     * @return the DVD object associated with the given title,  
     * null if no such DVD exists
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException;
    
    /**
     * Removes from the library the DVD associated with the given title.
     * Returns the DVD object that is being removed or null if
     * there is no DVD associated with the given title
     *
     * @param title title of the DVD to be removed
     * @return DVD object that was removed or null if no DVD
     * was associated with the given title
     * 
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException;
    
    /**
     * Saves the DVD library from memory to file system. This is done after
     * every add, remove, or edit call so that the correct DVD count and
     * list is displayed for the user when needed. The library is loaded
     * one time when the program runs. 
     */
    void saveLibrary() throws DvdLibraryDaoException;
    
    /**
     * Loads the DVD library from file system into memory for the user to
     * interact with. The library is loaded one time when the program runs. 
     */
    void loadLibrary() throws DvdLibraryDaoException;
    
    /**
     * Finds all DVDs released in the last N years 
     */
    List<Dvd> releasedInLastXYears(int year);
    
    /**
     * Finds all DVDs with a given MPAA rating
     */
    List<Dvd> searchByMpaaRating(String rating);
    
    /**
     * Finds all movies from a single director
     */
    List<Dvd> searchByDirector(String director);
    
    /**
     * Finds all movies by studio
     */
    List<Dvd> searchByStudio(String studio);

    /**
     * Averages all the years of the movies released in the collection
     */
    double avgMovieAge();
    
    /**
     * Finds the newest movie in the DVD collection
     */
    Dvd searchNewestMovie();
    
    /**
     * Finds the oldest movie in the DVD collection
     */
    Dvd searchOldestMovie();
    
    /**
     * Find the average note length of all the DVDs in the collection
     */
    double findAvgNoteLength();
}