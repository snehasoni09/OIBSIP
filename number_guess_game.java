 import java.util.Random;
import java.util.Scanner;

public class number_guess_game {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String play = "yes";

        //while loop to determine if we are going to play the game
        while(play.equals("yes")){
             Random rand = new Random(0);
             int randnum = rand.nextInt(100);
             int guess = -1;
             int tries = 0;
             // while loop to check if the game is over
            while(guess!=randnum){
                System.out.println("Guess a number between 1 and 100: ");
                guess = reader.nextInt();
                tries++;
                if(guess==randnum){
                    System.out.println(" Awesome! You guessed the number!");
                    System.out.println("it took you "+ tries+ " guesses!");
                    System.out.println("would you like to play again? Yes or No:");
                    play= reader.next().toLowerCase();

                }
                else if(guess>randnum){
                    System.out.println("Your guess is too high, try again.");

                }
                else{
                    System.out.println("Your guess is too low, try again.");
                }
            } 
             
        }
       reader.close(); 
    }
}