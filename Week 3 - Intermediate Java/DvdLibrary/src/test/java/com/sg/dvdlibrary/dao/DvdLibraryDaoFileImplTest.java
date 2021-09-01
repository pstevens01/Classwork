/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul
 */
public class DvdLibraryDaoFileImplTest {
    
    DvdLibraryDao testDao;
    
    public DvdLibraryDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testDvdLibrary.txt";
        // Write blank file
        new FileWriter(testFile);
        testDao = new DvdLibraryDaoFileImpl(testFile);
    }
    
    @Test
    public void testAddGetDvd() throws Exception {
        // Create a DVD
        String title = "Forrest Gump";
        Dvd dvd = new Dvd(title);
        dvd.setReleaseDate(LocalDate.parse("01-01-1994", DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        dvd.setRating("PG-13");
        dvd.setDirectorName("Robert Zemeckis");
        dvd.setStudio("The Tisch Company");
        dvd.setNotes("Best movie that I've seen.");
        
        // Add dvd to the DAO
        testDao.addDvd(title, dvd);
        
        // Get DVD
        Dvd retrievedDvd = testDao.getDvd(title);
        
        // Check if objects are equal
        assertEquals(dvd.getTitle(), retrievedDvd.getTitle(),
                "Checking titles match.");
        assertEquals(dvd.getReleaseDate(), retrievedDvd.getReleaseDate(),
                "Checking dates match.");
        assertEquals(dvd.getRating(), retrievedDvd.getRating(),
                "Checking ratings match.");
        assertEquals(dvd.getDirectorName(), retrievedDvd.getDirectorName(),
                "Checking directors match.");
        assertEquals(dvd.getStudio(), retrievedDvd.getStudio(),
                "Checking studios match.");
        assertEquals(dvd.getNotes(), retrievedDvd.getNotes(),
                "Checking notes match.");
    }
    
    @Test
    public void testGetAllDvds() throws Exception {
        // Create a DVD
        String firstTitle = "Forrest Gump";
        Dvd firstDvd = new Dvd(firstTitle);
        firstDvd.setReleaseDate(LocalDate.parse("01-01-1994", DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        firstDvd.setRating("PG-13");
        firstDvd.setDirectorName("Robert Zemeckis");
        firstDvd.setStudio("The Tisch Company");
        firstDvd.setNotes("Best movie that I've seen.");
        
        // Create a 2nd DVD
        String secondTitle = "Saving Private Ryan";
        Dvd secondDvd = new Dvd(secondTitle);
        firstDvd.setReleaseDate(LocalDate.parse("01-01-1998", DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        firstDvd.setRating("R");
        firstDvd.setDirectorName("Steven Spielberg");
        firstDvd.setStudio("Amblin Entertainment");
        firstDvd.setNotes("Very good movie. Not appriopriate for children.");
        
        // Add both DVDs to DAO
        testDao.addDvd(firstDvd.getTitle(), firstDvd);
        testDao.addDvd(secondDvd.getTitle(), secondDvd);
        
        // Retrieve list
        List<Dvd> allDvds = testDao.getAllDvds();
        
        // Check the list
        assertNotNull(allDvds, "The list should not be null");
        assertEquals(2, allDvds.size(), "The list should contain 2 DVDs");
        
        // Check for each DVD
        assertTrue(allDvds.contains(firstDvd), "The list should contain Forrest Gump");
        assertTrue(allDvds.contains(secondDvd), "The list should contain Saving Private Ryan");
    }
    
    @Test
    public void testRemoveDvd() throws Exception {
        // Create a DVD
        String firstTitle = "Forrest Gump";
        Dvd firstDvd = new Dvd(firstTitle);
        firstDvd.setReleaseDate(LocalDate.parse("01-01-1994", DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        firstDvd.setRating("PG-13");
        firstDvd.setDirectorName("Robert Zemeckis");
        firstDvd.setStudio("The Tisch Company");
        firstDvd.setNotes("Best movie that I've seen.");
        
        // Create a 2nd DVD
        String secondTitle = "Saving Private Ryan";
        Dvd secondDvd = new Dvd(secondTitle);
        firstDvd.setReleaseDate(LocalDate.parse("01-01-1998", DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        firstDvd.setRating("R");
        firstDvd.setDirectorName("Steven Spielberg");
        firstDvd.setStudio("Amblin Entertainment");
        firstDvd.setNotes("Very good movie. Not appriopriate for children.");
        
        // Add DVDs to DAO
        testDao.addDvd(firstDvd.getTitle(), firstDvd);
        testDao.addDvd(secondDvd.getTitle(), secondDvd);
        
        // Remove the first DVD
        Dvd removedDvd = testDao.removeDvd(firstDvd.getTitle());
        
        // Check if the first DVD was removed
        assertEquals(removedDvd , firstDvd, "The removed DVD should be Forrest Gump");
        
        // Get DVD list
        List<Dvd> allDvds = testDao.getAllDvds();
        
        // Check the list
        assertNotNull(allDvds, "Dvd list should not be null");
        assertEquals(1, allDvds.size(), "The list should only have 1 DVD");
        
        // Check list only contains second DVD and not the first DVD
        assertFalse(allDvds.contains(firstDvd), "First DVD should not be in the list");
        assertTrue(allDvds.contains(secondDvd), "Second DVD should be in the list");
        
        // Remove the second DVD
        removedDvd = testDao.removeDvd(secondDvd.getTitle());
        
        // Check if the second DVD was removed
        assertEquals(removedDvd, secondDvd, "The removed DVD should be Saving Private Ryan");
        
        // Get the DVD list
        allDvds = testDao.getAllDvds();
        
        // Check the list
        assertTrue(allDvds.isEmpty(), "The list should be empty now");
        
        // Try to retrieve both DVDs from the list - they shouldn't exist
        Dvd retrievedDvd = testDao.getDvd(firstDvd.getTitle());
        assertNull(retrievedDvd, "Forrest Gump was removed, should be null");
        
        retrievedDvd = testDao.getDvd(secondDvd.getTitle());
        assertNull(retrievedDvd, "Saving Private Ryan was removed, should be null");
    }
    
}
