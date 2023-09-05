import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

class candidate{
    String name;
    int roll_no;
    String email;
    String password;
    int obtained_marks=0;
    int correct = 0;
    int Incorrect = 0;
    
     
    void create_user(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Welcome to STUDENT REGISTRATION portal---------");
        System.out.println("Enter your name : ");
        this.name= sc.nextLine();
        System.out.println("Enter your Roll_no :");
        this.roll_no= sc.nextInt();
        System.out.println("Set Password:");
        this.password= sc.next();
        System.out.println();
        System.out.println("  REGISTRATION SUCCESSFULL........ kindly login ....");

    }

     void update_profile(candidate ab){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------WELCOME to Update Profile Interface-----------");
        System.out.println("The following things can be updated\n 1) Name \n 2) Roll_no. \n 3) Password \n 4) Email");
        int ch = sc.nextInt();
        switch(ch){
            case 1: {
                System.out.println("Enter new name : ");
                ab.name= sc.nextLine();
                break;
            }
            case 2:{
                System.out.println(" Enter new  Roll_no :");
                ab.roll_no= sc.nextInt();
                break;
            }
            case 3 : {
                System.out.println("Enter new Password : ");
                ab.password= sc.next();
                break;
            }
        }
     }
}
class portal{
    void login(candidate[] user, int number_students){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Test Portal ");
        System.out.println(" Enter your Roll_no");
        int roll_no= sc.nextInt();
        System.out.println("Enter your Password ");
        String password= sc.next();
        for (int i = 0; i <number_students; i++) {
            if((user[i].roll_no==roll_no) &&(user[i].password.equals(password)) ){
                System.out.println("logged successfully");
                System.out.println("Enter any key to begin test");
                sc.nextLine();
                System.out.flush();
                run_test(user[i]);
            }else{
                System.out.println("Credential not matched or candidate yet not registered.");
            }
        }
    }
    void run_test(candidate user){
        String filename = "question.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            Date startTime = new Date(0);
            Date endTime = new Date(startTime.getTime()+60*1000);
            System.out.println("The Test started at" + startTime+"\n The Test ended at"+ endTime);



            Scanner sc = new Scanner(System.in);
            while((( line = br .readLine())!=null)){
                if(new Date(0).before(endTime)){
                    System.out.println(line);
                   //System.out.println("Ans:");
                    String input = sc.nextLine();
                    System.out.println("Ans:");
                    String ans_fileName="answer_user_"+ user.roll_no+".txt";
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(ans_fileName,true))) {
                       bw.write(input);
                       bw.newLine(); 
                    } catch(IOException e) {
                        // TODO: handle exception
                        System.err.format("Exception %s%n"); 
                    }
                }else{
                    System.out.println("TIME OUT");
                    break;
                }
            }
            System.out.println(" Test completed for the candidate with following details - \n name"+user.name + " roll_no"+user.roll_no );
            System.out.println("Press any key to continue!");
            sc.nextLine();
        }catch(IOException e){
            System.err.format("Exception");
        }
          System.out.flush();
            evaluate_marks(user);
    }
    void evaluate_marks(candidate user){
        String fileName= "answer_user_"+ user.roll_no+ ".txt";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName));
            BufferedReader br2 = new BufferedReader(new FileReader("answers.txt"))){
                String answer_line , user_answer;
                while((answer_line= br2.readLine())!=null && (user_answer = br.readLine())!=null){
                    if(user_answer.equalsIgnoreCase(answer_line)){
                        user.obtained_marks+=1;
                        user.correct++;
                    }else{
                        user.Incorrect++;
                    }
                }

            } catch(IOException e){
             System.err.format("exception");
            }
            System.out.println("MARKS OBTAINED: "+ user.obtained_marks);
            System.out.println("CORRECT RESPONSES : "+ user.correct);
            System.out.println(" INCORRECT RESPONSES: "+ user.Incorrect);
            System.out.println("Press any key to continue");
            System.out.println(" Logging  out");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
    }
}


public class exam{			
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter the number of candidate you want to register: ");
        int n = sc.nextInt();
        candidate[] ob = new candidate[n];
        for (int i = 0; i <n; i++) {
            System.out.flush();
            ob[i]= new candidate();
            ob[i].create_user();
        }
        System.out.flush();
        System.out.println("Do you want to update details of any user: Y/N ");
        char c = sc.next().charAt(0);
        if((c=='Y')||(c=='y')){
            System.out.println("Enter roll_no of the candidate whose information is to be updated:");
            int roll_no= sc.nextInt();
            for (int i = 0; i < n; i++) {
                if(ob[i].roll_no==roll_no){
                    ob[i].update_profile(ob[i]);
                }else{
                    System.out.println("Credential not matched or candidate not yet registered");
                }
            }
        }
        System.out.flush();
        portal ob2= new portal();
        for(; ;){
            ob2.login(ob, n);
            System.out.flush();
            System.out.println("Do you want to continue test portal Y/N? ");
            char ch = sc.next().charAt(0);
            if(!((ch=='y'||ch=='Y'))){
                break;
            }
        } System.out.println("The marks obtained by all candidate is ");
        for (int i = 0; i < n; i++) {
            System.out.println("Candidate roll_no"+ ob[i].roll_no+ " Candidate name"+ ob[i].name+ "Obtained marks"+ ob[i].obtained_marks);
        }   
    }
}
