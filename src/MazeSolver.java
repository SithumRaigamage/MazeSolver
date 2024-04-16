import java.util.*;

public class MazeSolver {
    private char[][] maze;
    private int startRow, startCol, endRow, endCol;
    private int numRows, numCols;
    private boolean[][] visited;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private Point[][] predecessor;

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
        queue.offer(new Point(startRow, startCol));
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int row = current.x;
            int col = current.y;

            if (row == endRow && col == endCol) {
                printPath();
                return true;
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (isValid(newRow, newCol)) {
                    queue.offer(new Point(newRow, newCol));
                    visited[newRow][newCol] = true;
                    predecessor[newRow][newCol] = new Point(row, col);
                }
            }
        }

        System.out.println("No path found.");
        return false;
    }

    private void printPath() {
        System.out.println("Path (from start to end):");
        List<Point> path = new ArrayList<>();
        Point current = new Point(endRow, endCol);
        while (current != null) {
            path.add(current);
            current = predecessor[current.x][current.y];
        }
        Collections.reverse(path);
        for (Point p : path) {
            System.out.println("(" + p.y + ", " + p.x + ")");
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols &&
                maze[row][col] != 'O' && !visited[row][col];
    }
}