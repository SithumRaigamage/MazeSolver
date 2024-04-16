import java.util.*;
public class MazeSolver {
    private char[][] maze;
    private int startRow, startCol, endRow, endCol;
    private int numRows, numCols;
    private boolean[][] visited;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private Point[][] predecessor;
    private String[] dirNames = {"Up", "Down", "Left", "Right"};

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

    public boolean solveBFS() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startRow, startCol, null));
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int row = current.x;
            int col = current.y;

            if (row == endRow && col == endCol) {
                printPath();
                return true;
            }

            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                if (isValid(newRow, newCol)) {
                    queue.offer(new Point(newRow, newCol, dirNames[i]));
                    visited[newRow][newCol] = true;
                    predecessor[newRow][newCol] = current;
                }
            }
        }

        System.out.println("No path found.");
        return false;
    }

    private void printPath() {
        System.out.println("Path (from start to end):");
        Stack<Point> path = new Stack<>();
        Point current = new Point(endRow, endCol, null);
        while (current != null) {
            path.push(current);
            current = predecessor[current.x][current.y];
        }

        if (!path.isEmpty()) {
            Point start = path.pop();
            System.out.println("Start at (" + (start.x + 1) + ", " + (start.y + 1) + ")");
        }

        while (!path.isEmpty()) {
            Point p = path.pop();
            String direction = p.direction == null ? "" : "Move " + p.direction + " to ";
            System.out.println(direction + "(" + (p.x + 1) + ", " + (p.y + 1) + ")");
        }
        System.out.println("Done!");
    }



    private boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols &&
                maze[row][col] != 'O' && !visited[row][col];
    }
}