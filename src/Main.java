import java.io.FileNotFoundException;
import java.util.List;
public class Main {
    private static final String DIRECTORY = "examples/";  // Constant for the directory path

    public static void main(String[] args) {

        RunSpecificationExample();
        ExampleRun();
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
        List<String> filenames = List.of("maze1.txt", "maze2.txt", "maze3.txt"); // List of filenames without directory

        for (String filename : filenames) {
            String fullPath = buildFilePath(filename); // Construct full path for each file
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
}
