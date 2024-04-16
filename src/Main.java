import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            String filename = "filename.txt"; // Update with the correct path
            char[][] maze = Parser.parseFile(filename);
            MazeSolver solver = new MazeSolver(maze);
            System.out.println("Breadth-First Search:");
            if (!solver.solveBFS()) {
                System.out.println("No solution could be found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }
}
