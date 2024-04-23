import java.util.*;

public class MazeSolver {
    private char[][] maze;
    private int startRow, startCol, endRow, endCol;
    private int numRows, numCols;
    private boolean[][] visited;
    private Coordinates[][] predecessor;
    private String[] dirNames = {"Up", "Down", "Left", "Right"};
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public MazeSolver(char[][] maze) {
        this.maze = maze;
        this.numRows = maze.length;
        this.numCols = maze[0].length;
        this.visited = new boolean[numRows][numCols];
        this.predecessor = new Coordinates[numRows][numCols];
        findStartAndEnd();
    }

    private void findStartAndEnd() {
        boolean startFound = false, endFound = false;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (maze[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                    startFound = true;
                } else if (maze[i][j] == 'F') {
                    endRow = i;
                    endCol = j;
                    endFound = true;
                }
            }
        }
        if (!startFound || !endFound) {
            throw new IllegalStateException("Start or Finish position not found in the maze.");
        }
    }

    public boolean AStar() {
        PriorityQueue<Coordinates> queue = new PriorityQueue<>(Comparator.comparingInt(a -> (a.g + a.h))); // Priority queue based on f = g + h
        queue.offer(new Coordinates(startRow, startCol, "", 0, calculateHeuristic(startRow, startCol))); // Start node with 0 cost and heuristic value
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            int row = current.x;
            int col = current.y;

            if (row == endRow && col == endCol) {
                printPath(current);
                return true;
            }

            for (int i = 0; i < directions.length; i++) {
                int[] dir = directions[i];
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                int g = current.g + 1; // Cost to reach this node

                // Slide until an obstacle or border is hit
                while (isValid(newRow, newCol)) {
                    newRow += dir[0];
                    newCol += dir[1];
                }
                newRow -= dir[0]; // Move back one step to the last valid position
                newCol -= dir[1];

                // Check if the new position is valid and not visited
                if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
                    int h = calculateHeuristic(newRow, newCol); // Manhattan distance heuristic
                    queue.offer(new Coordinates(newRow, newCol, dirNames[i], g, h));
                    visited[newRow][newCol] = true;
                    predecessor[newRow][newCol] = current;
                }
            }
        }

        System.out.println("No path found.");
        return false;
    }


    private void printPath(Coordinates finalNode) {
        System.out.println("Path from S to F:");
        System.out.println("");
        // Outputting maze specifications after displaying the path
        System.out.println("Maze Width: " + numCols);
        System.out.println("Maze Height: " + numRows);
        System.out.println("S Position: (" + (startCol + 1) + ", " + (startRow + 1) + ")");
        System.out.println("F Position: (" + (endCol + 1) + ", " + (endRow + 1) + ")");
        System.out.println(" ");

        // Printing obstacle indexes
        System.out.println("Obstacle Indexes:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (maze[i][j] == '0') {
                    System.out.println("(" + (j + 1) + ", " + (i + 1) + ")");
                }
            }
        }
        System.out.println();

        LinkedList<Coordinates> path = new LinkedList<>();
        Coordinates current = finalNode;
        while (current != null) {
            path.addFirst(current);
            current = predecessor[current.x][current.y];
        }

        int stepCount = 0;
        Coordinates previous = null;
        for (Coordinates p : path) {
            if (stepCount == 0) {
                System.out.println(++stepCount + ". Start at (" + (p.y + 1) + ", " + (p.x + 1) + ")");
            } else {
                String direction = (p.direction == null && previous != null) ? "Move " + inferDirection(previous, p) : "Move " + p.direction.toLowerCase();
                System.out.println(++stepCount + ". " + direction + " to (" + (p.y + 1) + ", " + (p.x + 1) + ")");
            }
            previous = p;
        }
        System.out.println(++stepCount + ". Done!");
        System.out.println();
    }

    private String inferDirection(Coordinates from, Coordinates to) {
        if (from.x == to.x) {
            return (from.y < to.y) ? "right" : "left";
        } else {
            return (from.x < to.x) ? "down" : "up";
        }
    }

    private int calculateHeuristic(int row, int col) {
        return Math.abs(row - endRow) + Math.abs(col - endCol);
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols &&
                maze[row][col] != '0';
    }

    private static class Coordinates {
        int x, y;
        String direction;
        int g; // Cost to reach this node
        int h; // Heuristic (estimated cost to the goal)

        Coordinates(int x, int y, String direction, int g, int h) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.g = g;
            this.h = h;
        }
    }
}
