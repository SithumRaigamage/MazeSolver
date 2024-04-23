import java.io.FileNotFoundException;
import java.util.List;
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
                RunSpecificationExample();
                break;
            case 2:
                ExampleRun();
                break;
            case 3:
                RunBenchmarkTest();
                break;
            case 4:
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
        try {
            char[][] maze = Parser.parseFile(filename);
            MazeSolver solver = new MazeSolver(maze);
            System.out.println(" ");

            System.out.println("Breadth-First Search:");
            if (!solver.BFS()) {
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
        System.out.print("Enter the file name : ");
        String filename = input.nextLine();
        String fullPath ="examples/"+filename ;
         // Construct full path for each file
        try {
            char[][] maze = Parser.parseFile(fullPath);
            MazeSolver solver = new MazeSolver(maze);

            System.out.println("Breadth-First Search for file: " + fullPath);
            if (!solver.BFS()) {
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
        System.out.print("Enter the file name : ");
        String filename = input.nextLine();
        String fullPath ="benchmark_series/"+filename;  // Construct full path for each file
        try {
            char[][] maze = Parser.parseFile(fullPath);
            MazeSolver solver = new MazeSolver(maze);
            System.out.println("Breadth-First Search for file: " + fullPath);
            if (!solver.BFS()) {
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
        System.out.print("Enter the file name : ");
        String filename = input.nextLine();
        String fullPath ="MyTest/"+filename;  // Construct full path for each file
        try {
            char[][] maze = Parser.parseFile(fullPath);
            MazeSolver solver = new MazeSolver(maze);
            System.out.println("Breadth-First Search for file: " + fullPath);
            if (!solver.BFS()) {
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
