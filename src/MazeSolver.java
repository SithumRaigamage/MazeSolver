
import java.util.*;
public class MazeSolver {

    // initialized variables
    private char[][] maze;
    private int startRow, startCol, endRow, endCol;
    private int numRows, numCols;
    private boolean[][] visited;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private Point[][] predecessor;

    //constructor
    public MazeSolver(char[][] maze) {
        this.maze = maze;
        this.numRows = maze.length;
        this.numCols = maze[0].length;
        this.visited = new boolean[numRows][numCols];
        this.predecessor = new Point[numRows][numCols];
        findStartAndEnd();
    }

    private void findStartAndEnd() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (maze[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                }
                if (maze[i][j] == 'F') {
                    endRow = i;
                    endCol = j;
                }
            }
        }
    }


}
