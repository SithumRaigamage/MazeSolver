//Name -Sithum Lisitha Raigamage
//IIT student No -20222473
//UOW student No -w1999685

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to MazeSolver ");
        Scanner input=new Scanner(System.in);
        System.out.println("Choose of the one options below :");
        System.out.println(" ");
        System.out.println("1. Run Specification File");
        System.out.println("2. Run Example Tests");
        System.out.println("3. Run BenchMark Tests");
        System.out.println("4. Run My TestFiles");
        System.out.println("5. Exit");
        System.out.println(" ");

        System.out.print("Enter Option :");
        int option=input.nextInt();
        switch (option){
            case 1:
                //run specification example
                RunSpecificationExample();
                break;
            case 2:
                //run examples folder
                ExampleRun();
                break;
            case 3:
                //run benchmark test
                RunBenchmarkTest();
                break;
            case 4:
                //run custom test
                RunCustomTest();
                break;
            case 5:
                System.out.println("Exiting..");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a number between 1 and 4.");
        }
    }

    public static void RunSpecificationExample(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the file name for (e.g., maze1.txt): ");
        String filename = input.nextLine();
        // Construct full path for each file
        try {
            char[][] mazePath = Parser.parseFile(filename);
            MazeSolver solver = new MazeSolver(mazePath);
            System.out.println(" ");
            System.out.println("Breadth-First Search:");
            if (!solver.BFSMethod()) {
                System.out.println("No solution could be found.");
            }
            System.out.println("Time taken: " + solver.getTimeTaken() + " nanoseconds");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }

    public static void ExampleRun(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the file name in the example directory : ");
        String filename = input.nextLine();
        String fullPath ="examples/"+filename ;
         // Construct full path for each file
        try {
            char[][] mazePath = Parser.parseFile(fullPath);
            MazeSolver solver = new MazeSolver(mazePath);

            System.out.println("Breadth-First Search for file: " + fullPath);
            if (!solver.BFSMethod()) {
                System.out.println("No solution could be found for " + fullPath);
            }
            System.out.println("Time taken: " + solver.getTimeTaken() + " nanoseconds");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found for " + fullPath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error processing file " + fullPath + ": " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(); // Add a newline for better separation between results

    }

    public static void RunBenchmarkTest(){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the file name in the benchmark directory: ");
        String filename = input.nextLine();
        String fullPath ="benchmark_series/"+filename;
        // Construct full path for each file
        try {
            char[][] mazePath = Parser.parseFile(fullPath);
            MazeSolver solver = new MazeSolver(mazePath);
            System.out.println("Breadth-First Search for file: " + fullPath);
            if (!solver.BFSMethod()) {
                System.out.println("No solution could be found for " + fullPath);
            }
            System.out.println("Time taken: " + solver.getTimeTaken() + " nanoseconds");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found for " + fullPath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error processing file " + fullPath + ": " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(); // Add a newline for better separation between results

    }

    public static void RunCustomTest(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the file name in myTest directory: ");
        String filename = input.nextLine();
        String fullPath ="MyTest/"+filename;
        // Construct full path for each file
        try {
            char[][] mazePath = Parser.parseFile(fullPath);
            MazeSolver solver = new MazeSolver(mazePath);
            System.out.println("Breadth-First Search for file: " + fullPath);
            if (!solver.BFSMethod()) {
                System.out.println("No solution could be found for " + fullPath);
            }
            System.out.println("Time taken: " + solver.getTimeTaken() + " nanoseconds");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found for " + fullPath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error processing file " + fullPath + ": " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(); // Add a newline for better separation between results
    }


}
