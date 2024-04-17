import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DIRECTORY = "examples/";  // Constant for the directory path
    private static String DIRECTORYTwo="benchmark_series/";

    public static void main(String[] args) {

        System.out.println("Welcome to MazeSolver ");
        Scanner input=new Scanner(System.in);
        System.out.println("Choose of the one options below :");
        System.out.println(" ");
        System.out.println("1. Run Specification File");
        System.out.println("2. Run Example Tests");
        System.out.println("3. Run BenchMark Tests");
        System.out.println("4. Exit");
        System.out.println(" ");
        //RunSpecificationExample();
        //ExampleRun();
        //RunBenchmarkTest();
    }

























    public static void RunSpecificationExample(){
        try {
            String filename = "maze1.txt"; // Update with the correct path
            char[][] maze = Parser.parseFile(filename);
            MazeSolver solver = new MazeSolver(maze);
            System.out.println(" ");
            System.out.println("Breadth-First Search:");
            if (!solver.solveBFS()) {
                System.out.println("No solution could be found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }

    public static void ExampleRun(){

        List<String> filenames = List.of("maze10_1.txt", "maze10_2.txt", "maze10_3.txt","maze10_4.txt"
                ,"maze10_5.txt","maze15_1.txt","maze15_2.txt","maze15_3.txt","maze15_4.txt","maze10_5.txt",
                "maze20_1.txt","maze20_2.txt","maze20_3.txt","maze20_4.txt","maze20_5.txt","maze25_1.txt",
                "maze25_2.txt","maze25_3.txt","maze25_4.txt","maze25_5.txt","maze30_1.txt","maze30_2.txt",
                "maze30_3.txt","maze30_4.txt","maze30_5.txt"); // List of filenames without directory

        for (String filename : filenames) {
            String fullPath = buildFilePathTwo(filename); // Construct full path for each file
            try {
                char[][] maze = Parser.parseFile(fullPath);
                MazeSolver solver = new MazeSolver(maze);
                System.out.println("Breadth-First Search for file: " + fullPath);
                if (!solver.solveBFS()) {
                    System.out.println("No solution could be found for " + fullPath);
                }
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



    public static void RunBenchmarkTest(){
        List<String> filenames=List.of("puzzle_10.txt","puzzle_20.txt","puzzle_40.txt","puzzle_80.txt","puzzle_160.txt",
                "puzzle_320.txt","puzzle_1280.txt","puzzle_2560.txt");
        for (String filename : filenames) {
            String fullPath = buildFilePathTwo(filename); // Construct full path for each file
            try {
                char[][] maze = Parser.parseFile(fullPath);
                MazeSolver solver = new MazeSolver(maze);
                System.out.println("Breadth-First Search for file: " + fullPath);
                if (!solver.solveBFS()) {
                    System.out.println("No solution could be found for " + fullPath);
                }
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

    // Helper method to construct the full file path
    private static String buildFilePath(String filename) {
        return DIRECTORY + filename;
    }

    private static String buildFilePathTwo(String filename) {
        return DIRECTORYTwo +filename;
    }
}
