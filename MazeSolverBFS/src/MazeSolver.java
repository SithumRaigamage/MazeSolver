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

    public boolean BFS() {
        Queue<Coordinates> queue = new LinkedList<>();
        queue.offer(new Coordinates(startRow, startCol, null));
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            int row = current.x;
            int col = current.y;

            // Check if the current cell is the destination
            if (row == endRow && col == endCol) {
                printPath(current);
                return true;
            }

            // Consider each direction
            for (int i = 0; i < directions.length; i++) {
                int[] dir = directions[i];
                int newRow = row;  // Initialize newRow and newCol for each direction
                int newCol = col;

                // Slide until an obstacle or border is hit
                while (isValid(newRow + dir[0], newCol + dir[1])) {
                    newRow += dir[0];
                    newCol += dir[1];


                }

                // Check if the new cell is visited and not already in the queue
                if (!visited[newRow][newCol]) {
                    queue.offer(new Coordinates(newRow, newCol, dirNames[i]));
                    visited[newRow][newCol] = true;
                    predecessor[newRow][newCol] = current;
                }
            }
        }

        System.out.println("No path found.");
        return false;
    }

    private void printPath(Coordinates destination) {
        System.out.println("Path from S to F:");
        System.out.println("");
        // Outputting maze specifications after displaying the path
        System.out.println("Maze Width: " + numCols);
        System.out.println("Maze Height: " + numRows);
        System.out.println("S Position: (" + (getStartCol() + 1) + ", " + (getStartRow() + 1) + ")");
        System.out.println("F Position: (" + (getEndCol() + 1) + ", " + (getEndRow() + 1) + ")");
        System.out.println(" ");

        Stack<Coordinates> path = new Stack<>();
        // Construct the path from destination to start
        Coordinates current = destination;
        while (current != null) {
            path.push(current);
            current = predecessor[current.x][current.y];
        }

        if (!path.isEmpty()) {
            Coordinates start = path.pop();
            System.out.println("Start at (" + (start.y + 1) + ", " + (start.x + 1) + ")");
        }

        int stepCount = 1;
        while (!path.isEmpty()) {
            Coordinates p = path.pop();
            String direction = p.direction == null ? "" : "Move " + p.direction + " to ";
            System.out.println(stepCount + ". " + direction + "(" + (p.y + 1) + ", " + (p.x + 1) + ")");
            stepCount++;
        }
        System.out.println(stepCount+". "+"Done!");
    }


    private String inferDirection(Coordinates from, Coordinates to) {
        if (from == null || to == null) {
            return ""; // or handle null case appropriately
        }

        if (from.x == to.x) {
            return (from.y < to.y) ? "right" : "left";
        } else {
            return (from.x < to.x) ? "down" : "up";
        }
    }


    private boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols &&
                maze[row][col] != '0';
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }

}
