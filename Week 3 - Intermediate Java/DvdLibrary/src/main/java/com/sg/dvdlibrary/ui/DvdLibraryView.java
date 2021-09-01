/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Paul
 */
public class DvdLibraryView {
    private UserIO io;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    // Constructor creating a View obj using a UserIO obj. This is an example of composition.
    public DvdLibraryView(UserIO io) { 
        this.io = io;
    }
    
    // Display menu options for user and returns an integer of the selected menu option.
    public int printMenuAndGetSelection() {
        io.print("***** Main Menu *****");
        io.print(" 1. Add DVD to collection");
        io.print(" 2. Remove DVD from collection");
        io.print(" 3. Edit DVD information in collection");
        io.print(" 4. List all DVDs in the collection");
        io.print(" 5. Display information about a specific DVD");
        io.print(" 6. Search collection for DVDs using keywords");
        io.print(" 7. Exit");
        io.print("=========================================================");
        io.print("***** DVD Collection Analytics *****");
        io.print(" 8. Display movies made within the last X number of years");
        io.print(" 9. Display movies with a specific MPAA rating (G, PG, PG-13, R, NC-17)");
        io.print("10. Display movies directed by a specific director");
        io.print("11. Display movies producted by a specific studio");
        io.print("12. Display the average age of all the movies in the collection");
        io.print("13. Display the newest movie in the DVD collection");
        io.print("14. Display the oldest movie in the DVD collection");
        io.print("15. Dipslay the average note length of all the movies in the collection");
        
        return io.readInt("Please select from the above choices", 1, 15);
    }
    
    // User prompt to enter new DVD info to add to the library.
    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter the DVD title:");
        String releaseDate = io.readString("Please enter the DVD release date (MM-dd-yyyy):");
        String rating = io.readString("Please enter the MPAA rating (G, PG, PG-13, R, NC-17):");
        String directorName = io.readString("Please enter the director's name:");
        String studio = io.readString("Please enter the studio that produced the movie:");
        String notes = io.readString("Please enter your rating of the DVD or any additional notes:");
        
        // Convert releaseDate to LocalDate obj
        LocalDate release = LocalDate.parse(releaseDate);
        
        Dvd currentDvd = new Dvd(title); // Creates DVD obj with user-specified info.
        currentDvd.setReleaseDate(release);
        currentDvd.setRating(rating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setNotes(notes);
        return currentDvd; // Returns new DVD object to the controller.
    }
    
    // Accepts a list of DVD objs and then displays all information for each DVD in the list
    public void displayDvdLibrary(List<Dvd> dvdLibrary) {
        for (Dvd currentDvd : dvdLibrary) {
            io.print("Title: " + currentDvd.getTitle());
            io.print("Release Date: " + currentDvd.getReleaseDate().format(format));
            io.print("MPAA Rating: " + currentDvd.getRating());
            io.print("Director's Name: " + currentDvd.getDirectorName());
            io.print("Producing Studio: " + currentDvd.getStudio());
            io.print("User Notes: " + currentDvd.getNotes());
            io.print("");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Display information from a user-selected DVD. Prints information if DVD obj isn't null
    // otherwise it displays that no such DVD exists.
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate().toString()); 
            io.print(dvd.getRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getNotes());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Display information about any DVD in the DVD list that has a user-specified keyword in the
    // title. For example, if the user enters the keyword 'home' it will display all DVDs in the
    // library that has the word 'home' anywhere in the title.
    public void displayDvdKeywordList(String keyword, List<Dvd> dvdLibrary) {
        for (Dvd currentDvd : dvdLibrary) {
            if (currentDvd.getTitle().toLowerCase().contains(keyword.toLowerCase())) 
                io.print("Title: " + currentDvd.getTitle());
                io.print("Release Date: " + currentDvd.getReleaseDate().format(format));
                io.print("MPAA Rating: " + currentDvd.getRating());
                io.print("Director's Name: " + currentDvd.getDirectorName());
                io.print("Producing Studio: " + currentDvd.getStudio());
                io.print("User Notes: " + currentDvd.getNotes());
                io.print("");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Display add DVD banner
    public void displayCreateDvdBanner() {
        io.print("*** Add a DVD to the library ***");
    }
    
    // Display add DVD results banner
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully added to the library.  Please hit enter to continue");
    }
     
    // Display whole DVD library banner
    public void displayDisplayAllDvdBanner() {
        io.print("*** Dispaying all DVDs in the library ***");
    }
    
    // Display specific DVD banner
    public void displayDisplayDvdBanner() {
        io.print("*** Display DVD ***");
    }
    
    // Display user-specified title banner
    public String getDvdChoice() {
        return io.readString("Please enter the title of the DVD.");
    }
    
    // Display user-specified title keyword prompt banner
    public String getDvdTitleKeyword() {
        return io.readString("Please enter a DVD title keyword to search for.");
    }
    
    // Display remove DVD results banner
    public void displayRemoveDvdResults(Dvd dvd) {
        if (dvd != null) {
            io.print("DVD removed successfully.");
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Display edit DVD results banner
    public void displayEditResults(Dvd dvd) {
        if (dvd != null) {
            io.print("Dvd edited successfully.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
        
    // Display edit DVD banner
    public void displayDisplayEditDvdBanner() {
        io.print("*** Edit DVD ***");
    }
    
    // Display remove DVD banner
    public void displayDisplayRemoveDvdBanner() {
        io.print(("*** Remove DVD ***"));
    }
    
    // Display search DVD keyword banner
    public void displayDisplaySearchKeywordBanner() {
        io.print("*** Search DVDs using title keyword ***"); 
    }
    
    // Display search DVD keyword results banner
    public void displayDisplaySearchKeywordResult(String title) {
        io.print("\nAll DVDs with the keyword \'" + title + "\' in the title");
    }
    
    // Display exit banner
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    // Display unknown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    // Display error message banner
    public void displayErrorMessage(String errorMsg) {
        io.print("*** Error ***");
        io.print(errorMsg);
    }
    
    // Display loading DVD library from file into memory banner
    public void displayLoadBanner() {
        io.print("*** Loading DVD Library ***");
    }
    
    // Display saving DVD library from memory to file banner
    public void displaySaveBanner() {
        io.print("*** Saving DVD Library ***"); 
    }
    
    public void displaySearchInLastNYearsBanner(int years) {
        io.print("*** Searching for DVDs released in the last " + years + " years ***");
    }
    
    public int getNYears() {
        io.print("Displaying movies made in the last X years.");
        return io.readInt("How many years back would you like to search?");
    }
    
    public String getMpaaRating() {
        return io.readString("Which MPAA rating would you like to search for (G, PG, PG-13, R, NC-17)?");
    }
    
    public String getDirectorName() {
        return io.readString("Which director's movies would you like to display?");
    }
    
    public String getProductionStudioName() {
        return io.readString("Which production studio's movies would you like to display?");
    }
    
    public void displayAvgAge(double avgInDays) {
        io.print("\nThe average age of DVDs in the collection is: " + avgInDays + " days\n");
        io.readString("Press hit enter to continue.");
    }
    
    public void displayAvgNoteLength(double avgNoteLength) {
        io.print("\nThe average length of notes is " + Math.floor(100 * avgNoteLength) / 100 + " characters\n");
        io.print("Please hit enter to continue.");
    }
}
