/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.collections;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Paul
 */
public class StateCapitals {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<String, String> statesAndCapitals = new HashMap<>();
        String[][] arr = {{"Alabama", "Montgomery"}, {"Alaska", "Juneau"}, {"Arizona", "Phoenix"},
			{"Arkansas", "Little Rock"}, {"California", "Sacramento"},
			{"Colorado", "Denver"}, {"Connecticut", "Hartford"}, 
			{"Delaware", "Dover"}, {"Florida", "Tallahassee"}, 
			{"Georgia", "Atlanta"},{"Hawaii", "Honolulu"}, {"Idaho", "Boise"},
			{"Illinois", "Springfield"}, {"Indiana", "Indianapolis"},
			{"Iowa Des", "Moines"}, {"Kansas", "Topeka"}, {"Kentucky","Frankfort"}, 
			{"Louisiana", "Baton Rouge"}, {"Maine", "Augusta"}, 
			{"Maryland", "Annapolis"}, {"Massachusetts", "Boston"}, 
			{"Michigan", "Lansing"}, {"Minnesota", "Saint Paul"}, 
			{"Mississippi", "Jackson"}, {"Missouri", "Jefferson City"}, 
			{"Montana", "Helena"}, {"Nebraska", "Lincoln"}, 
			{"Nevada", "Carson City"}, {"New Hampshire", "Concord"}, 
			{"New Jersey", "Trenton"}, {"New Mexico", "Santa Fe"}, 
			{"New York", "Albany"}, {"North Carolina", "Raleigh"}, 
			{"North Dakota", "Bismarck"},{"Ohio", "Columbus"},
			{"Oklahoma", "Oklahoma City"}, {"Oregon", "Salem"}, 
			{"Pennsylvania", "Harrisburg"}, {"Rhode Island", "Providence"}, 
			{"South Carolina", "Columbia"}, {"South Dakota", "Pierre"}, 
			{"Tennessee", "Nashville"}, {"Texas", "Austin"}, 
			{"Utah", "Salt Lake City"}, {"Vermont", "Montpelier"}, 
			{"Virginia", "Richmond"}, {"Washington", "Olympia"}, 
			{"West Virginia", "Charleston"}, {"Wisconsin", "Madison"}, 
			{"Wyoming", "Cheyenne"}};
        for (int i = 0; i < arr.length; i++) {
            statesAndCapitals.put(arr[i][0], arr[i][1]);
        }
        System.out.println("STATES:");
        System.out.println("==========");
        for (String k : statesAndCapitals.keySet()) {
            System.out.println(k);
        }
        System.out.println("\nCAPITALS");
        System.out.println("==========");
        for (String j : statesAndCapitals.values()) {
            System.out.println(j);
        }
        System.out.println("\nSTATE/CAPITAL PAIRS");
        System.out.println("==========");
        for (String m : statesAndCapitals.keySet()) {
            System.out.println(m + " - " + statesAndCapitals.get(m));
        }
    }
    
}
