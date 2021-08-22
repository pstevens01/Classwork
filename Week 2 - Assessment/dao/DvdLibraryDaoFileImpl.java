/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    // DvdLibraryDaoFileImpl implements the DvdLibraryDao interface. All methods
    // declared in the DvdLibrary Dao interface are implemented here.
    
    // DVD library collection using HashMap key, value properties
    private Map<String, Dvd> dvds = new HashMap<>();
    
    public static final String DVD_LIBRARY = "library.txt";
    public static final String DELIMITER = "::";

    // Adds the DVD to the DVD library HashMap and returns that obj
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        Dvd prevDvd = dvds.put(title, dvd);
        return prevDvd;
    }

    // Returns all elements present in the DVD library HashMap
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        return new ArrayList<Dvd>(dvds.values());
    }

    // Returns from the DVD Hashmap the DVD with the specified title
    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        return dvds.get(title);
    }
    
    // Removes DVD from the DVD library HashMap with the specified title and returns it.
    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        Dvd removedDvd = dvds.remove(title);
        return removedDvd;
    }
    
    // Takes a line of text read from the loadLibrary method and creates a DVD object
    // using the info that was extracted from the 'dvdAsText' param which was split
    // using a specified delimiter.
    private Dvd unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        Dvd dvdFromFile = new Dvd(title);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setNotes(dvdTokens[5]);
        return dvdFromFile;
    }
    
    // Attempts to create a new file reader obj to read lines from the DVD library text
    // file. Each line is then passed to the unmarshallDvd method and the returned obj
    // is then put into the DVD library HashMap.
    public void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("Could not load library into memory", e);
        }
        
        String currentLine;
        Dvd currentDvd;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }
    
    // Accepts a DVD obj from the saveLibrary method and returns a string of text using
    // each properties of the obj. The specified delimiter is added between each property
    // so that the unmarshallDvd method can properly read each property when loading from
    // the library text file.
    private String marshallDvd(Dvd aDvd) {
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getNotes();
        return dvdAsText;
    }
    
    // Attempts to create a new file writer obj to write lines to the DVD library text
    // file. Each string returned from the marshallDvd method is written to a new line
    // so each record occupies only one line.
    public void saveLibrary() throws DvdLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save student data.", e);
        }
        
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
