import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DIRECTORY = "examples/";
    private static final String DIRECTORYTwo = "benchmark_series/";
    private static final String DIRECTORYThree = "MyTest/";

    public static void main(String[] args) {
        System.out.println("Welcome to MazeSolver ");
        Scanner input = new Scanner(System.in);
        System.out.println("Choose one of the options below:");
        System.out.println("1. Run Specification File");
        System.out.println("2. Run Example Tests");
        System.out.println("3. Run Benchmark Tests");
        System.out.println("4. Run My Test Files");
        System.out.println("5. Exit");

        System.out.print("Enter Option: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                runSpecificationExample();
                break;
            case 2:
                exampleRun();
                break;
            case 3:
                runBenchmarkTest();
                break;
            case 4:
                runCustomTest();
                break;
            case 5:
                System.out.println("Exiting..");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a number between 1 and 4.");
        }
    }

    public static void runSpecificationExample() {
        try {
            String filename = "maze1.txt";
            char[][] maze = Parser.parseFile(filename);
            MazeSolver solver = new MazeSolver(maze);
            System.out.println("\nA* Search:");
            if (!solver.AStar()) {
                System.out.println("No solution could be found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }

    public static void exampleRun() {
        List<String> filenames = List.of("maze10_1.txt", "maze10_2.txt", "maze10_3.txt", "maze10_4.txt",
                "maze10_5.txt", "maze15_1.txt", "maze15_2.txt", "maze15_3.txt", "maze15_4.txt", "maze10_5.txt",
                "maze20_1.txt", "maze20_2.txt", "maze20_3.txt", "maze20_4.txt", "maze20_5.txt", "maze25_1.txt",
                "maze25_2.txt", "maze25_3.txt", "maze25_4.txt", "maze25_5.txt", "maze30_1.txt", "maze30_2.txt",
                "maze30_3.txt", "maze30_4.txt", "maze30_5.txt");
        for (String filename : filenames) {
            String fullPath = buildFilePathForExamples(filename);
            try {
                char[][] maze = Parser.parseFile(fullPath);
                MazeSolver solver = new MazeSolver(maze);
                System.out.println("\nA* Search for file: " + fullPath);
                if (!solver.AStar()) {
                    System.out.println("No solution could be found for " + fullPath);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found for " + fullPath);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error processing file " + fullPath + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void runBenchmarkTest() {
        List<String> filenames = List.of("puzzle_10.txt", "puzzle_20.txt", "puzzle_40.txt", "puzzle_80.txt", "puzzle_160.txt",
                "puzzle_320.txt", "puzzle_1280.txt", "puzzle_2560.txt");
        for (String filename : filenames) {
            String fullPath = buildFilePathForBenchMark(filename);
            try {
                char[][] maze = Parser.parseFile(fullPath);
                MazeSolver solver = new MazeSolver(maze);
                System.out.println("\nA* Search for file: " + fullPath);
                if (!solver.AStar()) {
                    System.out.println("No solution could be found for " + fullPath);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found for " + fullPath);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error processing file " + fullPath + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void runCustomTest() {
        List<String> filenames = List.of("test1.txt", "test2.txt", "test3.txt");
        for (String filename : filenames) {
            String fullPath = buildFilePathForMyTest(filename);
            try {
                char[][] maze = Parser.parseFile(fullPath);
                MazeSolver solver = new MazeSolver(maze);
                System.out.println("\nA* Search for file: " + fullPath);
                if (!solver.AStar()) {
                    System.out.println("No solution could be found for " + fullPath);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found for " + fullPath);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error processing file " + fullPath + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static String buildFilePathForExamples(String filename) {
        return DIRECTORY + filename;
    }

    private static String buildFilePathForBenchMark(String filename) {
        return DIRECTORYTwo + filename;
    }

    private static String buildFilePathForMyTest(String filename) {
        return DIRECTORYThree + filename;
    }
}
