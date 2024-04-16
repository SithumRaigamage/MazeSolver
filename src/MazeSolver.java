import java.util.*;

public class MazeSolver {
    private char[][] maze;
    private int startRow, startCol, endRow, endCol;
    private int numRows, numCols;
    private boolean[][] visited;
    private Point[][] predecessor;
    private String[] dirNames = {"Up", "Down", "Left", "Right"};
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

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
                int[] dir = directions[i];
                int newRow = row;
                int newCol = col;

                // Slide until an obstacle or border is hit
                while (isValid(newRow + dir[0], newCol + dir[1])) {
                    newRow += dir[0];
                    newCol += dir[1];
                }

                if (!visited[newRow][newCol]) {
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
        System.out.println("Path from F to S :");
        System.out.println(" ");
        LinkedList<Point> path = new LinkedList<>();
        Point current = new Point(endRow, endCol, null);
        while (current != null) {
            path.addFirst(current);
            current = predecessor[current.x][current.y];
        }

        int stepCount = 0;
        if (!path.isEmpty()) {
            Point start = path.removeFirst();
            System.out.println(++stepCount + ". Start at (" + (start.x + 1) + ", " + (start.y + 1) + ")");
        }

        while (!path.isEmpty()) {
            Point p = path.removeFirst();
            String direction = p.direction == null ? "" : "Move " + p.direction.toLowerCase() + " to ";
            if (!direction.isEmpty()) {  // Ensures that no steps without direction are numbered
                System.out.println(++stepCount + ". " + direction + "(" + (p.x + 1) + ", " + (p.y + 1) + ")");
            } else {
                // This block only runs if there's a point with no direction, likely the final step before the destination
                System.out.println(++stepCount + ". (" + (p.x + 1) + ", " + (p.y + 1) + ")");
            }
        }
        System.out.println(++stepCount + ". Done!");
    }



    private boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols &&
                maze[row][col] != 'O';
    }
}
