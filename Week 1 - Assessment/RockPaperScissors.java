/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.week1assessment;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class RockPaperScissors {
    /**
     * @param args the command line arguments
     */
    
    static Scanner in = new Scanner(System.in);
   
    public static void main(String[] args) {
        int wins = 0;
        int losses = 0; 
        int ties = 0;
        int playerChoice, computerChoice;
        String choice;
        boolean keepPlaying = true;
        
        Random rng = new Random();
        
        while (keepPlaying == true) {   // Continue playing until player chooses to stop
            int gameRounds = setGameRounds();

            for (int i = 1; i <= gameRounds; i++) { // Loop from first round until final round

                playerChoice = getPlayerChoice(i);
                computerChoice = rng.nextInt(3) + 1; // Generate random computer selection of rock, paper, scissors

                switch (playerChoice) {
                case 1: // Player chose rock
                    if (computerChoice == 2) {
                        System.out.println("You chose rock and the computer chose paper. You lose.\n");
                        losses++;
                    } else if (computerChoice == 3) {
                        System.out.println("You chose rock and the computer chose scissors. You win!\n");
                        wins++;
                    } else {
                        System.out.println("You both chose rock. It's a tie.\n");
                        ties++;
                    }
                    break;
                case 2: // Player chose paper
                    if (computerChoice == 1) {
                        System.out.println("You chose paper and the computer chose rock. You win!\n");
                        wins++;
                    } else if (computerChoice == 3) {
                        System.out.println("You chose paper and the computer chose scissors. You lose.\n");
                        losses++;
                    } else {
                        System.out.println("You both chose paper. It's a tie.\n");
                        ties++;
                    }
                    break;
                case 3: // Player chose scissors
                    if (computerChoice == 1) {
                        System.out.println("You chose scissors and the computer chose rock. You lose.\n");
                        losses++;
                    } else if (computerChoice == 2) {
                        System.out.println("You chose scissors and the computer chose paper. You win!\n");
                        wins++;
                    } else {
                        System.out.println("You both chose scissors. It's a tie.\n");
                        ties++;
                    }
                    break;
                }
            }
            
            gameResults(gameRounds, wins, losses, ties);
            
            // Reset win/loss/draw counters
            wins = 0;
            losses = 0;
            ties = 0;
            // Prompt player if they would like to play again
            System.out.println("Would you like to play the game again? Yes or No.");
            choice = in.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                keepPlaying = true;
            } else if (choice.equalsIgnoreCase("no")) {
                keepPlaying = false;
                System.out.println("Thank you for playing.");
            } else {
                System.out.println("Invalid selection. The game will now exit.");
            }
        }    
    }
    
    // Sets the number of rounds to be played
    public static int setGameRounds() {
        System.out.println("Please enter the number of rounds you would like to play (1-10):");
        int rounds = Integer.parseInt(in.nextLine());
        // Validate round choice
        if (rounds < 1 || rounds > 10) {
            System.out.println("Error! You entered an invalid number. The game will now exit.");
            System.exit(0);
        }
        return rounds;
    }
    
    // Promps the player to make a selection of rock, paper, or scissors
    public static int getPlayerChoice(int i) {
        int selection;
        System.out.println("-*-*-Round " + i + "-*-*-");
        System.out.println("Please select one of the following:\n1. Rock\n2. Paper\n3. Scissors");
        selection = Integer.parseInt(in.nextLine());
        // Validate player choice
        while (selection < 1 || selection > 3) {
            System.out.println("Invalid selection. Please select again:");
            selection = Integer.parseInt(in.nextLine());
        }
        return selection;
    }
    
    // Prints to console the game results from all rounds
    public static void gameResults(int rounds, int win, int loss, int tie) {
        System.out.println("\nThe total number of rounds that were played: " + rounds);
        System.out.println("There were " + win + " rounds won by the player.");
        System.out.println("There were " + loss + " rounds won by the computer.");
        System.out.println("And there were " + tie + " ties.");
        
        if (win > loss) {
            System.out.println("Based on the tabulated results, the player has won the game of Rock, Paper, Scissors.\n");
        } else if (loss > win) {
            System.out.println("The computer has more wins and has won the game of Rock, Paper, Scissors.\n");
        } else if (win == loss) {
            System.out.println("Unfortunately no winner can be declared because both participants have the same amount of wins.\n");
        }
    }
}
