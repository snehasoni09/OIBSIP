

import java.util.Scanner;

public class online {
    String name;
    String email;

    public static boolean[] seats = new boolean[10];// initialisation an array of 10 seats, all empty
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            // display menu
            System.out.println("\n Please select an option : ");
            System.out.println("1. View Seat Map");
            System.out.println("2. Reserve Seat");
            System.out.println("3. Cancel Reservation ");
            System.out.println("4. Exit");

            //get user input
            int option = sc.nextInt();
            switch (option) {
                case 1:
                      viewSetMap();
                    break;
                case 2:
                       reserveSeat();
                       break;
                case 3:
                       cancelReservation();
                       break;
                case 4:
                      System.exit(0);// exit the program        
                default:
                     System.out.println("Invalid Option!");
            }
        }
    }
    public static void viewSetMap() {
        System.out.println("\n Current Seat Map: ");
        for (int i = 0; i < seats.length; i++) {
            if(seats[i]){
             System.out.println("X");// print an X if seat is reserved 
            } else{
                System.out.println((i+1) + " ");// print seat no if it is empty
            }
        }
        System.out.println();
    }

    public static void reserveSeat(){
       Scanner sc = new Scanner(System.in);
       System.out.println(" Enter passenger name:");
       String name= sc.nextLine();
       System.out.println("Enter valid email: ");
       String email= sc.nextLine();
       System.out.println("Enter Train number:");
       int trainNo= sc.nextInt();

       System.out.println(" Enter seat number (1-10):");
       System.out.println();
       int seatNumber = sc.nextInt();
       if(seatNumber < 1 || seatNumber>10){
        System.out.println("Invalid seat number!");
       }else if( seats[seatNumber -1]){
        System.out.println(" Seat already reserved!");
       }else{
        seats[seatNumber -1]=true;// reserve the seat
        System.out.println(" Seat reserved!");
       }
    }
    
    public static  void cancelReservation(){
        Scanner sc = new Scanner(System.in);
        int seatNumber = sc.nextInt();
        if( seatNumber < 1 || seatNumber>10){
            System.out.println("Invalid seat number!");
        }else if(!seats[seatNumber -1]){
            System.out.println("Seats not reserved!");
        }else{
           seats[seatNumber -1]=false;
           System.out.println("Reservation cancelled!");
        }
    } 
}
