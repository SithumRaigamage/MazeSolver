//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Creating the grid given in the example below
        char[][] maze = {
                {'.', '.', '.', '.', '.', 'O', '.', '.', '.', 'S'},
                {'.', '.', '.', '.', 'O', '.', '.', '.', '.', '.'},
                {'O', '.', '.', '.', '.', '.', 'O', '.', '.', 'O'},
                {'.', '.', '.', 'O', '.', '.', '.', '.', 'O', '.'},
                {'.', 'F', '.', '.', '.', '.', '.', '.', 'O', '.'},
                {'.', 'O', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'O', '.', '.'},
                {'.', 'O', '.', 'O', '.', '.', 'O', '.', '.', 'O'},
                {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'O', 'O', '.', '.', '.', '.', '.', 'O', '.'}
        };

        MazeSolver solver = new MazeSolver(maze);
        System.out.println("Breadth-First Search:");
        solver.solveBFS();
    }
}